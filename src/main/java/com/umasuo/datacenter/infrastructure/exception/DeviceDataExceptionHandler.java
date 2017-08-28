package com.umasuo.datacenter.infrastructure.exception;

import com.umasuo.exception.handler.ExceptionHandler;
import com.umasuo.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * DeviceDataExceptionHandler.
 */
@Component
public class DeviceDataExceptionHandler implements ExceptionHandler, HandlerExceptionResolver {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(DeviceDataExceptionHandler.class);

  /**
   * Resolve exception.
   *
   * @param request
   * @param response
   * @param handler
   * @param ex
   * @return
   */
  @Override
  public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                       Object handler, Exception ex) {
    setResponse(request, response, handler, ex);
    addExceptionBody(response, ex);
    return new ModelAndView();
  }

  /**
   * add customized message body to the response.
   *
   * @param response
   * @param ex
   */
  private void addExceptionBody(HttpServletResponse response, Exception ex) {
    try {
      ExceptionBody body = getBody(ex);
      if (body != null) {
        response.getWriter().print(JsonUtils.serialize(body));
      }
    } catch (IOException e) {
      LOG.error("failed to write response JSON", e);
      throw new IllegalStateException(e);
    }
  }

  /**
   * Get customized message body by exception type.
   *
   * @param ex exception.
   * @return exception body.
   */
  private ExceptionBody getBody(Exception ex) {
    assert ex != null;
    return null;
  }
}

package com.umasuo.datacenter.infrastructure.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * exception body.
 * return customized code and message to the client.
 */
@Getter
@Setter
public class ExceptionBody {

  /**
   * CODE.
   */
  private transient int code;

  /**
   * Message
   */
  private transient String message;

  /**
   * Exception creator.
   *
   * @param code
   * @param message
   * @return
   */
  public static ExceptionBody of(int code, String message) {
    ExceptionBody body = new ExceptionBody();
    body.code = code;
    body.message = message;
    return body;
  }
}

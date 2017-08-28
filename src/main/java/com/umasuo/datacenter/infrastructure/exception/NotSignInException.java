package com.umasuo.datacenter.infrastructure.exception;

/**
 * Not sign in exception.
 */
public class NotSignInException extends RuntimeException {

  /**
   * Constructor.
   */
  public NotSignInException() {
    super();
  }

  /**
   * Constructor.
   *
   * @param msg
   */
  public NotSignInException(String msg) {
    super(msg);
  }

  /**
   * Constructor.
   *
   * @param msg
   * @param e
   */
  public NotSignInException(String msg, Throwable e) {
    super(msg, e);
  }
}

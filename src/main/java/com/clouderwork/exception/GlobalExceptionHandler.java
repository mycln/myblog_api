package com.clouderwork.exception;

import com.clouderwork.common.CommResult;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.InterruptedIOException;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  /**
   * Handle sql exception exception response.
   *
   * @param e the e
   * @return the exception response
   */
  @ExceptionHandler(SQLException.class)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public CommResult handleSQLException(Exception e) {
    LOGGER.error("GlobalExceptionHandler.handleSQLException error : {} ", e);
    return CommResult.error(e.getMessage());
  }

  @ExceptionHandler(InterruptedIOException.class)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public CommResult handleInterruptedIOException(Exception e) {
    LOGGER.error("GlobalExceptionHandler.InterruptedIOException error : {} ", e);
    return CommResult.error(e.getMessage());
  }

  /**
   * Handle argument exception exception response.
   *
   * @param e the e
   * @return the exception response
   */
  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public CommResult handleArgumentException(Exception e) {
    LOGGER.error("GlobalExceptionHandler.handleArgumentException error : {} ", e);
    return CommResult.error(e.getMessage());
  }

  /**
   * Handle missing servlet request parameter exception exception response.
   *
   * @param e the e
   * @return the exception response
   */
  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public CommResult handleMissingServletRequestParameterException(Exception e) {
    LOGGER.error("GlobalExceptionHandler.handleMissingServletRequestParameterException error : {} ", e);
    return CommResult.error(e.getMessage());
  }

  /**
   * Handle bind exception exception response.
   *
   * @param e the e
   * @return the exception response
   */
  @ExceptionHandler(BindException.class)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public CommResult handleBindException(Exception e) {
    LOGGER.error("GlobalExceptionHandler.handleBindException error : {} ", e);
    return CommResult.error(((BindException)e).getFieldError().getDefaultMessage());
  }

  /**
   * Handle file size limit exceeded exception exception response.
   *
   * @param e the e
   * @return the exception response
   */
  @ExceptionHandler(FileUploadBase.SizeException.class)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public CommResult handleFileSizeLimitExceededException(FileUploadBase.SizeException e) {
    return CommResult.error(e.getMessage());
  }

}

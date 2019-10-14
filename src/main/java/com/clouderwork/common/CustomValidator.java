package com.clouderwork.common;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class CustomValidator {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomValidator.class);

  /**
   * Validate.
   *
   * @param obj the obj
   * @throws Exception the exception
   */
  public static void validate(Object obj) throws Exception {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);

    if (!CollectionUtils.isEmpty(constraintViolations)) {
      ConstraintViolation<Object> next = constraintViolations.iterator().next();
      String message = next.getMessage();
      LOGGER.error("CustomValidator.validate error : {} ", message);
      ObjectError error = new FieldError(obj.getClass().getSimpleName(), ((PathImpl) ((ConstraintViolationImpl) next).getPropertyPath()).getLeafNode().getName(), next.getMessage());
      BindException bindException = new BindException(obj, obj.getClass().getSimpleName());
      bindException.addError(error);
      throw bindException;
    }
  }
}

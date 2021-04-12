package com.example.schedulingsytem.service;

import com.example.schedulingsytem.domain.BaseEntity;
import com.example.schedulingsytem.exception.EntityFormatException;
import java.util.ArrayList;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseService {

  public void validate(BaseEntity entity) throws EntityFormatException {
    if (entity == null) {
      throw new EntityFormatException("Resource invalid.");
    }

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    Set<ConstraintViolation<Object>> constraintViolations = validator.validate(entity);

    if (constraintViolations.size() > 0) {
      ArrayList<ConstraintViolation> errors = new ArrayList<ConstraintViolation>(
          constraintViolations);
      String errorMessage = errors.get(errors.size() - 1).getPropertyPath().toString() + " " +
          errors.get(errors.size() - 1).getMessage() + ".";
      log.error(errorMessage);

      throw new EntityFormatException(errorMessage);
    }
  }
}

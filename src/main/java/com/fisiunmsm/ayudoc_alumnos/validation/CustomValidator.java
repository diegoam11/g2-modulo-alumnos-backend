package com.fisiunmsm.ayudoc_alumnos.validation;

import com.fisiunmsm.ayudoc_alumnos.exception.CustomException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomValidator {
    private final Validator validator;

    @SneakyThrows
    public <T> T validate (T object) {
        Set<ConstraintViolation<T>> errors = validator.validate(object);
        if(errors.isEmpty())
            return object;
        else {
            String message = errors.
                    stream().
                    map(violation -> {
                        if (violation.getMessage().contains("{field}"))
                            return violation
                                    .getMessage()
                                    .replace(
                                            "{field}",
                                            violation.getPropertyPath().toString().toUpperCase()
                                    );
                        return violation.getMessage();
                    }).
                    collect(Collectors.joining(", "));

            throw new CustomException(HttpStatus.BAD_REQUEST, message);
        }
    }
}

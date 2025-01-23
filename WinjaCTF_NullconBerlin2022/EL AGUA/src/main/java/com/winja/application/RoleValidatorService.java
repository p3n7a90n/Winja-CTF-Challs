package com.winja.application;

import com.winja.domain.Role;
import org.springframework.context.support.BeanDefinitionDsl;

import javax.management.relation.RoleNotFoundException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class RoleValidatorService implements ConstraintValidator<RoleExists, Collection<?>> {

    @Override
    public boolean isValid(final Collection<?> value, final ConstraintValidatorContext context) {
        List<Object> missing = new LinkedList<>();
        for (Object item : value) {
            try {
               if(!Arrays.stream(Role.values()).anyMatch(r->r.name().equals(String.valueOf(item)))){
                   throw new RoleNotFoundException("Role Not Found");
               }
            }
            catch (RoleNotFoundException e) {
                missing.add(item.toString());
            }
        }
        if (missing.isEmpty()) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Missing roles: " + missing)
                .addConstraintViolation();
        return false;
    }
}



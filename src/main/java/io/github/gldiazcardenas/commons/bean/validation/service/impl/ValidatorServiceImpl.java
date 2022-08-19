package io.github.gldiazcardenas.commons.bean.validation.service.impl;

import io.github.gldiazcardenas.commons.bean.validation.Validation;
import io.github.gldiazcardenas.commons.bean.validation.Validator;
import io.github.gldiazcardenas.commons.bean.validation.service.ValidatorException;
import io.github.gldiazcardenas.commons.bean.validation.service.ValidatorService;
import io.github.gldiazcardenas.commons.util.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Validation service implementation that collects all validators available and uses them to validate objects.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
public final class ValidatorServiceImpl implements ValidatorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorServiceImpl.class);

    // Class -> Scope -> Validators
    private final Map<Class<?>, Map<Class<?>, List<Validator<?>>>> validators = new HashMap<>();

    public ValidatorServiceImpl(Collection<Validator<?>> validators) {
        Optional.ofNullable(validators).ifPresent(vls -> vls.forEach(this::registerValidator));
    }

    @Override
    public <T> void validateAndThrow(T object, Class<?> scope) throws ValidatorException {
        Validation validation = validateAndGet(object, scope);
        if (validation.hasAnyError()) {
            throw new ValidatorException(validation);
        }
    }

    @Override
    public <T> Validation validateAndGet(T object, Class<?> scope) {
        Validation.Builder result = Validation.builder();

        List<Validator<T>> validatorsList = getValidatorsOfObjectByScope(object, scope);
        for (Validator<T> validator : validatorsList) {
            Validation validation = validator.validate(object);
            if (!validation.isEmpty()) {
                result.merge(validation);
                if (validator.isImmediate()) {
                    break;
                }
            }
        }

        return result.build();
    }

    @SuppressWarnings({"unchecked"})
    private <T> List<Validator<T>> getValidatorsOfObjectByScope(T object, Class<?> scope) {
        return new ArrayList(validators
                .computeIfAbsent(object.getClass(), c -> new HashMap<>())
                .computeIfAbsent(scope, s -> new ArrayList<>()));
    }

    private void registerValidator(Validator<?> validator) {
        Class<?> type = ReflectionUtils.getGenericInterfaceType(validator.getClass(), 0);
        Map<Class<?>, List<Validator<?>>> validatorsByScope = validators.computeIfAbsent(type, t -> new HashMap<>());
        for (Class<?> scope : validator.getScopes()) {
            List<Validator<?>> validatorsList = validatorsByScope.computeIfAbsent(scope, s -> new ArrayList<>());
            validatorsList.add(validator);
            LOGGER.debug("Validator found => {} {} ", type.getSimpleName(), scope.getSimpleName());
        }
    }

}

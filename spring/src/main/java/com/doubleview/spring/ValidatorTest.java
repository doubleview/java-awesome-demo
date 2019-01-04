package com.doubleview.spring;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * RequestResponseBodyMethodProcessor 133L
 */
public class ValidatorTest {

    @Test
    public void validatorTest() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();

        ValidatorDemo validatorDemo = new ValidatorDemo();
        validatorDemo.setCode(10);

        //使用原生Validator
        Set<ConstraintViolation<ValidatorDemo>> constraintViolationSet = localValidatorFactoryBean.validate(validatorDemo);
        assertEquals(constraintViolationSet.size() , 2);
        constraintViolationSet = localValidatorFactoryBean.validateProperty(validatorDemo, "name");
        assertEquals(constraintViolationSet.size() , 1);
        constraintViolationSet = localValidatorFactoryBean.validateValue(ValidatorDemo.class, "name", "");
        assertEquals(constraintViolationSet.size() , 1);


        //使用spring的校验方法
        BindingResult bindingResult = new BeanPropertyBindingResult(validatorDemo, "validatorDemo", true, 256);
        ValidationUtils.invokeValidator(localValidatorFactoryBean, validatorDemo, bindingResult);

        assertTrue(bindingResult.hasErrors());
        assertEquals(bindingResult.getErrorCount(), 2);
        assertTrue(bindingResult.hasFieldErrors("name"));
        assertTrue(bindingResult.hasFieldErrors("code"));


        ValidationUtils.invokeValidator(localValidatorFactoryBean, validatorDemo, bindingResult, GroupDemo.class);
        assertTrue(bindingResult.hasErrors());
        assertEquals(bindingResult.getErrorCount(), 3);
        assertTrue(bindingResult.hasFieldErrors("list"));
    }


    @Data
    private static class ValidatorDemo{

        @NotBlank
        private String name;

        @Max(5)
        @NotNull
        private Integer code;

        @NotEmpty(groups = GroupDemo.class)
        private List<String> list;
    }

    private interface GroupDemo{
    }
}

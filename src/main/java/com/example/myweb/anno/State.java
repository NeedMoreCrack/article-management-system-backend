package com.example.myweb.anno;

import com.example.myweb.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint( validatedBy = {StateValidation.class} ) //指定效驗規則的類
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface State {
    //失敗後的提示
    String message() default "state參數只能是 D(Draft) 或 P(Published)";

    //指定分組
    Class<?>[] groups() default {};

    //取得State註解的附加訊息
    Class<? extends Payload>[] payload() default {};
}
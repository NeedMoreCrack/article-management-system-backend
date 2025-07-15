package com.example.myweb.validation;

import com.example.myweb.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

//泛型<要效驗的類,效驗的類型>
public class StateValidation implements ConstraintValidator<State,String> {
    /*
    * */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //效驗規則
        if (value == null){
            return false;
        }
        //D = Draft,P = Published
        if (value.equals("D") || value.equals("P")){
            return true;
        }
        return false;
    }
}

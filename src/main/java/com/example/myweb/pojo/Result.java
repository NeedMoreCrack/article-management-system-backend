package com.example.myweb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Integer code;
    private String massage;
    private T data;

    public static <E> Result<E> success(E data){
        return new Result<>(0,"Success",data);
    }

    public static Result success(){
        return new Result(0,"Success",null);
    }

    public static Result error(String message){
        return new Result(1,message,null);
    }
}

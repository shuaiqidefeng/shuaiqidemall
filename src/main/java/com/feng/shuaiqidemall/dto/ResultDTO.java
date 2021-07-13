package com.feng.shuaiqidemall.dto;

import java.io.Serializable;

public class ResultDTO<T> implements Serializable {
    private boolean isSuccess;
    private String message;
    private T data;


    public ResultDTO(boolean isSuccess, String message, T data) {
        this.message = message;
        this.data = data;
        this.isSuccess = isSuccess;
    }

    public static ResultDTO success(boolean isSuccess,String message,Object data){
        return new ResultDTO(isSuccess,message,data);
    }

    public static ResultDTO success(String message){
        return success(true,message,null);
    }

    public static ResultDTO success(String message,Object data){
        return success(true,message,data);
    }

    public static ResultDTO success(Object data){
        return success(true,null,data);
    }

    public static ResultDTO failure(boolean isSuccess,String message,Object data){
        return new ResultDTO(isSuccess,message,data);
    }

    public static ResultDTO failure(String message){
        return new ResultDTO(false,message,null);
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    @Override
    public String toString() {
        return "ResultDTO{" +
                "isSuccess=" + isSuccess +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

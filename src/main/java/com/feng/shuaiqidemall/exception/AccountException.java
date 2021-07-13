package com.feng.shuaiqidemall.exception;

public class AccountException extends RuntimeException {

    private Object data;

    public AccountException() {
        super();
    }

    public AccountException(String message) {
        super(message, null);
    }

    public AccountException(Object data) {
        this(null, data);
    }

    public AccountException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public Object getData() {
        return getData(Object.class);
    }

    public <T> T getData(Class<T> clazz) {
        if (data == null) {
            return null;
        }
        try {
            return (T) data;
        } catch (ClassCastException castException) {
            return null;
        }
    }
}

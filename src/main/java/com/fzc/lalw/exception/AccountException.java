package com.fzc.lalw.exception;

/**
 * @author PerfectFu
 * @projectName IntelliJ IDEA
 * @date 2022/3/12
 */

public class AccountException extends Exception{
    public AccountException() {
        super();
    }

    public AccountException(String message) {
        super(message);
    }
}

package com.lemon.examination.bean;

/**
 * Created by David on 2017/5/5.
 */

public class BaseResult<T> {

    private int resultCode;
    private String resultMsg;
    private T t;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}

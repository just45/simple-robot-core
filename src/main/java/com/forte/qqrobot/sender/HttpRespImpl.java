/*
 * Copyright (c) 2020. ForteScarlet All rights reserved.
 * Project  simple-robot-core
 * File     HttpRespImpl.java
 *
 * You can contact the author through the following channels:
 * github https://github.com/ForteScarlet
 * gitee  https://gitee.com/ForteScarlet
 * email  ForteScarlet@163.com
 * QQ     1149159218
 *
 */

package com.forte.qqrobot.sender;

/**
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public class HttpRespImpl implements HttpResp {

    private int code;
    private String response;
    private String reasonPhrase;

    public HttpRespImpl(String response, int code, String reasonPhrase) {
        this.code = code;
        this.response = response;
        this.reasonPhrase = reasonPhrase;
    }



    @Override
    public String toString(){
        return response;
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }
}

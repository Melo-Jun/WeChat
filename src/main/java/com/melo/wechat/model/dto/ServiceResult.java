package com.melo.wechat.model.dto;/*
 * Copyright (c) 2019.  黄钰朝
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import com.melo.wechat.constant.Status;


/**
 * @Description: service层返回结果状态码
 * @author: Jun
 * @date: 10:36 2021/5/2
 */
public class ServiceResult {
    /**
     * 状态码
     */
    private Status status;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 响应数据
     */
    private Object data;
    /**
     * 操作成功与否
     */
    public boolean flag;


    public ServiceResult() {
    }

    public ServiceResult(boolean flag) {
        this.flag = flag;
    }

    public ServiceResult(String message, boolean flag) {
        this.message = message;
        this.flag = flag;
    }

    public ServiceResult(Object data, boolean flag) {
        this.data = data;
        this.flag = flag;
    }

    public ServiceResult(boolean flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public ServiceResult(String message) {
        this.message = message;
    }

    public ServiceResult(Object data) {
        this.data=data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

package com.melo.wechat.constant;

/**
 * @Description: 路径枚举类
 * @author: Jun
 * @date: 10:51 2021/5/25
 */
public enum Path {

    /**
     * PNG
     */
    PNG(".png"),
    /**
     * JPG
     */
    JPG(".jpg");

    private String path;

    Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

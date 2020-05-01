package com.mmkjflb.mmloan.model.entity;

import java.io.File;

/**
 * Created by Administrator on 2017/12/15.
 */

public class ClickEvent {

    private int status=-2222;
    private File  url=null;
    private int type=-333;
    public ClickEvent(int status){
        this.status=status;
    }

    public ClickEvent(int type, File url){
        this.url=url;
        this.type=type;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public File getUrl() {
        return url;
    }

    public void setUrl(File url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}


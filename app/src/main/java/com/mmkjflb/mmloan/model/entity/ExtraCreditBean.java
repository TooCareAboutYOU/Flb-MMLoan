package com.mmkjflb.mmloan.model.entity;

public class ExtraCreditBean {

    private boolean facebookAuth;
    private boolean grabAuth;
    private long userId;

    public boolean isFacebookAuth() {
        return facebookAuth;
    }

    public void setFacebookAuth(boolean facebookAuth) {
        this.facebookAuth = facebookAuth;
    }


    public boolean isGrabAuth() {
        return grabAuth;
    }

    public void setGrabAuth(boolean grabAuth) {
        this.grabAuth = grabAuth;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}

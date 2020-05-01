package com.mmkjflb.mmloan.model.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author zhangshuai
 */
public class ItemCertificationBean implements MultiItemEntity {
    public static final int TEXT = 1;
    public static final int CARD = 2;


    private int itemType;
    private int icon;
    private int title;
    private int txtColor;


    public ItemCertificationBean(int itemType,int title) {
        this.itemType = itemType;
        this.title=title;
    }

    public ItemCertificationBean(int itemType, int icon, int title, int txtColor) {
        this.itemType = itemType;
        this.icon = icon;
        this.title = title;
        this.txtColor = txtColor;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getTxtColor() {
        return txtColor;
    }

    public void setTxtColor(int txtColor) {
        this.txtColor = txtColor;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append(",\"icon\":")
                .append(icon);
        sb.append(",\"title\":")
                .append(title);
        sb.append(",\"txtColor\":")
                .append(txtColor);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}

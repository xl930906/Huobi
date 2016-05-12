package com.android.HuoBiAssistant.model.bean;

/**
 * Created by Dragon丶Lz on 2016/4/1.
 */
public class LeftSlideBean {

    private int ImgId;
    private String ListName;

    public LeftSlideBean(int ImgId , String ListName){
        this.ImgId = ImgId;
        this.ListName = ListName;
    }

    public int getImgId() {
        return ImgId;
    }

    public void setImgId(int imgId) {
        ImgId = imgId;
    }

    public String getListName() {
        return ListName;
    }

    public void setListName(String listName) {
        ListName = listName;
    }
}

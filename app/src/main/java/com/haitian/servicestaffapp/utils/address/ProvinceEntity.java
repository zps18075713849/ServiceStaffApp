package com.haitian.servicestaffapp.utils.address;


public class ProvinceEntity {

    /**
     * text : 北京市
     * id : 110000
     */
    private String text;
    private String id;
    public boolean isSelect; // 是否选中


    public void setText(String text) {
        this.text = text;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public String getId() {
        return id;
    }
}

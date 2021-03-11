package com.haitian.servicestaffapp.utils.address;


public class CityEntity {

    /**
     * text : 市辖区
     * id : 110100
     */
    private String text;
    private String id;

    public CityEntity() {
    }

    public CityEntity(String text, String id) {
        this.text = text;
        this.id = id;
    }

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

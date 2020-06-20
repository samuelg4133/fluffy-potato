package com.server.model.dto;

import com.server.model.Item;

import javax.persistence.Entity;
import javax.persistence.Transient;


public class ItemDTO {

    private Item item;

    public ItemDTO(Item item) {
        this.item = item;
    }

    public Long getId(){
        return item.getId();
    }

    public String getTitle(){
       return item.getTitle();
    }

    public String getImageURL(){
        return "http://localhost:8080/uploads/"+item.getImage();
    }
}

package com.server.model.dto;

import com.server.model.Item;
import com.server.model.Point;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PointDTO {

    private Point point;

    private List<Item> items;

    public PointDTO(Point point) {
        this.point = point;
    }

    public Long getId(){
        return point.getId();
    }

    public String getName(){
        return point.getName();
    }

    public String getImage(){
        return point.getImage();
    }

    public String getEmail(){
        return point.getEmail();
    }

    public String getWhatsapp(){
        return point.getWhatsapp();
    }

    public BigDecimal getLatitude(){
        return point.getLatitude();
    }

    public BigDecimal getLongitude(){
        return point.getLongitude();
    }

    public String getCity(){
        return point.getCity();
    }

    public String getUf(){
        return point.getUf();
    }

    public List<String> getItems(){
        List<String> titles = new ArrayList<>();

        this.items.forEach(
                item -> {
                    titles.add(item.getTitle());
                }
        );

        return titles;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}

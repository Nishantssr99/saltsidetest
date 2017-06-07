package com.saltside.test.entity;

import javax.validation.constraints.NotNull;

/**
 * Created by krsna on 07/06/2017.
 */
public class Continents {
    private String type;
    private String description;
    private int minItems;
    private Items items;
    private boolean uniqueItems;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getMinItems() {
        return this.minItems;
    }

    public void setMinItems(int minItems) {
        this.minItems = minItems;
    }


    public Items getItems() {
        return this.items;
    }

    public void setItems(Items items) {
        this.items = items;
    }


    public boolean getUniqueItems() {
        return this.uniqueItems;
    }

    public void setUniqueItems(boolean uniqueItems) {
        this.uniqueItems = uniqueItems;
    }
}

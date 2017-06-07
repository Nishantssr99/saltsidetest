package com.saltside.test.entity;

import javax.validation.constraints.NotNull;

/**
 * Created by krsna on 07/06/2017.
 */

public class Name {
    private String type;
    private String description;

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
}

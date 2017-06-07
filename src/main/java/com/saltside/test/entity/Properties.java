package com.saltside.test.entity;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by krsna on 07/06/2017.
 */
public class Properties {
    private Name name;
    private Family family;
    private Continents continents;
    private Added added;
    @Value("#{config['value'] ?: 'false'}")
    private Visible visible;

    public Name getName() {
        return this.name;
    }

    public void setName(Name name) {
        this.name = name;
    }


    public Family getFamily() {
        return this.family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }


    public Continents getContinents() {
        return this.continents;
    }

    public void setContinents(Continents continents) {
        this.continents = continents;
    }

    public Added getAdded() {
        return this.added;
    }

    public void setAdded(Added added) {
        this.added = added;
    }


    public Visible getVisible() {
        return this.visible;
    }

    public void setVisible(Visible visible) {
        this.visible = visible;
    }
}

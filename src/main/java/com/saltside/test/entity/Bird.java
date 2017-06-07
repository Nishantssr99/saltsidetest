package com.saltside.test.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.util.ArrayList;

/**
 * Created by krsna on 03/06/2017.
 */
@Document(collection = "bird")
public class Bird {
    @Id
    public String id;
    private String $schema;
    private String title;
    private String description;
    private String type;
    private ArrayList<String> required;
    private boolean additionalProperties;
    private Properties properties;

    @Indexed(unique = true)
    private int birdId;

    public Bird() {
    }

    public String getSchema() {
        return this.$schema;
    }

    public void setSchema(String $schema) {
        this.$schema = $schema;
    }


    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public ArrayList<String> getRequired() {
        return this.required;
    }

    public void setRequired(ArrayList<String> required) {
        this.required = required;
    }


    public boolean getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperties(boolean additionalProperties) {
        this.additionalProperties = additionalProperties;
    }


    public Properties getProperties() {
        return this.properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public int getBirdId() {
        return birdId;
    }

    public void setBirdId(int birdId) {
        this.birdId = birdId;
    }

}


/*

{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "title": "POST /birds [request]",
  "description": "Add a new bird to the library",
  "type": "object",
  "required": [
    "name",
    "family",
    "continents"
  ],
  "additionalProperties": false,
  "properties": {
    "name": {
      "type": "string",
      "description": "English bird name"
    },
    "family": {
      "type": "string",
      "description": "Latin bird family name"
    },
    "continents": {
      "type": "array",
      "description": "Continents the bird exist on",
      "minItems": 1,
      "items": { "type": "string" },
      "uniqueItems": true
    },
    "added": {
      "type": "string",
      "description": "Date the bird was added to the registry. Format YYYY-MM-DD"
    },
    "visible": {
      "type": "boolean",
      "description": "Determines if the bird should be visible in lists"
    }
  }
}
 */
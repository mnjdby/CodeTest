package com.fullerton.olp.controller.util;

/**
 * Simple class that holds a primary key and a label for autocomplete
 */
public class AutoCompleteResult {
    private String id;
    private String label;

    public AutoCompleteResult(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }
}
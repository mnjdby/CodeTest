package com.fullerton.olp.controller.util;

/**
 * Used by the {@link SearchForm} to allow more flexible search options
 */
public class NullRestriction {

    public static enum NullRestrictionKind {
        SPECIFIED, ANY, IS_NOT_NULL, IS_NULL
    }

    private String property;
    private NullRestrictionKind restriction = NullRestrictionKind.SPECIFIED;

    /**
     * @param property the property on which the restriction applies.
     */
    public NullRestriction(String property) {
        this.property = property;
    }

    public final String getProperty() {
        return property;
    }

    public void setRestriction(NullRestrictionKind restriction) {
        this.restriction = restriction;
    }

    public NullRestrictionKind getRestriction() {
        return restriction;
    }
}
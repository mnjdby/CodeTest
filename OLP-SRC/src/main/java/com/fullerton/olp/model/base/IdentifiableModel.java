package com.fullerton.olp.model.base;

import java.io.Serializable;

/**
 * <p>
 * By making entities implement this interface we can easily retrieve from the generic DAO the identifier property of the entity.
 * </p>
 * 
 * @author nitishchandrap
 * @since Mar 26, 2012
 * @param <PK>
 */
public interface IdentifiableModel<PK extends Serializable> {

    /**
     * @return the primary key
     */
    PK getId();

    /**
     * Sets the primary key
     */
    void setId(PK id);

    /**
     * Helper method to know whether the primary key is set or not.
     * 
     * @return true if the primary key is set, false otherwise
     */
    boolean isIdSet();

    @Override
    public int hashCode();

    @Override
    public String toString();

    @Override
    public boolean equals(Object obj);
}

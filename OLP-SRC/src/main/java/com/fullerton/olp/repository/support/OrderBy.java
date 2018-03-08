package com.fullerton.olp.repository.support;

import static com.fullerton.olp.repository.support.OrderByDirection.ASC;
import static com.fullerton.olp.repository.support.OrderByDirection.DESC;

import java.io.Serializable;


import org.apache.commons.lang.Validate;

/**
 * Holder class for search ordering used by the {@link SearchTemplate}
 */
public class OrderBy implements Serializable {
    private static final long serialVersionUID = 1L;
    private String column;
    private OrderByDirection direction;

    public OrderBy(String column, OrderByDirection direction) {
        Validate.notNull(column);
        Validate.notNull(direction);
        this.column = column;
        this.direction = direction;
    }

    public OrderBy(String column) {
        Validate.notNull(column);
        this.column = column;
        this.direction = ASC;
    }

    public String getColumn() {
        return column;
    }

    public OrderByDirection getDirection() {
        return direction;
    }

    public boolean isOrderDesc() {
        return DESC == direction;
    }
}
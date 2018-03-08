package com.fullerton.olp.controller.util;

import java.io.Serializable;
import java.util.List;

import com.fullerton.olp.repository.support.DateRange;
import com.fullerton.olp.repository.support.SearchTemplate;

import java.util.ArrayList;

/**
 * Holds informations to be displayed and filled by

import com.fullerton.olp.repository.support.DateRange; the spring mvc {@link Controller}.
 * <p>
 * This class return its information as a {@link SearchTemplate} that will be consummed by the {@link GenericEntityService}
 */
public class SearchForm implements Serializable {
    private static final long serialVersionUID = 1L;
    private SearchParameters searchParameters = new SearchParameters();

    /**
     * @return the {@link SearchParameters} controlling search meta attributes (order, pagination, etc.)
     */
    public SearchParameters getSp() {
        return searchParameters;
    }

    /**
     * Override it in subclass in order to provide specific {@link DateRange} criteria to search.
     */
    protected List<DateRange<?>> getDateRanges() {
        return new ArrayList<DateRange<?>>();
    }

    /**
     * Override it in subclass in order to provide specific {@link NullRestriction} criteria in search.
     */
    protected List<NullRestriction> getNullRestrictions() {
        return new ArrayList<NullRestriction>();
    }

    /**
     * Copy this search form information to a new {@link SearchTemplate} and returns it.
     */
    public SearchTemplate toSearchTemplate() {
        // search meta parameters
        SearchTemplate searchTemplate = searchParameters.toSearchTemplate();

        // date ranges
        searchTemplate.setDateRanges(getDateRanges());

        // null/not null criterion //TODO check this
        /*for (NullRestriction nr : getNullRestrictions()) {
            if (nr.getRestriction() == IS_NULL) {
                searchTemplate.addCriterion(isNull(nr.getProperty()));
            } else if (nr.getRestriction() == IS_NOT_NULL) {
                searchTemplate.addCriterion(isNotNull(nr.getProperty()));
            }
        }*/
        return searchTemplate;
    }
}
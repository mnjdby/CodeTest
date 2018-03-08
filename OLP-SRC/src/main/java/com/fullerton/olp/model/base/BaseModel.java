package com.fullerton.olp.model.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@SuppressWarnings("rawtypes")
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseModel<PK extends Serializable, T extends BaseModel> implements IdentifiableModel<PK>, Serializable, Copyable<T>{

    private static final long serialVersionUID = 1303520228057448936L;
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        IdentifiableModel<PK> other = (IdentifiableModel<PK>) obj;
        if (this.getId() == null) {
            if (other.getId() != null) {
                return false;
            }
        } else if (!this.getId().equals(other.getId())) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
        return result;
    }

    /*@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }*/

    public boolean isIdSet() {
      return getId() != null;
    }

    @Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
    
    @Column(name = "created_on", nullable = false, updatable = false)
    @CreatedDate
    protected Date createdOn;
 
    @Column(name = "modified_on")
    @LastModifiedDate
    protected Date modifiedOn;
    
    @Column(name = "created_by")
    @CreatedBy
    protected String createdBy;
 
    @Column(name = "modified_by")
    @LastModifiedBy
    protected String modifiedBy;

}

package com.fullerton.olp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullerton.olp.model.LoanEligibility;

//@Repository
public interface LoanEligibilityRepository extends GenericDao<LoanEligibility, Long> {
	
	 @Query("SELECT l FROM LoanEligibility l WHERE l.tier.id = :tierId and l.qualification.id = :qualificationId")
	 LoanEligibility findByTierAndQualification(@Param("tierId") Long tierId,@Param("qualificationId") Long qualificationId);
}
package com.fullerton.olp.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullerton.olp.model.UserDetail;

//@Repository
public interface UserDetailRepository extends GenericDao<UserDetail, Long> {

	  @Query("SELECT count(*) FROM UserDetail ud  WHERE ud.mobile = :mobile and ud.professionalDetail.panNumber = :panNumber and createdOn > :dateCreated ")
 	  Long getPanCount(@Param("mobile") String mobile, @Param("panNumber") String panNumber, @Param("dateCreated") Date dateCreated);
	  
	  @Query("SELECT count(*) FROM UserDetail ud")
 	  Long getTotalApplicationsCount();
	  
	  @Query("SELECT ud.city.state.name as key,  count(*) as value FROM UserDetail ud group by ud.city.state.name")
 	  List<Map<String,Object>> getStatewiseTotalApplicationsCount();
	  
	  @Query("SELECT count(*) FROM UserDetail ud  WHERE ud.applicationStatus.id = 1227")
 	  Long getTotalDisbursementCount();
	  
	  @Query("SELECT count(*) FROM UserDetail ud  WHERE ud.applicationStatus.id = 161")
 	  Long getTotalInprogressCount();
	  
	  @Query("SELECT count(*) FROM UserDetail ud  WHERE ud.applicationStatus.id = 1231")
 	  Long getTotalLeadsCount();
	    
	  @Query("SELECT count(*) FROM UserDetail ud  WHERE ud.profession.id = :professionId")
	  Long getTotalApplicationsCount(@Param("professionId") Long professionId);

	  @Query("SELECT ud.city.state.name as key, count(*) as value FROM UserDetail ud WHERE ud.profession.id = :professionId group by ud.city.state.name")
	  List<Map<String,Object>> getStatewiseTotalApplicationsCount(@Param("professionId") Long professionId);
	  
	  @Query("SELECT count(*) FROM UserDetail ud  WHERE ud.applicationStatus.id = 1227 and ud.profession.id = :professionId")
 	  Long getTotalDisbursementCount(@Param("professionId") Long professionId);
	  
	  @Query("SELECT count(*) FROM UserDetail ud  WHERE ud.applicationStatus.id = 161 and ud.profession.id = :professionId")
 	  Long getTotalInprogressCount(@Param("professionId") Long professionId);
	  
	  @Query("SELECT count(*) FROM UserDetail ud  WHERE ud.applicationStatus.id = 1231 and ud.profession.id = :professionId")
 	  Long getTotalLeadsCount(@Param("professionId") Long professionId);

	    
}
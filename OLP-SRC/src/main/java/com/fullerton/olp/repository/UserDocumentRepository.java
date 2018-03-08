package com.fullerton.olp.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullerton.olp.model.UserDocument;

//@Repository
public interface UserDocumentRepository extends GenericDao<UserDocument, Long> {

	 	@Query("UPDATE UserDocument ud set ud.active = false WHERE ud.userDetail.id = :userDetailId and ud.documentType.id = :documentTypeId and ud.document.id = :documentId ")
	 	@Transactional
	 	  @Modifying
	 	  void markInactive(@Param("userDetailId") Long userDetailId,@Param("documentTypeId") Long documentTypeId,@Param("documentId") Long documentId);
}
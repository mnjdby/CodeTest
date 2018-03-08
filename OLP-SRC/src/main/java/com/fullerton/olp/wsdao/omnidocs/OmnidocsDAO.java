package com.fullerton.olp.wsdao.omnidocs;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;


/**
 * Client to handle file upload related services
 * 
 * @author nitish
 *
 */
public interface OmnidocsDAO {

	
	public String upload(String filePath, MultipartFile multipartFile) throws IllegalStateException, IOException;

}
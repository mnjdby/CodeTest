package com.fullerton.olp.wsdao.omnidocs;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


/**
 * Client to handle file upload related services
 * 
 * @author nitish
 *
 */
@Component
public class OmnidocsDAOImpl  implements OmnidocsDAO {

	private static final Logger log = LoggerFactory.getLogger(OmnidocsDAO.class);

	@Value("${omnidocs.host}")
	private String OMNIDOCS_HOST;
	
		
	
	public String upload(String filePath, MultipartFile multipartFile) throws IllegalStateException, IOException {
		
		File file = new File(OMNIDOCS_HOST+ filePath);
		file.getParentFile().mkdirs();
		file.createNewFile();
		file.setWritable(true);
		multipartFile.transferTo(file);
		return file.getPath();
		
	}

}
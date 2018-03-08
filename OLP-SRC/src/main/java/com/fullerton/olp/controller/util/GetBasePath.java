package com.fullerton.olp.controller.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class GetBasePath {
	
	public static void main(String args[]) throws Exception{
	    GetBasePath s = new GetBasePath();
	    s.getRoot();
	  }

	  public String getRoot() throws IOException, URISyntaxException{
	    URL u = getClass().getProtectionDomain().getCodeSource().getLocation();
	    File f = new File(u.toURI());
	    String basePath = f.getParent();
	    basePath = basePath.replace("target", "");
	    
	    return basePath;
	  }
}

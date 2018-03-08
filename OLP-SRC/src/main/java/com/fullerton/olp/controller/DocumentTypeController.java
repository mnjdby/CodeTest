package com.fullerton.olp.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fullerton.olp.controller.util.SearchParameters;
import com.fullerton.olp.model.DocumentType;
import com.fullerton.olp.service.DocumentTypeService;

@Controller
@RequestMapping("documentType")
public class DocumentTypeController extends BaseController{
    
	@Autowired
    public FormattingConversionService formattingConversionService;

    @Autowired
    private DocumentTypeService documentTypeService;
    
    
    @RequestMapping(value = "/{pk}")
    @ResponseBody
    public DocumentType get(@PathVariable("pk") DocumentType documentType) {
        return documentType;
    }
    
    
    @RequestMapping(value = "/", method = GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> list(DocumentType documentType, SearchParameters searchParameters) {
    	
        List<DocumentType> documentTypes = documentTypeService.find(documentType, searchParameters.toSearchTemplate());
        List<DocumentType> ret = new ArrayList<DocumentType>();
        for (DocumentType _documentType : documentTypes) {
            ret.add(_documentType.copy());
        }
        Map<String, Object> result = new HashMap<>();
        
        result.put("size", documentTypes.size());
        result.put("value", ret);
        
        ResponseEntity<Map<String,Object>> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

   
}
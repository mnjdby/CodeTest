package com.fullerton.olp.wsdao.crmnext;

import java.util.Calendar;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.fullerton.olp.model.UserDetail;
import com.fullerton.olp.wsdao.bre.model.InvokeBREResponse;
import com.fullerton.olp.wsdao.crmnext.model.ArrayOfSaveResult;
import com.fullerton.olp.wsdao.crmnext.model.CRMnextObjectKey;
import com.fullerton.olp.wsdao.crmnext.model.CRMnextObjectType;
import com.fullerton.olp.wsdao.crmnext.model.Fetch;
import com.fullerton.olp.wsdao.crmnext.model.FetchResponse;
import com.fullerton.olp.wsdao.crmnext.model.Lead;
import com.fullerton.olp.wsdao.crmnext.model.ObjectFactory;
import com.fullerton.olp.wsdao.crmnext.model.Save;
import com.fullerton.olp.wsdao.crmnext.model.SaveResponse;
import com.fullerton.olp.wsdao.crmnext.model.UserContext;


/**
 * Sample client to invoice WS
 * 
 * @author nitish
 *
 */
public class CrmNextDAOImpl extends WebServiceGatewaySupport implements CrmNextDAO{

	private static final Logger log = LoggerFactory.getLogger(CrmNextDAO.class);

	@Value("${crm-ws.token}")
	private String token = "qjg8clqv8mjs5njd6l2s676fk7uplvkfh4grbugc7gsfkj3eujzl5sjxyzz9u67bel3kdzdbvqaces9qkwst4ud6qktbk8lxk9lsghsu3duc6l4dwq39lh6x2x3qe729gh433ymdpajs93hpwzdpq8dmbmhmdnqyzyg4v6d5d9w4kmcug5zms3es8uavzwrgyguybajv7tgelytyjec884csa3bjsydc7kbqpgf73pk3cc6u6k4l94qbxjl6cm9qh2f8ab4uwwuc348ztqv6fwuhykhe2seq4hckadv333sc263tbqncgsh8ymmeq8m7evnw6gr3ndngzsemwvtlnaltg47j4jwbtj6zfah86erb8x3x5xup35buymb6y6mz6vdl6l4378lreaete9cw7j5k44bpb3tej8r8a39gs8abd2wrl4tph86acdgl879znugl35afvjmetlstfth4hlpal3nnb3ltfqzkcwbmdhf5dvck2sxax8qus2d9w8ryucqlcb458jjrtmrpll7cvprwrxjgmvdc9cygnacalxf7fqrnnypzb5p8xc5ftkte2dyv5q52sk8gbmawt9sdgr7z55789c4cwn5x5f2";
	
	public String createLead(UserDetail userDetail) {
		Lead leadRequest = new ObjectFactory().createLead();
        
		//saveRequest.setUserContext(value);
		//log.info("Creating new account with request: " + request);

		SaveResponse response =  (SaveResponse)getWebServiceTemplate()
				.marshalSendAndReceive(getWebServiceTemplate().getDefaultUri(),
						leadRequest,
						new SoapActionCallback("Save"));
		JAXBElement<ArrayOfSaveResult> saveResult = response.getSaveResult();
		System.out.println(saveResult.getValue());
		/*FetchOutputNewApplicationXML response =  (FetchOutputNewApplicationXML) getWebServiceTemplate()
				.marshalSendAndReceive(getWebServiceTemplate().getDefaultUri(),
						root,
						new SoapActionCallback("fetchNewApplicationXML"));*/

		
		/*<wsdl:operation name="Save">
		  <wsdl:input wsaw:Action="http://www.crmnext.com/api/ICRMnextApi/Save" message="tns:ICRMnextApi_Save_InputMessage" /> 
		  <wsdl:output wsaw:Action="http://www.crmnext.com/api/ICRMnextApi/SaveResponse" message="tns:ICRMnextApi_Save_OutputMessage" /> 
		  </wsdl:operation>*/
		return null;
		
		
	}
	
	public FetchResponse leadFetch(UserDetail userDetail) {
		Fetch fetchRequest = new ObjectFactory().createFetch();
		JAXBElement<UserContext> userContext = fetchRequest.getUserContext();
		setUserContext(userContext);
		userContext.getValue().getLongDateFormat().setValue("");
		userContext.getValue().getLongDateTimeFormat().setValue("");
		
		fetchRequest.setObjectType(CRMnextObjectType.LEAD);
		
		CRMnextObjectKey crmNextObjectKey = new ObjectFactory().createCRMnextObjectKey();
		crmNextObjectKey.getItemKey().setValue(userDetail.getCrmLeadId());//TODO check this
		
		fetchRequest.getObjectids().getValue().getCRMnextObjectKeies().add(crmNextObjectKey); 
		
		
		FetchResponse response =  (FetchResponse)getWebServiceTemplate()
				.marshalSendAndReceive(getWebServiceTemplate().getDefaultUri(),
						fetchRequest,
						new SoapActionCallback("Fetch"));
		System.out.println(response);
		
//		<api:Fetch>
//        <!--Optional:-->
//        <api:userContext>
//           <!--Optional:-->
//           <api:IsSuccess>true</api:IsSuccess>
//           <!--Optional:-->
//           <api:Message></api:Message>
//           <!--Optional:-->
//           <api:ClientTimeOffSet>0</api:ClientTimeOffSet>
//           <!--Optional:-->
//           <api:ExpiresOn>2027-04-07T04:12:19.3497212+05:30</api:ExpiresOn>
//           <!--Optional:-->
//           <api:IsUTC>false</api:IsUTC>
//           <!--Optional:-->
//           <api:LongDateFormat></api:LongDateFormat>
//           <!--Optional:-->
//           <api:LongDateTimeFormat></api:LongDateTimeFormat>
//           <!--Optional:-->
//           <!--<api:Token>qjg8clqv8mjs5njd6l2s676fkp4v9ls33x27s4zcvb25y9d965nnzlwr5cnbvf84henvdscnvmfxkdfrwap5e2k3nmrtn8dneu5pcy676hymgmjxfbtfclysd7thqhcf4zll7fmdgqgnlcer6hz748hktsegtybt8jcns5gbrjerphg4gqvfr35l338rdp9f6u8w48cq6c8psbsse4xpt6ph7jkvld4dplaqek2c5a6da6vbkstexu4gjgy7ya5mz7nz2znt7fbcr7prcmlgqhharve53r8w2f4jyg2nnr597mhfr4q3a2pb8twt6fyb</api:Token>-->
//           <api:Token>qjg8clqv8mjs5njd6l2s676fk7uplvkfh4grbugc7gsfkj3eujzl5sjxyzz9u67bel3kdzdbvqaces9qkwst4ud6qktbk8lxk9lsghsu3duc6l4dwq39lh6x2x3qe729gh433ymdpajs93hpwzdpq8dmbmhmdnqyzyg4v6d5d9w4kmcug5zms3es8uavzwrgyguybajv7tgelytyjec884csa3bjsydc7kbqpgf73pk3cc6u6k4l94qbxjl6cm9qh2f8ab4uwwuc348ztqv6fwuhykhe2seq4hckadv333sc263tbqncgsh8ymmeq8m7evnw6gr3ndngzsemwvtlnaltg47j4jwbtj6zfah86erb8x3x5xup35buymb6y6mz6vdl6l4378lreaete9cw7j5k44bpb3tej8r8a39gs8abd2wrl4tph86acdgl879znugl35afvjmetlstfth4hlpal3nnb3ltfqzkcwbmdhf5dvck2sxax8qus2d9w8ryucqlcb458jjrtmrpll7cvprwrxjgmvdc9cygnacalxf7fqrnnypzb5p8xc5ftkte2dyv5q52sk8gbmawt9sdgr7z55789c4cwn5x5f2</api:Token>
//        </api:userContext>
//        <!--Optional:-->
//        <api:objectType>Lead</api:objectType>
//        <!--Optional:-->
//        <api:objectids>
//           <!--Zero or more repetitions:-->
//           <api:CRMnextObjectKey>
//              <!--Optional:-->
//              <api:ItemKey>1528924</api:ItemKey>
//           </api:CRMnextObjectKey>
//        </api:objectids>
//     </api:Fetch>
     return response;
	}

	private void setUserContext(JAXBElement<UserContext> userContext) {
		
		userContext.getValue().setToken(new ObjectFactory().createUserContextToken(token));
		userContext.getValue().setIsSuccess(true);
		userContext.getValue().setMessage(new ObjectFactory().createResultMessage(""));
		userContext.getValue().setClientTimeOffSet(0d);
		Calendar calendar = Calendar.getInstance();
		calendar.set(2027, 03, 07, 04, 12, 19);  
//		"2027-04-07T04:12:19.3497212+05:30"
		userContext.getValue().setExpiresOn(calendar);
		userContext.getValue().setIsUTC(true);
	}
	
	public String createSave(UserDetail userDetail  ) {
		Save saveRequest = new ObjectFactory().createSave();
		saveRequest.setUserContext(new ObjectFactory().createUserContext(new ObjectFactory().createUserContext()));
		JAXBElement<UserContext> userContext = saveRequest.getUserContext();
		setUserContext(userContext);

		SaveResponse response =  (SaveResponse)getWebServiceTemplate()
				.marshalSendAndReceive(getWebServiceTemplate().getDefaultUri(),
						saveRequest,
						new SoapActionCallback("Save"));
		JAXBElement<ArrayOfSaveResult> saveResult = response.getSaveResult();
		System.out.println(saveResult.getValue());
		/*FetchOutputNewApplicationXML response =  (FetchOutputNewApplicationXML) getWebServiceTemplate()
				.marshalSendAndReceive(getWebServiceTemplate().getDefaultUri(),
						root,
						new SoapActionCallback("fetchNewApplicationXML"));*/

		
		/*<wsdl:operation name="Save">
		  <wsdl:input wsaw:Action="http://www.crmnext.com/api/ICRMnextApi/Save" message="tns:ICRMnextApi_Save_InputMessage" /> 
		  <wsdl:output wsaw:Action="http://www.crmnext.com/api/ICRMnextApi/SaveResponse" message="tns:ICRMnextApi_Save_OutputMessage" /> 
		  </wsdl:operation>*/
		return null;
	}

}
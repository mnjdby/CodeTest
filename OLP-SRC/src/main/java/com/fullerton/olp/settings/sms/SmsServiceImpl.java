package com.fullerton.olp.settings.sms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("smsService")
public class SmsServiceImpl implements SmsService
{
	
	@Value("${sms.gateway.host}")
	private String API_HOST;
	
	@Value("${sms.gateway.usrename}")
	private String USERNAME;
	
	@Value("${sms.gateway.password}")
	private String PASSWORD;
	
	@Value("${sms.gateway.senderid}")
	private String SENDER_ID;
	
	private String ROUTE = "T";
	  
  @Override
  public void send( String to, String body )
  {
   
    	try {
 
			URL url = new URL(getURLPath().replace("MOBILENUMBER", to)+body);
			final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			int responseCode = conn.getResponseCode();
			//TODO check response code and decide the logic
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		 
     
    
  }
  

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SmsServiceImpl test = new SmsServiceImpl();
		test.send( "9890098900", "Test sms from gh. Thanks for choosing our service - ghj - SMSC Platform");
	}
	 

	private String getURLPath() {
		String twar = API_HOST + "httpapi/send?username="+USERNAME+"&password="+PASSWORD+"&sender_id="+SENDER_ID+"&route="+ROUTE+"&phonenumber=MOBILENUMBER&message=";
		return twar;
	}
  
}

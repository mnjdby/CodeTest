package com.fullerton.olp.settings.configuration;

import java.io.IOException;

import javax.xml.transform.TransformerException;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceMessageExtractor;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.support.MarshallingUtils;

public class WebServiceTemplateExtension extends WebServiceTemplate{

	
	public WebServiceTemplateExtension() {
		super();
	}
	
	public WebServiceTemplateExtension(Marshaller marshaller) {
		super(marshaller);
	}
	
	@Override
	public Object marshalSendAndReceive(String uri,
										final Object requestPayload,
										final WebServiceMessageCallback requestCallback) {
		return sendAndReceive(uri, new WebServiceMessageCallback() {

			public void doWithMessage(WebServiceMessage request) throws IOException, TransformerException {
				if (requestPayload != null) {
					Marshaller marshaller = getMarshaller();
					if (marshaller == null) {
						throw new IllegalStateException(
								"No marshaller registered. Check configuration of WebServiceTemplate.");
					}
					
					marshaller.marshal(requestPayload, request.getPayloadResult());
					
					if (requestCallback != null) {
						requestCallback.doWithMessage(request);
					}
				}
			}
		}, new WebServiceMessageExtractor<Object>() {

			public Object extractData(WebServiceMessage response) throws IOException {
				Unmarshaller unmarshaller = getUnmarshaller();
				if (unmarshaller == null) {
					throw new IllegalStateException(
							"No unmarshaller registered. Check configuration of WebServiceTemplate.");
				}
				return MarshallingUtils.unmarshal(unmarshaller, response);
			}
		});
	}
	
}

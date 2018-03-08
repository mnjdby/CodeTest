package com.fullerton.olp.settings.configuration;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;

import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.oxm.mime.MimeContainer;

public class Jaxb2MarshallerExtension extends Jaxb2Marshaller{

	@Override
	public void marshal(Object graph, Result result, MimeContainer mimeContainer) throws XmlMappingException {
		try {
			Marshaller marshaller = createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty("jaxb.encoding", "Unicode");
			marshaller.setProperty("com.sun.xml.bind.marshaller.CharacterEscapeHandler",
	                new CDataAwareUtfEncodedXmlCharacterEscapeHandler());

           
			marshaller.marshal(graph, result);
			
		}
		catch (JAXBException ex) {
			throw convertJaxbException(ex);
		}
	}
}

/*Wrapper, Objeto pra transformar em XML uma lista tipo ArrayList*/



package br.petservice.utils;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;

import br.petservice.domain.Dog;
import br.petservice.domain.ListaDogs;

public class JAXBUtil {
     private static JAXBUtil instance;
     private static JAXBContext context;
     
     public static JAXBUtil getInstance(){
    	 return instance;
     }
     static{
    	 try{
    		 context = JAXBContext.newInstance(ListaDogs.class, Dog.class);
    	 }catch (JAXBException e) {
    		 throw new RuntimeException(e);
		}
     }
     public static String toXML(Object object) throws IOException{
    	 try{
    		 StringWriter writer = new StringWriter();
    		 Marshaller m = context.createMarshaller();
    		 m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    		 m.marshal(object, writer);
    		 String xml = writer.toString();
    		 return xml;
    	 }catch (JAXBException e) {
    		 e.printStackTrace();
    		 return null;
			// TODO: handle exception
		 }
    	 
     }
 	public static String toJSON(Object object) throws IOException {
		try {
			StringWriter writer = new StringWriter();
			Marshaller m = context.createMarshaller();
			MappedNamespaceConvention con = new MappedNamespaceConvention();
			XMLStreamWriter xmlStreamWriter = new MappedXMLStreamWriter(con, writer);
			m.marshal(object, xmlStreamWriter);
			String json = writer.toString();
			return json;
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}

}

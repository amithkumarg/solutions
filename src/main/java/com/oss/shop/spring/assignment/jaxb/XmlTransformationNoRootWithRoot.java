package com.oss.shop.spring.assignment.jaxb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;

//@SpringBootApplication //uncomment if you wanna run Jaxb2Marshaller example
public class XmlTransformationNoRootWithRoot {

    public static void main(String[] args) {
        SpringApplication.run(XmlTransformationNoRootWithRoot.class, args);
    }

}

@Component
class TestTransformation implements CommandLineRunner {

    public void run(String[] args) {

        //no root
        //marshalling
        String testXML = marshallXmlWithNoRoot(new Partner("HelloWorld"));
        System.out.println("Marshalled : " + testXML);

        //unmarshalling
        System.out.println("Unmarshalled : " + unmarshallXmlWithNoRoot(testXML, Partner.class));

        //with root
        //marshalling
        testXML = marshallXmlWithRoot(new PartnerRequest("HelloWorld"));
        System.out.println("Marshalled : " + testXML);

        //unmarshalling
        System.out.println("Unmarshalled : " + unmarshallXmlWithRoot(testXML));
    }

    @SuppressWarnings("unchecked")
    private <T> String marshallXmlWithNoRoot(T object) {
        StringWriter sw = new StringWriter();
        Result result = new StreamResult(sw);
        jaxb2Marshaller().marshal(new JAXBElement(
                new QName(object.getClass().getPackage().getName(), object.getClass().getSimpleName()),
                object.getClass(), object), result);
        return sw.toString();
    }

    @SuppressWarnings("unchecked")
    private <T> T unmarshallXmlWithNoRoot(String source, Class<T> clazz) {
        Jaxb2Marshaller marshaller = jaxb2Marshaller();
        //this line voids singleton use of Jaxb2Marshaller
        marshaller.setMappedClass(clazz);
        return (T) marshaller.unmarshal(new StreamSource(new StringReader(source)));
    }

    private String marshallXmlWithRoot(Object object) {
        StringWriter sw = new StringWriter();
        Result result = new StreamResult(sw);
        jaxb2Marshaller().marshal(object, result);
        return sw.toString();
    }

    private Object unmarshallXmlWithRoot(String source) {
        Jaxb2Marshaller marshaller = jaxb2Marshaller();
        return marshaller.unmarshal(new StreamSource(new StringReader(source)));
    }

    @SuppressWarnings("unchecked")
    private Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setMarshallerProperties(new HashMap() {{
            put(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);
        }});
        marshaller.setClassesToBeBound(Partner.class, PartnerRequest.class);
        return marshaller;
    }
}

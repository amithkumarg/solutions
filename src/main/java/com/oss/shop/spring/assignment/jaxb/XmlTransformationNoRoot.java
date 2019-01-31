package com.oss.shop.spring.assignment.jaxb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;

//@SpringBootApplication //uncomment if you wanna run Jaxb2Marshaller example
public class XmlTransformationNoRoot {

    public static void main(String[] args) {
        SpringApplication.run(XmlTransformationNoRoot.class, args);
    }

}

@Component
class TestTransformation implements CommandLineRunner {

    public void run(String[] args) throws JAXBException {

        //marshalling
        String testXML = marshallXml(new Partner("HelloWorld"));
        System.out.println("Marshalled : " + testXML);

        //unmarshalling
        System.out.println("Unmarshalled : " + unmarshallXml(testXML, Partner.class));
    }

    public <T> String marshallXml(T object) throws JAXBException {
        StringWriter sw = new StringWriter();
        Result result = new StreamResult(sw);
        jaxb2Marshaller().marshal(new JAXBElement(
                new QName(object.getClass().getPackage().getName(), object.getClass().getSimpleName()),
                object.getClass(), object), result);
        return sw.toString();
    }

    @SuppressWarnings("unchecked")
    public <T> T unmarshallXml(String source, Class<T> clazz) throws JAXBException {
        Jaxb2Marshaller marshaller = jaxb2Marshaller();
        //this line voids singleton use of Jaxb2Marshaller
        marshaller.setMappedClass(clazz);
        return (T) marshaller.unmarshal(new StreamSource(new StringReader(source)));
    }

    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setMarshallerProperties(new HashMap() {{
            put(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);
        }});
        marshaller.setClassesToBeBound(new Class[]{Partner.class});
        return marshaller;
    }
}

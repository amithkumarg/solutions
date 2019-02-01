package com.oss.shop.spring.assignment.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(
        name = "PartnerRequest"
)
public class PartnerRequest {
    private String name;

    public PartnerRequest(){};

    public PartnerRequest(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "PartnerRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}

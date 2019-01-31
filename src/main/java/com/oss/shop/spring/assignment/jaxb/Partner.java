package com.oss.shop.spring.assignment.jaxb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Partner")
public class Partner {
    private String name;

    public Partner(){};

    public Partner(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Partner{" +
                "name='" + name + '\'' +
                '}';
    }
}

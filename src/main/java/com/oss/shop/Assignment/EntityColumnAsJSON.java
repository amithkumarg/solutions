package com.oss.shop.Assignment;

import java.io.Serializable;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EntityColumnAsJSON {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}


@Entity
@Table(name = "Topic")
class Topic implements Serializable {
	@Id
	@JsonProperty("id")
	@Column(name = "id")
	int id;

	@Column(name = "name")
	@JsonProperty("name")
	String name;

	@Column(name = "content")
	@JsonProperty("content")
	@Convert(converter = ContentToJson.class)
	Content content;
}

class Content implements Serializable{
	@JsonProperty("abcs")
     List<Element> abc;
	@JsonProperty("xyzs")
     List<String> xyz;
}


class Element implements Serializable{
	@JsonProperty("a")
    String a;
	@JsonProperty("a")
    String b;
}


@Convert
class ContentToJson implements AttributeConverter<Content, String> {

	@Override
	public String convertToDatabaseColumn(Content attribute) {
		try {
			return new ObjectMapper().writeValueAsString(attribute);
		} catch (Exception e) {
			//log error
			return null;
		}

	}

	@Override
	public Content convertToEntityAttribute(String dbData) {
		try {
			return new ObjectMapper().readValue(dbData, Content.class);
		} catch (Exception e) {
			//log error
			return null;
		}
	}

}

package com.oss.shop.assignment;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class JsonArrayParse {

    public static void main(String[] args){
        //String testJson = "[ { \"Name\": \"jdbc.connection.driver_class\", \"Value\": \"com.mysql.jdbc.Driver\" }, { \"Name\": \"password\", \"Value\": \"abc\" }, { \"Name\": \"url\", \"Value\": \"jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false\" }, { \"Name\": \"username\", \"Value\": \"abc\" } ]";
        System.out.println(args[0]);
        parseJson(args[0]);
    }

    private static void parseJson(String dbConnectionValues) {

        try {

            List<DatabaseConfiguration> list = new ObjectMapper().readValue(dbConnectionValues,
                    TypeFactory.defaultInstance().constructCollectionType(List.class, DatabaseConfiguration.class));

            for (DatabaseConfiguration l : list) {
                String key = l.getName();
                String value = l.getValue();
                System.out.println("key:" + key + "value:" + value);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}

class DatabaseConfiguration {

    @JsonProperty("Name")
    String name;
    @JsonProperty("Value")
    String value;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
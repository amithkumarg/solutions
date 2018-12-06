package com.oss.shop.assignment;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.*;

public class ReadGenericJSON {

    public static void main(String[] args) throws Exception {
        System.out.println(readFileAsString("data.json"));
        System.out.println(readFileAsJSON("data.json"));
    }


    public static JsonObject readFileAsJSON(String filePath) throws Exception {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(ReadGenericJSON.class.getClassLoader().getResource(filePath).getFile()));
        return gson.fromJson(reader, JsonObject.class);
    }

    public static String readFileAsString(String filePath)
            throws IOException {
        InputStream fileInputStream = new FileInputStream(ReadGenericJSON.class.getClassLoader().getResource(filePath).getFile());
        byte[] buffer = new byte[fileInputStream.available()];
        int length = fileInputStream.read(buffer);
        fileInputStream.close();
        return new String(buffer, 0, length);
    }
}

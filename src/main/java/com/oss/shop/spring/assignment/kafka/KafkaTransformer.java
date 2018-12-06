package com.oss.shop.spring.assignment.kafka;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import java.io.File;
import java.util.Map;
import java.util.Properties;

public class KafkaTransformer {

    public static void main(String[] args) throws Exception{
        //define properties
        Properties props=new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //locate file
        File file = new File(KafkaTransformer.class.getClassLoader().getResource("data.json").getFile());

        //stream data as key value pair
        KafkaProducer<String,String> sampleProducer= new KafkaProducer<String,String>(props);
        readJsonFileAsMap(file).forEach((k,v)->{
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("test",k,v);
            sampleProducer.send(record);
        });
        sampleProducer.close();
    }


    private static Map<String, String> readJsonFileAsMap(File file) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, new TypeReference<Map<String,String>>(){});
    }

}

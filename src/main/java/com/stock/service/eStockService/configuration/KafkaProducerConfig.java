package com.stock.service.eStockService.configuration;


import com.stock.service.eStockService.model.entity.MongoDBEntity.StockMongoEntity;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import java.util.HashMap;
import java.util.Map;
//
//@Configuration
//public class KafkaProducerConfig {
//
//    @Value("${spring.kafka.bootstrap-servers}")
//    private String kafkaServers;
//
//
//    @Bean
//    public ProducerFactory<String,StockMongoEntity> myKafkaProdFactory(){
//        Map<String,Object> propertyDetails=new HashMap<>();
//        propertyDetails.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaServers);
//        propertyDetails.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        propertyDetails.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class);
//        return new DefaultKafkaProducerFactory<>(propertyDetails);
//    }
//
//    @Bean
//    public KafkaTemplate<String, StockMongoEntity> getKafkaTemplate(){
//        return new KafkaTemplate<>(myKafkaProdFactory());
//    }
//}

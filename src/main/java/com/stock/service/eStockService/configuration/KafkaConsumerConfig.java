package com.stock.service.eStockService.configuration;

import com.stock.service.eStockService.model.entity.MongoDBEntity.StockMongoEntity;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {


    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaBootstrapServers;


    @Bean
    public ConsumerFactory<String, StockMongoEntity> myKafkaConsumerFactory(){
        Map<String,Object> propertyDetails=new HashMap<>();
        propertyDetails.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaBootstrapServers);
        propertyDetails.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propertyDetails.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(propertyDetails,new StringDeserializer(),
                new JsonDeserializer<>(StockMongoEntity.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,StockMongoEntity> getConsumerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,StockMongoEntity> factory=new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(myKafkaConsumerFactory());
        return factory;


    }
}

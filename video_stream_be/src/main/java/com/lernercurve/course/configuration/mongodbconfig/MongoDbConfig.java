package com.lernercurve.course.configuration.mongodbconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoDbConfig {

    // @Bean
    // @Primary
    // MongoClient mongoClient() {
    // MongoClientSettings settings = MongoClientSettings.builder()
    // .applyToConnectionPoolSettings(builder -> builder
    // .applyConnectionString(new
    // ConnectionString("mongodb://ppanda:ppanda@localhost:27017")))
    // .build();
    // return MongoClients.create(settings);
    // }

    // @Bean
    // @Primary
    // MongoTemplate mongoTemplate(MongoClient mongoClient) {
    // return new MongoTemplate(mongoClient, "postit");
    // }

    // @Bean
    // @Primary
    // MongoDatabaseFactory mongoDbFactory() throws Exception {
    // return new SimpleMongoClientDatabaseFactory(mongoClient(), "postit");
    // }

    // @Bean
    // @Primary
    // MongoTransactionManager transactionManager(MongoDatabaseFactory
    // mongoDatabaseFactory) {
    // return new MongoTransactionManager(mongoDatabaseFactory);
    // }
}

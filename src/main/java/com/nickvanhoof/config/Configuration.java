package com.nickvanhoof.config;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

public class Configuration {

    @Produces
    @Singleton
    public Gson gson(){
        return new GsonBuilder().create();
    }

    @Produces
    @Singleton
    public DynamoDBMapper dynamoDBMapper(){
        AmazonDynamoDB amazonDynamoDBClient = AmazonDynamoDBClientBuilder
                .standard()
                .withRegion("eu-west-1")
                .build();
        return new DynamoDBMapper(amazonDynamoDBClient);
    }
}

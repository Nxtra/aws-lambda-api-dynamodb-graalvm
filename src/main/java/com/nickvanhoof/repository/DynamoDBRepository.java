package com.nickvanhoof.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.nickvanhoof.dao.MessageDao;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class DynamoDBRepository {

    private static final String REGION = "eu-west-1";


    private DynamoDBMapper dynamoDBMapper;

    public DynamoDBRepository(){
        AmazonDynamoDB amazonDynamoDBClient = AmazonDynamoDBClientBuilder
                .standard()
                .withRegion(REGION)
                .build();
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDBClient);
    }

    public Optional<MessageDao> getOneMessageById(String id){
        MessageDao messageDao = new MessageDao.Builder().id(id).build();
        DynamoDBQueryExpression<MessageDao> queryExpression = new DynamoDBQueryExpression<MessageDao>()
                .withHashKeyValues(messageDao)
                .withConsistentRead(false)
                .withLimit(1);

        List<MessageDao> queryResult = dynamoDBMapper.query(MessageDao.class, queryExpression);
        if(queryResult.isEmpty()){
            log.info("No result found using inconsistent read");
            log.info("Trying a consistent read");
            queryExpression = new DynamoDBQueryExpression<MessageDao>()
                    .withHashKeyValues(messageDao)
                    .withConsistentRead(true)
                    .withLimit(1);
            queryResult = dynamoDBMapper.query(MessageDao.class, queryExpression);
        }
        return queryResult.stream().findAny();
    }
}
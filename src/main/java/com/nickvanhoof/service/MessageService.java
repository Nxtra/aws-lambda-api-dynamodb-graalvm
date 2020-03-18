package com.nickvanhoof.service;

import com.nickvanhoof.dao.MessageDao;
import com.nickvanhoof.repository.DynamoDBRepository;

import java.util.Optional;

public class MessageService {

    private DynamoDBRepository dynamoDBRepository;

    public MessageService() {
        dynamoDBRepository = new DynamoDBRepository();
    }

    public MessageDao handleGetMessageRequest(String uuid) {
        return getMessageDao(uuid);
    }

    private MessageDao getMessageDao(String uuid) {
        Optional<MessageDao> messageDaoOpt = dynamoDBRepository.getOneMessageById(uuid);
        if(messageDaoOpt.isEmpty()){

        }
        return messageDaoOpt.get();
    }


}

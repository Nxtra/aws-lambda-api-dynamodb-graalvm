package com.nickvanhoof.service;

import com.nickvanhoof.dao.MessageDao;
import com.nickvanhoof.repository.DynamoDBRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
public class MessageService {

    private DynamoDBRepository dynamoDBRepository;

    @Inject
    public MessageService(DynamoDBRepository dynamoDBRepository) {
        this.dynamoDBRepository = dynamoDBRepository;
    }

    public MessageDao handleGetMessageRequest(String uuid) {
        return getMessageDao(uuid);
    }

    private MessageDao getMessageDao(String uuid) {
        Optional<MessageDao> messageDaoOpt = dynamoDBRepository.getOneMessageById(uuid);
        if(!messageDaoOpt.isPresent()){

        }
        return messageDaoOpt.get();
    }


}

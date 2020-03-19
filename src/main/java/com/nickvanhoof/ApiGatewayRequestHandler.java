package com.nickvanhoof;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.nickvanhoof.dao.MessageDao;
import com.nickvanhoof.service.MessageService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@Named("myHandler")
public class ApiGatewayRequestHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Inject
    private Gson gson;
    @Inject
    private MessageService messageService;

    public ApiGatewayRequestHandler() {}

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        MessageDao messageDao = messageService.handleGetMessageRequest(input.getPathParameters().get("uuid"));

        return new APIGatewayProxyResponseEvent()
                .withStatusCode(201)
                .withHeaders(Map.of("X-Powered-By", "AWS-Lambda", "X-Created-By", "https://twitter.com/TheNickVanHoof"))
                .withBody(messageToJson(messageDao));
    }

    private String messageToJson(MessageDao message) {
        return gson.toJson(message);
    }


}
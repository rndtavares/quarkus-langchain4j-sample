package com.code.ai;

import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;
import io.quarkus.websockets.next.WebSocketConnection;
import jakarta.inject.Inject;

import java.util.Objects;
import java.util.UUID;

@WebSocket(path = "/ws/{interactionId}")
public class AgentWSEndpoint {

    private final Agent agent;

    private final WebSocketConnection connection;

    @Inject
    AgentWSEndpoint(Agent agent, WebSocketConnection connection) {
        this.agent = agent;
        this.connection = connection;
    }

    @OnTextMessage
    String reply(String message) {
        var interactionId = connection.pathParam("interactionId");
        return agent.chat(
                Objects.isNull(interactionId) || interactionId.isBlank()
                        ? UUID.randomUUID().toString()
                        : interactionId,
                message
        );
    }

}

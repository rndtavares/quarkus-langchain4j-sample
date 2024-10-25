package com.code.ai;

import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;

@WebSocket(path = "/ws")
public class AgentWSEndpoint {

    private final Agent agent;

    AgentWSEndpoint(Agent agent) {
        this.agent = agent;
    }

    @OnTextMessage
    String reply(String message) {
        return agent.chat(message);
    }

}

package com.code.ai;

import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;

@WebSocket(path = "/ws")
public class BotWSEndpoint {

    private final Bot bot;

    BotWSEndpoint(Bot bot) {
        this.bot = bot;
    }

    @OnTextMessage
    String reply(String message) {
        return bot.chat(message);
    }

}

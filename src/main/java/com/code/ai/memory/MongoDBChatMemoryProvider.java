package com.code.ai.memory;

import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;

import java.util.function.Supplier;

public class MongoDBChatMemoryProvider implements Supplier<ChatMemoryProvider> {

    private MongoDBChatMemoryStore mongoDBChatMemoryStore = new MongoDBChatMemoryStore();

    @Override
    public ChatMemoryProvider get() {
        return memoryId -> MessageWindowChatMemory.builder()
                .maxMessages(100)
                .id(memoryId)
                .chatMemoryStore(mongoDBChatMemoryStore)
                .build();
    }
}

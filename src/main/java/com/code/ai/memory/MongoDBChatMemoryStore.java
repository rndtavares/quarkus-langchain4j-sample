package com.code.ai.memory;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;

import java.util.List;
import java.util.Objects;

public class MongoDBChatMemoryStore implements ChatMemoryStore {

    private InteractionRepository interactionRepository = new InteractionRepository();

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        var interactionEntity = interactionRepository.findByInteractionId(memoryId.toString());
        return Objects.isNull(interactionEntity) ? List.of() : interactionEntity.getMessages();
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        interactionRepository.updateMessages(memoryId.toString(), messages);
    }

    @Override
    public void deleteMessages(Object memoryId) {
        interactionRepository.deleteMessages(memoryId.toString());
    }
}

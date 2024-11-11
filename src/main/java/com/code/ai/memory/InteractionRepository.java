package com.code.ai.memory;

import dev.langchain4j.data.message.ChatMessage;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;

import java.util.List;

public class InteractionRepository implements PanacheMongoRepositoryBase<InteractionEntity, String> {

    public InteractionEntity findByInteractionId(String interactionId) {
        return findById(interactionId);
    }

    public void updateMessages(String interactionId, List<ChatMessage> messages) {
        persistOrUpdate(new InteractionEntity(interactionId, messages));
    }

    public void deleteMessages(String interactionId) {
        deleteById(interactionId);
    }

}

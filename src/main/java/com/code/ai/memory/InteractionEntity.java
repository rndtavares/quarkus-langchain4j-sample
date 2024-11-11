package com.code.ai.memory;

import dev.langchain4j.data.message.ChatMessage;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonId;

import java.util.List;
import java.util.Objects;

@MongoEntity(collection = "interactions")
public class InteractionEntity {

    @BsonId
    private String interactionId;
    private List<ChatMessage> messages;

    public InteractionEntity() {
    }

    public InteractionEntity(String interactionId, List<ChatMessage> messages) {
        this.interactionId = interactionId;
        this.messages = messages;
    }

    public String getInteractionId() {
        return interactionId;
    }

    public void setInteractionId(String interactionId) {
        this.interactionId = interactionId;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InteractionEntity that = (InteractionEntity) o;
        return Objects.equals(interactionId, that.interactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(interactionId, messages);
    }
}

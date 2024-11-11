package com.code.ai.memory;

import dev.langchain4j.data.message.ChatMessage;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

public class ChatMessageCodecProvider implements CodecProvider {

    @Override
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        if (clazz == ChatMessage.class) {
            return (Codec<T>) new ChatMessageCodec();
        }
        return null;
    }

}

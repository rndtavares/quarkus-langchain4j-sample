package com.code.ai.memory;

import com.mongodb.MongoClientSettings;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageJsonCodec;
import io.quarkiverse.langchain4j.QuarkusChatMessageJsonCodecFactory;
import org.bson.BsonReader;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class ChatMessageCodec implements CollectibleCodec<ChatMessage> {

    private final Codec<Document> documentCodec;
    private final ChatMessageJsonCodec chatMessageJsonCodec;

    public ChatMessageCodec() {
        this.documentCodec = MongoClientSettings.getDefaultCodecRegistry().get(Document.class);
        this.chatMessageJsonCodec = new QuarkusChatMessageJsonCodecFactory().create();
    }

    @Override
    public ChatMessage generateIdIfAbsentFromDocument(ChatMessage document) {
        return document;
    }

    @Override
    public boolean documentHasId(ChatMessage document) {
        return false;
    }

    @Override
    public BsonValue getDocumentId(ChatMessage document) {
        return null;
    }

    @Override
    public ChatMessage decode(BsonReader reader, DecoderContext decoderContext) {
        var document = documentCodec.decode(reader, decoderContext);
        return this.chatMessageJsonCodec.messageFromJson(document.toJson());
    }

    @Override
    public void encode(BsonWriter writer, ChatMessage value, EncoderContext encoderContext) {
        var json = this.chatMessageJsonCodec.messageToJson(value);
        var doc = Document.parse(json);
        documentCodec.encode(writer, doc, encoderContext);
    }

    @Override
    public Class<ChatMessage> getEncoderClass() {
        return ChatMessage.class;
    }
}

package com.code.ai.guardrails;

import dev.langchain4j.data.message.UserMessage;
import io.quarkiverse.langchain4j.guardrails.InputGuardrail;
import io.quarkiverse.langchain4j.guardrails.InputGuardrailResult;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AgentPromptInjectionGuardrail implements InputGuardrail {

    private final AgentPromptInjectionDetectionService service;

    public AgentPromptInjectionGuardrail(AgentPromptInjectionDetectionService service) {
        this.service = service;
    }

    @Override
    public InputGuardrailResult validate(UserMessage userMessage) {
        double result = service.isInjection(userMessage.singleText());
        if (result > 0.75) {
            return failure("Prompt injection detected");
        }
        return success();
    }
}

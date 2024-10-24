package com.code.ai;

import com.code.ai.guardrails.BotPromptInjectionGuardrail;
import com.code.ai.guardrails.HallucinationGuard;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.ToolBox;
import io.quarkiverse.langchain4j.guardrails.InputGuardrails;
import io.quarkiverse.langchain4j.guardrails.OutputGuardrails;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RegisterAiService
public interface Bot {

    @SystemMessage("""
            Você é um agente especializado em futebol brasileiro, seu nome é FutAgentBR
            Você sabe responder sobre os principais títulos dos principais times brasileiros e da seleção brasileira
            Sua resposta precisa ser educada, você pode deve responder em Português brasileiro e de forma relevante a pergunta feita

            Quando você não souber a resposta, responda que você não sabe responder nesse momento mas saberá em futuras versões.
            """)
    @ToolBox(BotTools.class)
    @InputGuardrails(BotPromptInjectionGuardrail.class)
    @OutputGuardrails(HallucinationGuard.class)
    String chat(@UserMessage String message);
}
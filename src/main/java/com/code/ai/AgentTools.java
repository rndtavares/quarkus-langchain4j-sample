package com.code.ai;

import dev.langchain4j.agent.tool.Tool;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.time.LocalTime;

@ApplicationScoped
public class AgentTools {

    @Tool
    LocalDate currentDate() {
        System.out.println("Called currentDate()");
        return LocalDate.now();
    }

    @Tool
    LocalTime currentTime() {
        System.out.println("Called currentTime()");
        return LocalTime.now();
    }

    @Tool("Calcula a soma de dois números")
    int add(int a, int b) {
        System.out.println("Called add with a=" + a + ", b=" + b);
        return a + b;
    }

    @Tool("Calcula a raiz quadrada de um número")
    double sqrt(int x) {
        System.out.println("Called sqrt with x=" + x);
        return Math.sqrt(x);
    }
}

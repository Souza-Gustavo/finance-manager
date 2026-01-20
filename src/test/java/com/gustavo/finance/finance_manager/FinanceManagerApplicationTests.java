package com.gustavo.finance.finance_manager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
@Import(FinanceManagerApplicationTests.TestSecurityConfig.class)
class FinanceManagerApplicationTests {

    @Test
    void contextLoads() {
        // Teste simples apenas para verificar se o ApplicationContext carrega
    }

    // Configuração de segurança falsa apenas para testes
    static class TestSecurityConfig {
        @Bean
        public SecurityFilterChain securityFilterChain() {
            return http -> http.build(); // apenas retorna um builder vazio
        }
    }
}


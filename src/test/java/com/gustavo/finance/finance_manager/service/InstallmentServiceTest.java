package com.gustavo.finance.finance_manager.service;

import com.gustavo.finance.domain.entities.Installment;
import com.gustavo.finance.domain.repositories.InstallmentRepository;
import com.gustavo.finance.domain.services.InstallmentService;
import com.gustavo.finance.domain.entities.User;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class InstallmentServiceTest {

    @Mock
    private InstallmentRepository installmentRepository;

    @InjectMocks
    private InstallmentService installmentService;

    @Test
    void deveListarParcelasDoUsuario() {
    User user = new User();

    Installment installment = new Installment();
    installment.setUser(user);
    when(installmentRepository.findByUser(user))
            .thenReturn(List.of(installment));

    List<Installment> result = installmentService.listarDoUsuario(user);

    assertThat(result).hasSize(1);

    }
}



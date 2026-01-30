package com.gustavo.finance.domain.services;

import com.gustavo.finance.application.exceptions.ResourceNotFoundException;
import com.gustavo.finance.application.exceptions.AccessDeniedException;
import com.gustavo.finance.application.exceptions.BusinessException;

import java.math.RoundingMode;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gustavo.finance.domain.repositories.CategoryRepository;
import com.gustavo.finance.domain.repositories.InstallmentParcelRepository;
import com.gustavo.finance.domain.repositories.InstallmentRepository;
import com.gustavo.finance.domain.entities.Category;
import com.gustavo.finance.domain.entities.Installment;
import com.gustavo.finance.domain.entities.InstallmentParcel;
import com.gustavo.finance.domain.entities.User;
import com.gustavo.finance.domain.enums.InstallmentStatus;

import com.gustavo.finance.application.dto.InstallmentParcelResponseDTO;
import com.gustavo.finance.application.dto.InstallmentRequestDTO;

@Service
public class InstallmentService {

    private final InstallmentRepository installmentRepository;
    private final InstallmentParcelRepository parcelRepository;
    private final CategoryRepository categoryRepository;

    public InstallmentService(
            InstallmentRepository installmentRepository,
            InstallmentParcelRepository parcelRepository,
            CategoryRepository categoryRepository
    ) {
        this.installmentRepository = installmentRepository;
        this.parcelRepository = parcelRepository;
        this.categoryRepository = categoryRepository;
    }

    // Listar todos os parcelamentos de um usuário
    public List<Installment> listarDoUsuario(User user) {
        return installmentRepository.findByUser(user);
    }

    // Criar um novo parcelamento
    public Installment criar(InstallmentRequestDTO dto, User user) {

        Category category = null;

        if (dto.getCategoryId() != null) {
            category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

            if (!category.getUser().getId().equals(user.getId())) {
                throw new AccessDeniedException("Categoria não pertence ao usuário");
            }
        }

        Installment installment = new Installment();
        installment.setDescription(dto.getDescription());
        installment.setTotalAmount(dto.getTotalAmount());
        installment.setTotalParcels(dto.getTotalParcels());
        installment.setStartDate(dto.getStartDate());
        installment.setUser(user);
        installment.setCategory(category);
        installment.setStatus(InstallmentStatus.ACTIVE); // Status inicial

        Installment salvo = installmentRepository.save(installment);

        // Criar parcelas
        BigDecimal valorParcela = dto.getTotalAmount()
                .divide(BigDecimal.valueOf(dto.getTotalParcels()), 2, RoundingMode.HALF_UP);

        for (int i = 1; i <= dto.getTotalParcels(); i++) {
            InstallmentParcel parcel = new InstallmentParcel();
            parcel.setParcelNumber(i);
            parcel.setAmount(valorParcela);
            parcel.setDueDate(dto.getStartDate().plusMonths(i - 1));
            parcel.setInstallment(salvo);

            parcelRepository.save(parcel);
        }

        return salvo;
    }

    // Listar parcelas de um parcelamento específico
    public List<InstallmentParcelResponseDTO> listarParcelas(Long installmentId, User user) {

        Installment installment = installmentRepository.findById(installmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Parcelamento não encontrado"));

        if (!installment.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("Acesso negado");
        }

        return parcelRepository.findByInstallment(installment)
                .stream()
                .map(parcel -> {
                    InstallmentParcelResponseDTO dto = new InstallmentParcelResponseDTO();
                    dto.setId(parcel.getId());
                    dto.setParcelNumber(parcel.getParcelNumber());
                    dto.setAmount(parcel.getAmount());
                    dto.setDueDate(parcel.getDueDate());
                    dto.setPaid(parcel.isPaid());
                    dto.setPaymentDate(parcel.getPaymentDate());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // Marcar uma parcela como paga
    public void marcarParcelaComoPaga(Long parcelId, User user) {

        InstallmentParcel parcel = parcelRepository.findById(parcelId)
                .orElseThrow(() -> new ResourceNotFoundException("Parcela não encontrada"));

        Installment installment = parcel.getInstallment();

        if (!installment.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("Acesso negado");
        }

        if (parcel.isPaid()) {
            throw new BusinessException("Parcela já está paga");
        }

        parcel.setPaid(true);
        parcel.setPaymentDate(LocalDate.now());
        parcelRepository.save(parcel);

        // Atualizar status do parcelamento
        boolean existeParcelaAberta = parcelRepository.existsByInstallmentAndPaidFalse(installment);

        if (!existeParcelaAberta) {
            installment.setStatus(InstallmentStatus.FINISHED);
        } else {
            installment.setStatus(InstallmentStatus.ACTIVE);
        }

        installmentRepository.save(installment);
    }

    // Listar todos os parcelamentos ativos de um usuário
    public List<Installment> listarAtivosDoUsuario(User user) {
        return installmentRepository.findByUserAndStatus(user, InstallmentStatus.ACTIVE);
    }

    public void excluir(Long installmentId, User user) {
        Installment installment = installmentRepository.findById(installmentId)
            .orElseThrow(() -> new ResourceNotFoundException("Parcelamento não encontrado"));

        if (!installment.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("Acesso negado");
        }

    // Excluir parcelas primeiro
    parcelRepository.deleteByInstallment(installment);

    // Excluir o parcelamento
    installmentRepository.delete(installment);
    }

}

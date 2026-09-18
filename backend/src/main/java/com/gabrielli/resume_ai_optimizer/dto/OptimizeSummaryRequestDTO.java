package com.gabrielli.resume_ai_optimizer.dto;

import jakarta.validation.constraints.NotBlank;

public record OptimizeSummaryRequestDTO(
        @NotBlank(message = "O resumo atual não pode estar vazio")
        String currentSummary,

        @NotBlank(message = "A descrição da vaga não pode estar vazia")
        String jobDescription
) { }

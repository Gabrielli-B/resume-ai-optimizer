package com.gabrielli.resume_ai_optimizer.dto;

import jakarta.validation.constraints.NotBlank;

public record AnalysisRequestDTO(
    @NotBlank(message = "O texto do currículo não pode estar vazio")
    String resumeText,

    @NotBlank(message="A descrição da vaga não pode estar vazia")
    String jobDescription
) { }

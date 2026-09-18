package com.gabrielli.resume_ai_optimizer.dto;

import java.util.List;

public record AnalysisResponseDTO(
        int matchScore,
        List<String> matchingSkills,
        List<String> missingSkills,
        List<String> improvementPoints,
        String optmizedSummary
) { }

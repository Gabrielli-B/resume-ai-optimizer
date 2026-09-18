package com.gabrielli.resume_ai_optimizer.controller;

import com.gabrielli.resume_ai_optimizer.dto.AnalysisRequestDTO;
import com.gabrielli.resume_ai_optimizer.dto.AnalysisResponseDTO;
import com.gabrielli.resume_ai_optimizer.dto.OptimizeSummaryRequestDTO;
import com.gabrielli.resume_ai_optimizer.dto.OptimizeSummaryResponseDTO;
import com.gabrielli.resume_ai_optimizer.service.ResumeAnalysisService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/resume")
public class ResumeController {
    private final ResumeAnalysisService resumeAnalysisService;

    public ResumeController(ResumeAnalysisService resumeAnalysisService) {
        this.resumeAnalysisService = resumeAnalysisService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<AnalysisResponseDTO> analyze(@Valid @RequestBody AnalysisRequestDTO request){
        return ResponseEntity.ok(resumeAnalysisService.analyze(request));
    }

    @PostMapping("/optimize-summary")
    public ResponseEntity<OptimizeSummaryResponseDTO> optimizeSummary(@Valid @RequestBody OptimizeSummaryRequestDTO request) {
        return ResponseEntity.ok(resumeAnalysisService.optimizeSummary(request));
    }
}

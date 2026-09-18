package com.gabrielli.resume_ai_optimizer.service;

import com.gabrielli.resume_ai_optimizer.dto.AnalysisRequestDTO;
import com.gabrielli.resume_ai_optimizer.dto.AnalysisResponseDTO;
import com.gabrielli.resume_ai_optimizer.dto.OptimizeSummaryRequestDTO;
import com.gabrielli.resume_ai_optimizer.dto.OptimizeSummaryResponseDTO;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.stereotype.Service;

@Service
public class ResumeAnalysisService {
    private final ChatClient chatClient;

    public ResumeAnalysisService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public AnalysisResponseDTO analyze(AnalysisRequestDTO request){
        var outputConverter = new BeanOutputConverter<>(AnalysisResponseDTO.class);
        String format = outputConverter.getFormat();

        String promptText= """
                Atue como um recrutador técnico sênior e especialista em sistemas  ATS (Applicant Tracking System).
                
                Compare o CURRÍCULO abaixo com a DESCRIÇÃO DA VAGA e gere uma análise objetiva.
                
                CURRÍCULO:
                %s
                
                DESCRIÇÃO DA VAGA:
                %s
                
                Responda EXCLUSIVAMENTE no formato JSON abaixo, sem nenhum texto adcional antes ou depois:
                %s
                """.formatted(request.resumeText(),request.jobDescription(),format);

        String rawResponse = chatClient.prompt()
                .user(promptText)
                .call()
                .content();
        return  outputConverter.convert(rawResponse);

    }

    public OptimizeSummaryResponseDTO optimizeSummary(OptimizeSummaryRequestDTO request){
        var outputConverter = new BeanOutputConverter<>(OptimizeSummaryResponseDTO.class);
        String format = outputConverter.getFormat();

        String promptText = """
                Atue como um especialista em copywriting para currículos e recrutamento técnico.

                Reescreva o RESUMO PROFISSIONAL abaixo para que fique alinhado à DESCRIÇÃO DA VAGA,
                destacando palavras-chave relevantes e usando linguagem orientada a resultados.

                RESUMO ATUAL:
                %s

                DESCRIÇÃO DA VAGA:
                %s

                Responda EXCLUSIVAMENTE no formato JSON abaixo, sem nenhum texto adicional antes ou depois:
                %s
                """.formatted(request.currentSummary(), request.jobDescription(), format);

        String rawResponse = chatClient.prompt()
                .user(promptText)
                .call()
                .content();

        return outputConverter.convert(rawResponse);
    }

}

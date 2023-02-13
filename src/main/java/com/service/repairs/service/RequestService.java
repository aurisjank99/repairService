package com.service.repairs.service;

import com.service.repairs.domain.AnalysisRequest;
import com.service.repairs.domain.RepairRequest;
import com.service.repairs.domain.ReplacementRequest;
import com.service.repairs.domain.Request;
import com.service.repairs.domain.repository.RequestRepository;
import com.service.repairs.utils.StringBuilderPlus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;

    @Transactional
    public void createRequest(AnalysisRequest request, StringBuilderPlus errorMessages) {
        try {
            if (errorMessages.toString().isBlank())
                requestRepository.save(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorMessages.appendLine("Could not create work order request for analysis");
        }
    }

    @Transactional
    public void createRequest(RepairRequest request, StringBuilderPlus errorMessages) {
        try {
            if (errorMessages.toString().isBlank())
                requestRepository.save(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorMessages.appendLine("Could not create work order request for repair");
        }
    }

    @Transactional
    public void createRequest(ReplacementRequest request, StringBuilderPlus errorMessages) {
        try {
            if (errorMessages.toString().isBlank())
                requestRepository.save(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorMessages.appendLine("Could not create work order request for replacement");
        }
    }

    public List<Request> getAll() {
        List<Request> requests = new ArrayList<>();
        requestRepository.findAll().forEach(obj -> requests.add(obj));
        return requests;
    }
}

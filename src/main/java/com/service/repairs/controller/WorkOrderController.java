package com.service.repairs.controller;

import com.service.repairs.domain.WorkOrderRequest;
import com.service.repairs.dto.AnalysisRequestDto;
import com.service.repairs.dto.RepairRequestDto;
import com.service.repairs.dto.ReplacementRequestDto;
import com.service.repairs.service.WorkOrderResolveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("work-order")
@RequiredArgsConstructor
public class WorkOrderController {
    private final WorkOrderResolveService workOrderResolveService;

    @PostMapping("analysis")
    String createAnalysisRequest(@RequestBody AnalysisRequestDto request) {
        return workOrderResolveService.createAnalysisRequest(request);
    }

    @PostMapping("repair")
    String createRepairRequest(@RequestBody RepairRequestDto request) {
        return workOrderResolveService.createRepairRequest(request);
    }

    @PostMapping("replacement")
    String createReplacementRequest(@RequestBody ReplacementRequestDto request) {
        return workOrderResolveService.createReplacementRequest(request);
    }

    @GetMapping
    ResponseEntity<List<Object>> getAllRequests(){
        return ResponseEntity.ok(workOrderResolveService.getAllRequests());
    }

    @GetMapping("history")
    ResponseEntity<List<WorkOrderRequest>> getAllRequestsHistory(){
        return ResponseEntity.ok(workOrderResolveService.getAllRequestsHistory());
    }
}

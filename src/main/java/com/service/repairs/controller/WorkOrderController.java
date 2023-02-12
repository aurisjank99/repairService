package com.service.repairs.controller;

import com.service.repairs.dto.AnalysisRequestDto;
import com.service.repairs.dto.RepairRequestDto;
import com.service.repairs.dto.ReplacementRequestDto;
import com.service.repairs.service.WorkOrderResolveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

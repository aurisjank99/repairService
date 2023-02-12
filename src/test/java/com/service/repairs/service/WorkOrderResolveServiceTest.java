package com.service.repairs.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.service.repairs.dto.AnalysisRequestDto;
import com.service.repairs.dto.RepairRequestDto;
import com.service.repairs.dto.ReplacementRequestDto;
import com.service.repairs.utils.StringBuilderPlus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkOrderResolveServiceTest {

    @Autowired
    private WorkOrderResolveService service;

    ObjectMapper mapper = new ObjectMapper()
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(new JavaTimeModule());


    @Test
    public void successfullyCreatesAnalysisRequest() throws IOException {
        AnalysisRequestDto requestDto = mapper.readValue(new File("src/test/resources/json/analysis.json"), AnalysisRequestDto.class);
        assertEquals("Success", service.createAnalysisRequest(requestDto));
    }

    @Test
    public void successfullyCreatesRepairRequest() throws IOException {
        RepairRequestDto requestDto = mapper.readValue(new File("src/test/resources/json/repair.json"), RepairRequestDto.class);
        assertEquals("Success", service.createRepairRequest(requestDto));
    }

    @Test
    public void successfullyCreatesReplacementRequest() throws IOException {
        ReplacementRequestDto requestDto = mapper.readValue(new File("src/test/resources/json/replacement.json"), ReplacementRequestDto.class);
        assertEquals("Success", service.createReplacementRequest(requestDto));
    }

    @Test
    public void repairRequestUsedForAnalysisRequest() throws IOException {
        AnalysisRequestDto requestDto = mapper.readValue(new File("src/test/resources/json/repair.json"), AnalysisRequestDto.class);
        StringBuilderPlus sb = new StringBuilderPlus();
        sb.appendLine("Selected department type is not supported by this operation");
        assertEquals(sb.toString(), service.createAnalysisRequest(requestDto));
    }

    @Test
    public void analysisRequestUsedForRepairRequest() throws IOException {
        RepairRequestDto requestDto = mapper.readValue(new File("src/test/resources/json/analysis.json"), RepairRequestDto.class);
        StringBuilderPlus sb = new StringBuilderPlus();
        sb.appendLine("Selected department type is not supported by this operation");
        sb.appendLine("Test date cannot be empty");
        sb.appendLine("Responsible person cannot be empty");
        assertEquals(sb.toString(), service.createRepairRequest(requestDto));
    }

    @Test
    public void analysisRequestUsedForReplacementRequest() throws IOException {
        ReplacementRequestDto requestDto = mapper.readValue(new File("src/test/resources/json/analysis.json"), ReplacementRequestDto.class);
        StringBuilderPlus sb = new StringBuilderPlus();
        sb.appendLine("Selected department type is not supported by this operation");
        sb.appendLine("Factory name cannot be empty");
        sb.appendLine("Order number should be 10 symbols long, first 2 symbols - letters other numbers");
        assertEquals(sb.toString(), service.createReplacementRequest(requestDto));
    }
}

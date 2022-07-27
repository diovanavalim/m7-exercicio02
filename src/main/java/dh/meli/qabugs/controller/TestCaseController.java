package dh.meli.qabugs.controller;

import dh.meli.qabugs.dto.TestCaseDto;
import dh.meli.qabugs.model.TestCase;
import dh.meli.qabugs.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class TestCaseController<T> {

    @Autowired
    public TestCaseService testCaseService;

    @PostMapping("/testcase")
    public ResponseEntity<TestCaseDto> createTestCase(@RequestBody TestCase testCase) {
        TestCase savedTestCase = testCaseService.createTestCase(testCase);

        return new ResponseEntity<TestCaseDto>(new TestCaseDto(savedTestCase), HttpStatus.OK);
    }

    @GetMapping("/testcase")
    public ResponseEntity<List<TestCaseDto>> getTestCase() {
        List<TestCase> testCaseList = testCaseService.getTestCase();

        List<TestCaseDto> testCaseDtoList = new ArrayList<TestCaseDto>();

        for (TestCase testCase : testCaseList) {
            testCaseDtoList.add(new TestCaseDto(testCase));
        }

        return new ResponseEntity<List<TestCaseDto>>(testCaseDtoList, HttpStatus.OK);
    }

    @GetMapping("/testcase/{id}")
    public ResponseEntity<TestCaseDto> getTestCaseById(@PathVariable long id) {
        TestCase testCase = testCaseService.getTestCaseById(id);

        return new ResponseEntity<TestCaseDto>(new TestCaseDto(testCase), HttpStatus.OK);
    }

    @PatchMapping("/testcase/{id}")
    public ResponseEntity<TestCaseDto> updateTestCase(@PathVariable long id, @RequestBody Map<String, T> data) {
        TestCase updateTestCase = testCaseService.updateTestCase(id, data);

        return new ResponseEntity<TestCaseDto>(new TestCaseDto(updateTestCase), HttpStatus.OK);
    }

    @DeleteMapping("/testcase/{id}")
    public ResponseEntity<Void> deleteTestCase(@PathVariable long id) {
        testCaseService.deleteTestCase(id);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/testcase/last_update/{lastUpdate}")
    public ResponseEntity<List<TestCaseDto>> getTestCaseByLastUpdate(@PathVariable String lastUpdate) {
        List<TestCase> testCaseList = testCaseService.getTestCaseByLastUpdate(lastUpdate);

        List<TestCaseDto> testCaseDtoList = new ArrayList<TestCaseDto>();

        for (TestCase testCase : testCaseList) {
            testCaseDtoList.add(new TestCaseDto(testCase));
        }

        return new ResponseEntity<List<TestCaseDto>>(testCaseDtoList, HttpStatus.OK);
    }
}

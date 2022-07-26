package dh.meli.qabugs.service;

import dh.meli.qabugs.exception.DeleteException;
import dh.meli.qabugs.exception.GetException;
import dh.meli.qabugs.exception.SaveException;
import dh.meli.qabugs.exception.TestCaseNotFoundException;
import dh.meli.qabugs.model.TestCase;
import dh.meli.qabugs.repository.TestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TestCaseService<T> {

    @Autowired
    public TestCaseRepository testCaseRepository;

    public TestCase createTestCase(TestCase testCase) {
        try {
            return testCaseRepository.save(testCase);
        } catch (Exception e) {
            throw new SaveException(e.getMessage());
        }
    }

    public List<TestCase> getTestCase() {
        try {
            List<TestCase> testCaseList = new ArrayList<TestCase>();

            testCaseRepository.findAll().forEach(testCaseList::add);

            return testCaseList;
        } catch (Exception e) {
            throw new GetException(e.getMessage());
        }
    }

    public TestCase getTestCaseById(long id) {
        Optional<TestCase> testCase;

        try {
            testCase = testCaseRepository.findById(id);
        } catch (Exception e) {
            throw new GetException(e.getMessage());
        }

        if (testCase.isEmpty()) {
            throw new TestCaseNotFoundException(String.format("Could not find test case for id %d", id));
        }

        return testCase.get();
    }

    public TestCase updateTestCase(long id, Map<String, T> data) {
        Optional<TestCase> testCase = testCaseRepository.findById(id);

        if (testCase.isEmpty()) {
            throw new TestCaseNotFoundException(String.format("Could not find test case for id %d", id));
        }

        data.forEach((atributo, valor) -> {
            switch (atributo) {
                case "description": testCase.get().setDescription((String) valor); break;
                case "tested": testCase.get().setTested((boolean) valor); break;
                case "passed": testCase.get().setPassed((boolean) valor); break;
                case "numberOfTries": testCase.get().setNumberOfTries((int) valor); break;
            }
        });

        try {
            testCaseRepository.save(testCase.get());
        } catch (Exception e) {
            throw new SaveException(e.getMessage());
        }

        return testCase.get();
    }

    public void deleteTestCase(long id) {
        Optional<TestCase> testCase = testCaseRepository.findById(id);

        if (testCase.isEmpty()) {
            throw new TestCaseNotFoundException(String.format("Could not find test case for id %d", id));
        }

        try {
            testCaseRepository.deleteById(id);
        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }

    public List<TestCase> getTestCaseByLastUpdate(String lastUpdate) {
        try {
            return new ArrayList<TestCase>(testCaseRepository
                    .getTestCaseByLastUpdate(lastUpdate));
        } catch (Exception e) {
            throw new GetException(e.getMessage());
        }
    }
}

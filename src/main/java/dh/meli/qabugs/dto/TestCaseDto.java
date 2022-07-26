package dh.meli.qabugs.dto;

import dh.meli.qabugs.model.TestCase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class TestCaseDto {
    private long id;
    private String description;
    private boolean tested;
    private boolean passed;

    public TestCaseDto(TestCase testCase) {
        this.id = testCase.getId();
        this.description = testCase.getDescription();
        this.tested = testCase.isTested();
        this.passed = testCase.isPassed();
    }
}

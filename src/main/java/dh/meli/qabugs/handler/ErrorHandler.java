package dh.meli.qabugs.handler;

import dh.meli.qabugs.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(SaveException.class)
    public ResponseEntity<ExceptionDetails> saveExceptionHandler(SaveException exception) {
        return new ResponseEntity<ExceptionDetails>(ExceptionDetails
                .builder()
                .title("Could not save test case")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(GetException.class)
    public ResponseEntity<ExceptionDetails> getExceptionHandler(GetException exception) {
        return new ResponseEntity<ExceptionDetails>(ExceptionDetails
                .builder()
                .title("Could not get any test case")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(TestCaseNotFoundException.class)
    public ResponseEntity<ExceptionDetails> testCaseNotFoundExceptionHandler(TestCaseNotFoundException exception) {
        return new ResponseEntity<ExceptionDetails>(ExceptionDetails
                .builder()
                .title("Could not get any test case")
                .status(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(DeleteException.class)
    public ResponseEntity<ExceptionDetails> deleteExceptionHandler(DeleteException exception) {
        return new ResponseEntity<ExceptionDetails>(ExceptionDetails
                .builder()
                .title("Could not delete test case")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}

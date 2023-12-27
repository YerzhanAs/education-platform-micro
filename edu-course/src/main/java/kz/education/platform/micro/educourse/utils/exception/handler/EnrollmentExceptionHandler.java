package kz.education.platform.micro.educourse.utils.exception.handler;

import kz.education.platform.micro.educourse.models.response.MessageResponse;
import kz.education.platform.micro.educourse.utils.exception.EnrollmentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EnrollmentExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EnrollmentException.class)
    public ResponseEntity<MessageResponse> handleEnrollmentException(EnrollmentException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
    }

}

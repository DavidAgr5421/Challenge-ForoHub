package alura.forohub.infra.errors;


import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors().stream().map(ErrorDataValidation::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity hnadleDuplicateEntry(SQLIntegrityConstraintViolationException exception){
            ErrorDataValidation errorDataValidation = new ErrorDataValidation("Titulo | Mensaje","La entidad que estás intentando registrar ya existe: " + exception.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDataValidation);
    }


    private record ErrorDataValidation(String campo, String error){
        public ErrorDataValidation(FieldError fieldError){
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}

package alura.forohub.controller;

import alura.forohub.domain.answer.ListAnswerData;
import alura.forohub.domain.answer.RegisterAnswerData;
import alura.forohub.domain.answer.ResponseAnswerData;
import alura.forohub.services.AnswersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class AnswerController {

    @Autowired
    private AnswersService service;

    @PostMapping("/{id}/respuestas")
    public ResponseEntity<ResponseAnswerData> registerAnswer(@PathVariable Long id, @RequestBody @Valid RegisterAnswerData registerAnswer, UriComponentsBuilder uriComponentsBuilder){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return service.registerAnswer(id, registerAnswer, authentication.getName(), uriComponentsBuilder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Page<ListAnswerData>> getAnswerList(@PathVariable Long id, Pageable pageable){
        return service.getAnswersListById(id, pageable);
    }
}

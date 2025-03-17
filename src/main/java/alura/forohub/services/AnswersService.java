package alura.forohub.services;

import alura.forohub.domain.answer.Answer;
import alura.forohub.domain.answer.ListAnswerData;
import alura.forohub.domain.answer.RegisterAnswerData;
import alura.forohub.domain.answer.ResponseAnswerData;
import alura.forohub.domain.topic.Topic;
import alura.forohub.domain.users.User;
import alura.forohub.repository.AnswersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class AnswersService {

    @Autowired
    private AnswersRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    public ResponseEntity<ResponseAnswerData> registerAnswer(Long id, RegisterAnswerData registerAnswer, String username, UriComponentsBuilder uriComponentsBuilder){
        Optional<User> user = Optional.ofNullable(userService.getUserByName(username));
        Optional<Topic> topic = Optional.ofNullable(new Topic(topicService.getTopicById(id)));
        if(user.isPresent()){
            Answer answer = repository.save(new Answer(registerAnswer,user.get(),id));
            URI url = uriComponentsBuilder.path(id+"/respuestas/{id}").buildAndExpand(user.get().GetId()).toUri();
            ResponseAnswerData responseAnswerData = new ResponseAnswerData(answer);
            return ResponseEntity.created(url).body(responseAnswerData);
        }else{
            throw new RuntimeException("No se ha encontrado el usuario");
        }
    }

    public ResponseEntity<Page<ListAnswerData>> getAnswersListById(Long id, Pageable pageable){
        return ResponseEntity.ok(repository.findById(id, pageable).map(ListAnswerData::new));
    }
}

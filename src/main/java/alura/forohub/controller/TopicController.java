package alura.forohub.controller;


import alura.forohub.domain.topic.*;
import alura.forohub.services.TopicService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/topicos")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping
    public ResponseEntity<ResponseTopicData> registerTopic(@RequestBody @Valid RegisterTopicData registerTopic, UriComponentsBuilder uriComponentsBuilder){
        return topicService.registerTopic(registerTopic, uriComponentsBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<ListTopicData>> getTopicList(Pageable pageable){
        return topicService.getTopicsList(pageable);
    }

    @GetMapping("/date10")
    public ResponseEntity<Page<ListTopicData>> getTopicFirst10List(Pageable pageable){
        return topicService.get10TopicsByDate(pageable);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ListTopicData>> getTopicByNameAndYear(Pageable pageable, @RequestParam(required = false) String curso,@RequestParam(required = false) Integer year ){
        return topicService.getTopicByNameAndYear(pageable,curso,year);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTopicData> getTopicById(@PathVariable Long id){
        return topicService.getTopicById(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseTopicData> updateTopicById(@RequestBody @Valid UpdateTopicData updateTopicData, @PathVariable Long id){
        return topicService.updateTopicById(updateTopicData,id);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopicById(@PathVariable Long id){
        return topicService.deleteTopicById(id);
    }
}

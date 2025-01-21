package alura.forohub.controller;

import alura.forohub.domain.topic.*;
import alura.forohub.repository.TopicRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicController {

    @Autowired
    private TopicRepository repository;

    @PostMapping
    public ResponseEntity<ResponseTopicData> registerTopic(@RequestBody @Valid RegisterTopicData registerTopic, UriComponentsBuilder uriComponentsBuilder){
        Topic topic = repository.save(new Topic(registerTopic));
        ResponseTopicData responseTopicData = new ResponseTopicData(topic);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(url).body(responseTopicData);
    }

    @GetMapping
    public ResponseEntity<Page<ListTopicData>> getTopicList(Pageable pageable){
        return ResponseEntity.ok(repository.findByStatusTrue(pageable).map(ListTopicData::new));
    }

    @GetMapping("/date10")
    public ResponseEntity<Page<ListTopicData>> getTopicFirst10List(Pageable pageable){
        return ResponseEntity.ok(repository.findTop10ByStatusTrueOrderByFechaCreacionAsc(pageable).map(ListTopicData::new));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ListTopicData>> getTopicByNameAndYear(Pageable pageable, @RequestParam(required = false) String curso,@RequestParam(required = false) Integer year ){
        if(curso != null && year != null){
            return ResponseEntity.ok(repository.findByCursoAndYear(pageable,curso, year).map(ListTopicData::new));
        }else if(curso != null){
            return ResponseEntity.ok(repository.findByCurso(pageable,curso).map(ListTopicData::new));
        }else if(year != null){
            return ResponseEntity.ok(repository.findByYear(pageable,year).map(ListTopicData::new));
        } else{
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTopicData> getTopicById(@PathVariable Long id){
        Topic topic = repository.getReferenceById(id);
        return ResponseEntity.ok(new ResponseTopicData(topic));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseTopicData> updateTopicById(@RequestBody @Valid UpdateTopicData updateTopicData, @PathVariable Long id){
        Optional<Topic> optionalTopic = Optional.of(repository.getReferenceById(id));
        if(optionalTopic.isPresent()){
            Topic topic = optionalTopic.get();
            topic.updateData(updateTopicData);
            return ResponseEntity.ok(new ResponseTopicData(topic));
        }else{
            throw new IllegalArgumentException("El tópico con el ID : |" + id + "| no existe.");
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopicById(@PathVariable Long id){
        Optional<Topic> optionalTopic = Optional.of(repository.getReferenceById(id));
        if(optionalTopic.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }else{
            throw new IllegalArgumentException("El tópico con el ID : |" + id + "| no existe.");
        }
    }
}

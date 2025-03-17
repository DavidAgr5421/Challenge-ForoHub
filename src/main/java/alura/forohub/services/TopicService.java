package alura.forohub.services;

import alura.forohub.domain.course.Course;
import alura.forohub.domain.topic.*;
import alura.forohub.repository.CourseRepository;
import alura.forohub.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository repository;

    @Autowired
    private CourseRepository courseRepository;


    public ResponseEntity<ResponseTopicData> registerTopic(RegisterTopicData registerTopic, UriComponentsBuilder uriComponentsBuilder){
        Optional<Course> course = courseRepository.findByNombre(registerTopic.curso().toUpperCase());
        if(course.isPresent()){
            Topic topic = repository.save(new Topic(registerTopic, course.get()));
            ResponseTopicData responseTopicData = new ResponseTopicData(topic);
            URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topic.getId()).toUri();
            return ResponseEntity.created(url).body(responseTopicData);
        }else{
            throw new IllegalArgumentException("El curso no existe.");
        }
    }

    public ResponseEntity<Page<ListTopicData>> getTopicsList(Pageable pageable){
        return ResponseEntity.ok(repository.findByStatusTrue(pageable).map(ListTopicData::new));
    }

    public ResponseEntity<Page<ListTopicData>> get10TopicsByDate(Pageable pageable){
        return ResponseEntity.ok(repository.findTop10ByStatusTrueOrderByFechaCreacionAsc(pageable).map(ListTopicData::new));
    }

    public ResponseEntity<Page<ListTopicData>> getTopicByNameAndYear(Pageable pageable, String curso , Integer year){
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

    public ResponseEntity<ResponseTopicData> getTopicById(Long id){
        Topic topic = repository.getReferenceById(id);
        return ResponseEntity.ok(new ResponseTopicData(topic));
    }

    public Topic getTopicByIdEntity(Long id){
        return repository.getReferenceById(id);
    }

    public ResponseEntity<ResponseTopicData> updateTopicById(UpdateTopicData updateTopicData, Long id){
        Optional<Topic> optionalTopic = Optional.of(repository.getReferenceById(id));
        if(updateTopicData.curso() != null){
            Optional<Course> optionalCourse = courseRepository.findByNombre(updateTopicData.curso());
            if(optionalCourse.isPresent() && optionalTopic.isPresent()){
                Topic topic = optionalTopic.get();
                Course course = optionalCourse.get();
                topic.updateData(updateTopicData,course);
                return ResponseEntity.ok(new ResponseTopicData(topic));
            }
        }else if(optionalTopic.isPresent()){
            Topic topic = optionalTopic.get();
            topic.updateData(updateTopicData,null);
            return ResponseEntity.ok(new ResponseTopicData(topic));
        }
        throw new IllegalArgumentException("El tópico con el ID : |" + id + "| no existe.");
    }

    public ResponseEntity deleteTopicById(Long id){
        Optional<Topic> optionalTopic = Optional.of(repository.getReferenceById(id));
        if(optionalTopic.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }else{
            throw new IllegalArgumentException("El tópico con el ID : |" + id + "| no existe.");
        }
    }
}

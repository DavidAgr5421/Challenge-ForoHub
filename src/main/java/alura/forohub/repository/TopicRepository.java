package alura.forohub.repository;

import alura.forohub.domain.topic.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TopicRepository extends JpaRepository<Topic, Long> {
    Page<Topic> findByStatusTrue(Pageable pageable);
}

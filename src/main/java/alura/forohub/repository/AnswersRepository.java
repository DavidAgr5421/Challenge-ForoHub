package alura.forohub.repository;

import alura.forohub.domain.answer.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswersRepository extends JpaRepository {
    Page<Answer> findById(Long id, Pageable pageable);
}

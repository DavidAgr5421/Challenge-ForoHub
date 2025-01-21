package alura.forohub.repository;

import alura.forohub.domain.topic.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TopicRepository extends JpaRepository<Topic, Long> {
    Page<Topic> findByStatusTrue(Pageable pageable);
    @Query("SELECT t FROM Topic t WHERE t.status = true ORDER BY t.fechaCreacion DESC")
    Page<Topic> findTop10ByStatusTrueOrderByFechaCreacionAsc(Pageable pageable);
    @Query("SELECT t FROM Topic t WHERE t.curso = :curso AND YEAR(t.fechaCreacion) = :year")
    Page<Topic> findByCursoAndYear(Pageable pageable,@Param("curso") String curso, @Param("year") Integer year);
    @Query("SELECT t FROM Topic t WHERE YEAR(t.fechaCreacion) = :year")
    Page<Topic> findByYear(Pageable pageable,@Param("year") Integer year);
    Page<Topic> findByCurso(Pageable pageable,@Param("curso") String curso);
}

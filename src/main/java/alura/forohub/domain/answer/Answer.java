package alura.forohub.domain.answer;


import alura.forohub.domain.topic.Topic;
import alura.forohub.domain.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "answers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;
    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topic topic;
    private LocalDateTime fechaCreacion;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private User user;
    @OneToMany
    @JoinColumn(name = "respuesta_padre_id")
    private Long respuestaPadreId;

    public Answer(RegisterAnswerData registerAnswer, User user, Topic topic) {
        this.mensaje = registerAnswer.mensaje();
        this.user  = user;
        this.topic = topic;
        this.fechaCreacion = LocalDateTime.now();
        this.respuestaPadreId = Optional.ofNullable(registerAnswer.respuesta_padre_id()).orElse(this.respuestaPadreId);
    }
}

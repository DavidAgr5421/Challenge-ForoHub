package alura.forohub.domain.topic;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Topic")
@Table(name = "topics")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean status;
    private String autor;
    private String curso;

    public Topic(){}

    public Topic(RegisterTopicData topicData){
        this.titulo = topicData.titulo();
        this.autor = topicData.autor();
        this.mensaje = topicData.mensaje();
        this.curso = topicData.curso();
        this.status = true;
        this.fechaCreacion = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void deactivateTopic(){
        this.status = false;
    }

    public void updateData(UpdateTopicData updateTopicData){
        if(updateTopicData.titulo() != null){
            this.titulo = updateTopicData.titulo();
        }
        if(updateTopicData.mensaje() != null){
            this.mensaje = updateTopicData.mensaje();
        }
        if(updateTopicData.autor() != null){
            this.autor = updateTopicData.autor();
        }
        if(updateTopicData.curso() != null){
            this.curso = updateTopicData.curso();
        }

    }
}

package alura.forohub.domain.topic;

public record UpdateTopicData(
        String titulo,
        String mensaje,
        String autor,
        String curso
) {
}

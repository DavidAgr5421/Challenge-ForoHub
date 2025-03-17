CREATE TABLE answers (
    id BIGINT NOT NULL AUTO_INCREMENT,
    mensaje VARCHAR(255) NOT NULL,
    topico_id BIGINT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    autor_id BIGINT NOT NULL,
    respuesta_padre_id BIGINT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (topico_id) REFERENCES topics(id) ON DELETE CASCADE,
    FOREIGN KEY (autor_id) REFERENCES users(id) ON DELETE CASCADE
);

ALTER TABLE answers
ADD CONSTRAINT fk_respuesta_padre
FOREIGN KEY (respuesta_padre_id) REFERENCES answers(id) ON DELETE CASCADE;
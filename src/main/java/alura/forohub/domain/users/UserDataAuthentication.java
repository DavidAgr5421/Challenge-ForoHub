package alura.forohub.domain.users;

import jakarta.validation.constraints.NotBlank;

public record UserDataAuthentication(
        @NotBlank
        String login,
        @NotBlank
        String password
) {
}

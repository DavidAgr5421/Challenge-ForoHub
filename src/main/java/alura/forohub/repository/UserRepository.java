package alura.forohub.repository;

import alura.forohub.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String username);
    @Query("SELECT u from User u WHERE u.login = :username")
    User getUserByLogin(String username);
}

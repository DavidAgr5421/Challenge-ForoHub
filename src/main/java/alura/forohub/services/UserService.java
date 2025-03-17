package alura.forohub.services;

import alura.forohub.domain.users.User;
import alura.forohub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User getUserByName(String name){
        return repository.getUserByLogin(name);
    }

}

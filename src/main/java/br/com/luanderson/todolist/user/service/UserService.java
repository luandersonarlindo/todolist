package br.com.luanderson.todolist.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.luanderson.todolist.user.entity.UserModel;
import br.com.luanderson.todolist.user.repository.IUserRepository;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    public UserModel create(UserModel userModel) {
        validateUser(userModel);

        String passwordHashed = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
        userModel.setPassword(passwordHashed);

        return userRepository.save(userModel);
    }

    public void validateUser(UserModel userModel) {
        // verifica se o username já está em uso por outro usuário
        Boolean usernameExists = userRepository.findByUsername(userModel.getUsername())
                .filter(user -> !user.getId().equals(userModel.getId())).isPresent();

        if (usernameExists) {
            throw new IllegalArgumentException("Username already in use");
        }

        // verifica se o nome já está em uso por outro usuário
        Boolean nameExists = userRepository.findByName(userModel.getName())
                .filter(user -> !user.getId().equals(userModel.getId())).isPresent();
        if (nameExists) {
            throw new IllegalArgumentException("Name already in use");
        }
    }
}

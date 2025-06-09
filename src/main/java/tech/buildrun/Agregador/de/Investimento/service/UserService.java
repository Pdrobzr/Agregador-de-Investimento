package tech.buildrun.Agregador.de.Investimento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.buildrun.Agregador.de.Investimento.dto.RecordUserDTO;
import tech.buildrun.Agregador.de.Investimento.entity.User;
import tech.buildrun.Agregador.de.Investimento.infra.SecurityConfig;
import tech.buildrun.Agregador.de.Investimento.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UUID createUser(RecordUserDTO createUserDTO) {

        var hashSenha = passwordEncoder.encode(createUserDTO.password());

        var entity = new User(createUserDTO.userName(), createUserDTO.email(), hashSenha);

        var userCreated = userRepository.save(entity);

        return userCreated.getUserId();

    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(UUID id) {
        var userExists = userRepository.existsById(id);

        if(userExists) {
            userRepository.deleteById(id);
        }
    }

    public void updateUser(UUID id, RecordUserDTO updateUserDTO) {
        Optional<User> userExists = userRepository.findById(id);

        if(userExists.isPresent()) {
            User user = new User(id, updateUserDTO.userName(), updateUserDTO.email(), updateUserDTO.password(), userExists.get().getCreatedAt());
            userRepository.save(user);
        }
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }
}

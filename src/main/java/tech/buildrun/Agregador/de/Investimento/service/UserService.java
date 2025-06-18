package tech.buildrun.Agregador.de.Investimento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.buildrun.Agregador.de.Investimento.dto.RecordLoginDTO;
import tech.buildrun.Agregador.de.Investimento.dto.RecordUserDTO;
import tech.buildrun.Agregador.de.Investimento.dto.ResponseLoginDTO;
import tech.buildrun.Agregador.de.Investimento.dto.ResponseUserDTO;
import tech.buildrun.Agregador.de.Investimento.entity.Role;
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

    @Autowired
    private TokenService tokenService;

    public UUID createUser(RecordUserDTO createUserDTO) {

        var hashSenha = passwordEncoder.encode(createUserDTO.password());

        var entity = new User(createUserDTO.userName(), createUserDTO.email(), hashSenha, new Role(2));

        var userCreated = userRepository.save(entity);

        return userCreated.getUserId();

    }

    public ResponseLoginDTO loginUser(RecordLoginDTO recordLoginDTO) {
        User user = userRepository.findByEmail(recordLoginDTO.email()).orElseThrow(() -> new RuntimeException("Email inválido!"));
        if(passwordEncoder.matches(recordLoginDTO.password(), user.getPassword())) {
            String token = tokenService.generateToken(user);
            return new ResponseLoginDTO(user.getUserId(), token);
        }
        return null;
    }

    public List<ResponseUserDTO> listUsers() {
        return userRepository.findAll().stream().map(user -> new ResponseUserDTO(user.getUserId(), user.getUserName(),
                user.getEmail(), user.getPassword(), user.getCreatedAt(), user.getRole().getRoleName())).toList();
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
            User user = new User(id, updateUserDTO.userName(), updateUserDTO.email(), updateUserDTO.password(), userExists.get().getCreatedAt(), userExists.get().getRole());
            userRepository.save(user);
        }
    }

    public ResponseUserDTO getUserById(UUID id) {
        Optional <User> findUser = userRepository.findById(id);
        User user = findUser.get();
        return new ResponseUserDTO(user.getUserId(), user.getUserName(), user.getEmail(), user.getPassword(),
                user.getCreatedAt(), user.getRole().getRoleName());
    }
}

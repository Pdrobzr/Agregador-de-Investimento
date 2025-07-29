package tech.buildrun.Agregador.de.Investimento.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import tech.buildrun.Agregador.de.Investimento.dto.RecordLoginDTO;
import tech.buildrun.Agregador.de.Investimento.dto.RecordUserDTO;
import tech.buildrun.Agregador.de.Investimento.dto.ResponseLoginDTO;
import tech.buildrun.Agregador.de.Investimento.dto.ResponseUserDTO;
import tech.buildrun.Agregador.de.Investimento.entity.Role;
import tech.buildrun.Agregador.de.Investimento.entity.User;
import tech.buildrun.Agregador.de.Investimento.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    TokenService tokenService;

    UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

    @BeforeEach
    public void setUp() {
        User user = new User(this.id,"Pedro", "pedro@gmail.com", "123", new Role(2));
        lenient().when(userRepository.findById(this.id)).thenReturn(Optional.of(user));
        lenient().when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        lenient().when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        lenient().when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
    }

    @Test
    @DisplayName("Deve criar usuário com sucesso")
    public void shouldCreateUserSuccessfully() {
        RecordUserDTO userDTO = new RecordUserDTO("teste", "teste@gmail.com", "123");

        when(passwordEncoder.encode(userDTO.password())).thenReturn("hashSenha");

        User user = new User(userDTO.userName(), userDTO.email(), "hashSenha", new Role(2));
        user.setUserId(UUID.randomUUID());

        when(userRepository.save(any(User.class))).thenReturn(user);

        UUID userId = userService.createUser(userDTO);

        assertNotNull(userId);
        verify(userRepository).save(any(User.class));
    }

    @Test
    @DisplayName("Deve realizar o login com sucesso")
    public void shouldLoginSuccessfully() {
        RecordLoginDTO loginDTO = new RecordLoginDTO("pedro@gmail.com", "123");
        User user = new User("Pedro", "pedro@gmail.com", "123", new Role(2));
        user.setUserId(this.id);

        when(tokenService.generateToken(any(User.class))).thenReturn("mocked_token");

        ResponseLoginDTO responseLoginDTO = userService.loginUser(loginDTO);

        assertEquals(user.getUserId(), responseLoginDTO.id());
        assertEquals("mocked_token", responseLoginDTO.token());
    }

    @Test
    @DisplayName("Deve retornar exceção de email inválido")
    public void shouldThrowExceptionWhenEmailInvalid() {
        RecordLoginDTO loginDTO = new RecordLoginDTO("invalid@gmail.com", "123");

        assertThrows(RuntimeException.class, () -> userService.loginUser(loginDTO));
    }

    @Test
    @DisplayName("Deve deletar usuário com sucesso")
    public void shouldDeleteUser() {
        when(userRepository.existsById(this.id)).thenReturn(true);

        userService.deleteUser(this.id);

        verify(userRepository).deleteById(this.id);
    }

    @Test
    @DisplayName("Deve falhar ao tentar encontrar usuário para deletar")
    public void shouldThrowExceptionWhenDeletingNonexistentUser() {
        when(userRepository.existsById(this.id)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> userService.deleteUser(this.id));
    }

    @Test
    @DisplayName("Deve retornar uma lista com apenas um usuário")
    public void shouldReturnListOfUsers() {
        List<ResponseUserDTO> usuarios = userService.listUsers();

        assertEquals(1, usuarios.size());

        verify(userRepository).findAll();
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    @DisplayName("Deve retornar um usuário com base no id")
    public void shouldReturnUserById() {
        ResponseUserDTO userResponse = userService.getUserById(id);
        assertNotNull(userResponse);
    }


    @Test
    @DisplayName("Deve retornar exceção de usuário não encontrado!")
    public void shouldThrowExceptionWhenUserNotFoundById() {

        assertThrows(RuntimeException.class, () -> userService.getUserById(UUID.randomUUID()));
    }
}
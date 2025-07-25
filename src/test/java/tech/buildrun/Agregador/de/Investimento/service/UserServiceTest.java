package tech.buildrun.Agregador.de.Investimento.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

    String idString = "123e4567-e89b-12d3-a456-426614174000";

    @BeforeEach
    public void setUp() {
        User user = new User(UUID.fromString(this.idString),"Pedro", "pedro@gmail.com", "123", new Role(2));
        lenient().when(userRepository.findById(UUID.fromString(this.idString))).thenReturn(Optional.of(user));
        lenient().when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
    }

    @Test
    @DisplayName("Deve retornar uma lista com apenas um usuário")
    public void returnUserList() {
        List<ResponseUserDTO> usuarios = userService.listUsers();

        assertEquals(1, usuarios.size());

        verify(userRepository).findAll();
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    @DisplayName("Deve retornar um usuário com base no id")
    public void returnUserById() {

        var usuarioResponse = userService.getUserById(UUID.fromString(this.idString));
        assertNotNull(usuarioResponse);
    }
}
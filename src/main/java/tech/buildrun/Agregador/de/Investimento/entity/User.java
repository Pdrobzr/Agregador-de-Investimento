package tech.buildrun.Agregador.de.Investimento.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tech.buildrun.Agregador.de.Investimento.dto.RecordUserDTO;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
public class User implements UserDetails {

    public User(UUID userId, String userName, String email, String password, LocalDateTime createdAt, Role role) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.role = role;
    }

    public User(RecordUserDTO recordUserDTO) {
        this.userName = recordUserDTO.userName();
        this.email = recordUserDTO.email();
        this.password = recordUserDTO.password();
    }

    public User(UUID userId, String userName, String email, String password, LocalDateTime createdAt, List<Investment> investments) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.investments = investments;
    }

    public User(String userName, String email, String password, Role role) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(unique = true)
    private String userName;

    @Column(unique = true)
    private String email;

    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

//    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Investment> investments;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + getRole().getRoleName()));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Investment> getInvestments() {
        return investments;
    }

    public void setInvestments(List<Investment> investments) {
        this.investments = investments;
    }
}

package tech.buildrun.Agregador.de.Investimento.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_roles")
public class Role {

    @Id
    private int roleId;

    @Column(unique = true, nullable = false)
    private String roleName;

    public Role(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Role(int roleId) {
        this.roleId = roleId;
    }
    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role() {
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}

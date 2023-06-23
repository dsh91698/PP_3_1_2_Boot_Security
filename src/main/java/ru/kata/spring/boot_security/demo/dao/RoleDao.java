package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    void addRolesToDb(Set<Role> roles);
    List<Role> getAllRoles();

    Role getRoleById(Long roleId);

    Role getRoleByName(String roleName);

    void setUserRole(String role);
}

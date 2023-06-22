package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleDao {
    void addRolesToDb(List<Role> roles);
    List<Role> getAllRoles();

    Role getRoleById(Long roleId);

    Role getRoleByName(String roleName);

    void setUserRole(String role);
}

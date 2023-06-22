package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    void addRolesToDb(List<Role> roles);

    List<Role> getAllRoles();

    Role getRoleById(Long roleId);

    Role getRoleByName(String roleName);

    void setUserRole(String role);
}

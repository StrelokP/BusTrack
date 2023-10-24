package ru.aptech.bustrack.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aptech.bustrack.entities.Role;
import ru.aptech.bustrack.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;
@Service
public class RoleService {
    @SuppressWarnings("unused")
    @Autowired
    protected RoleRepository roleRepository;

    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

}

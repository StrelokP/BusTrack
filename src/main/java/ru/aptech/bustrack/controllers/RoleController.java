package ru.aptech.bustrack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aptech.bustrack.entities.Role;
import ru.aptech.bustrack.services.RoleService;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    protected RoleService roleService;

    @GetMapping("/role")
    public ResponseEntity<?> getRoleById(@RequestParam(name = "id") Long id) {
        Optional<Role> role = roleService.getRoleById(id);

        if (role.isPresent()) {
            return ResponseEntity.ok(role.get());
        } else {
            return ResponseEntity.badRequest().body("Роль не найдена");
        }
    }

    @GetMapping("/roles")
    public ResponseEntity<?> getRoles() {
        return ResponseEntity.ok(roleService.getRoles());
    }

    @PostMapping("/role")
    public void saveRole(@RequestBody Role role) {
        roleService.saveRole(role);
    }

    @DeleteMapping("/role")
    public void deleteRole(@RequestParam(name = "id") Long id) {
        roleService.deleteRoleById(id);
    }

}

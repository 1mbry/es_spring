package it.syncroweb.logintest.service;

import it.syncroweb.logintest.model.Role;
import it.syncroweb.logintest.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role create(Role role) {
        return roleRepository.save(role);
    }
}

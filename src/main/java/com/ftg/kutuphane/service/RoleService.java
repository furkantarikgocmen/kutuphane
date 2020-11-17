package com.ftg.kutuphane.service;

import com.ftg.kutuphane.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}

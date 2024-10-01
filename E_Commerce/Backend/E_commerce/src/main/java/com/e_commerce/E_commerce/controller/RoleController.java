package com.e_commerce.E_commerce.controller;


import com.e_commerce.E_commerce.model.Role;
import com.e_commerce.E_commerce.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @GetMapping()
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }


}

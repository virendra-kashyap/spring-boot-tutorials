package com.redis.spring.ctl;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redis.spring.dto.RoleDTO;
import com.redis.spring.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleCtl {

    private final RoleService roleService;

    public RoleCtl(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public RoleDTO save(@RequestBody RoleDTO roleDTO) {
        return roleService.save(roleDTO);
    }

    @GetMapping
    public List<RoleDTO> getList() {
        return roleService.findAllFromCache();
    }
    
}
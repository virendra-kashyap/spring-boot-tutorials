package com.redis.spring.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class RoleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    
}

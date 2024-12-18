package com.virendra.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class EntityNotFoundException extends RuntimeException{
    private final String message;
}

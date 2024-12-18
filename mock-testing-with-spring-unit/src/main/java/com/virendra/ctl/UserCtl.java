package com.virendra.ctl;

import com.virendra.entity.UserEntity;
import com.virendra.exception.EntityNotFoundException;
import com.virendra.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
@AllArgsConstructor
public class UserCtl {

    private final UserService userService;

    @PostMapping
    public String save(@RequestBody UserEntity user) {
        userService.save(user);
        return "record successfully added.";
    }

    @GetMapping
    public List<UserEntity> list() {
        return userService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found"));
    }


}

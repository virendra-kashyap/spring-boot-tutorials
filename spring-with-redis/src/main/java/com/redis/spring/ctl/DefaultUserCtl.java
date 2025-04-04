package com.redis.spring.ctl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redis.spring.dto.User;

@RestController
@RequestMapping("/api/default/")
public class DefaultUserCtl {

    @GetMapping("get")
    public List<User> get() {
        List<User> listOfUser = new ArrayList<>();
        User user = new User("1", "Virendra");
        User user2 = new User("2", "Piyush");
        User user3 = new User("3", "Satya");
        User user4 = new User("4", "Parth");
        listOfUser.add(user);
        listOfUser.add(user2);
        listOfUser.add(user3);
        listOfUser.add(user4);
        return listOfUser;
    }
    
}

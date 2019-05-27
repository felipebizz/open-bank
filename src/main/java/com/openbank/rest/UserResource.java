package com.openbank.rest;

import com.openbank.controller.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
@RequestMapping("rest")
public class UserResource {

    @RequestMapping("user")
    @ResponseBody
    public User getUserDetails() {
        String username = ((User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUsername();

        User user = new User();
        user.setUsername(username);

        return user;
    }
}

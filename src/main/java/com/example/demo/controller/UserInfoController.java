package com.example.demo.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.Date;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserInfoController {

    @PostMapping("/whoim")
   // @PreAuthorize("hasRole('DEVELOPERS')")
    public ResponseEntity<?> getUserLogin(Authentication principal) {
        Map<String, Object> params = new HashedMap<>();
        params.put("time", new Date());
        params.put("currentUser", principal.getName());
        return ok().body(params);
    }
}

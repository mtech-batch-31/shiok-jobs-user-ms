package com.mtech.sjmsuser.controller;

import com.mtech.sjmsuser.model.UpdateUserDto;
import com.mtech.sjmsuser.util.JwtTokenUtil;
import com.mtech.sjmsuser.model.UserProfileDto;
import com.mtech.sjmsuser.service.UserProfileService; // Import the service
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserProfileController {

    private final UserProfileService userProfileService; // Inject the service

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/api/v1/user/me")
    public ResponseEntity<UserProfileDto> verifyToken(@RequestHeader("user-id") String userId) {
//            System.out.println("token " + token);
            UserProfileDto userProfileDto = userProfileService.findByAccountUuid(userId);
        if (userProfileDto != null) {
            return ResponseEntity.ok(userProfileDto);
        } else {
            UserProfileDto errorResponse = new UserProfileDto();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PutMapping("/api/v1/user")
    public ResponseEntity<UserProfileDto> update(@RequestHeader("user-id") String accountUuid, @RequestBody UpdateUserDto updateUserDto) {
        UserProfileDto userProfileDto = userProfileService.updateUserProfile(accountUuid, updateUserDto);
        if (userProfileDto != null) {
            return ResponseEntity.ok(userProfileDto);
        } else {
            UserProfileDto errorResponse = new UserProfileDto();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
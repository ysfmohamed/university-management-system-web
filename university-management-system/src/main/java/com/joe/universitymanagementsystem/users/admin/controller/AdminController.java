package com.joe.universitymanagementsystem.users.admin.controller;

import com.joe.universitymanagementsystem.requests.exceptions.FinAidAlreadyAccepted;
import com.joe.universitymanagementsystem.requests.exceptions.FinAidAlreadyRejected;
import com.joe.universitymanagementsystem.users.admin.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PutMapping("/accept/user-registration/{email}")
    private ResponseEntity<String> acceptUserRegistration(@PathVariable String email, @RequestParam String type) {
        adminService.acceptUserRegistration(email, type);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Account with this \"" + email + "\" is activated.");
    }

    @PutMapping("/reject/user-registration/{email}")
    private ResponseEntity<String> rejectUserRegistration(@PathVariable String email, @RequestParam String type) {
        adminService.rejectUserRegistration(email, type);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Account with this \"" + email + "\" is rejected.");
    }

    @PutMapping("/accept/financial-aid/{id}")
    private ResponseEntity<String> acceptFinancialAidRequest(@PathVariable Integer id) throws FinAidAlreadyAccepted, FinAidAlreadyRejected {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(adminService.acceptFinancialAidRequest(id));
    }

    @PutMapping("/reject/financial-aid/{id}")
    private ResponseEntity<String> rejectFinancialAidRequest(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(adminService.rejectFinancialAidRequest(id));
    }
}

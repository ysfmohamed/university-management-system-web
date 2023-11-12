package com.joe.universitymanagementsystem.requests.financialrequests;

import com.joe.universitymanagementsystem.users.enums.Status;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/financial-aid")
@AllArgsConstructor
public class FinancialAidController {
    private final FinancialAidService financialAidService;

    @GetMapping("/all")
    private ResponseEntity<List<RetrievedFinancialAid>> getFinancialAidRequestsByStatus(@RequestParam Status status) {
        return ResponseEntity.ok().body(financialAidService.getFinancialAidRequestsByStatus(status));
    }

    @GetMapping("/{id}")
    private ResponseEntity<SingleFinancialAid> getFinancialAidRequest(@PathVariable Integer id) {
        return ResponseEntity.ok().body(financialAidService.getFinancialAidRequest(id));
    }
}

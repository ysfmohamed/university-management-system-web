package com.joe.universitymanagementsystem.requests.financialrequests;

import com.joe.universitymanagementsystem.users.enums.Status;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class FinancialAidService {
    private final FinancialAidRepository financialAidRepository;

    public List<RetrievedFinancialAid> getFinancialAidRequestsByStatus(Status status) {
        return financialAidRepository.findByStatus(status);
    }

    public SingleFinancialAid getFinancialAidRequest(Integer id) {
        return financialAidRepository.findById(id, SingleFinancialAid.class).get();
    }
}

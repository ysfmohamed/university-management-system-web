package com.joe.universitymanagementsystem.requests.financialrequests;

import com.joe.universitymanagementsystem.users.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FinancialAidRepository extends JpaRepository<FinancialAid, Integer> {
    public <T> Optional<T> findById(Integer id, Class<T> type);

    public List<FinancialAid> findByStudentEmailAndStatus(String email, Status status);

    @Query("UPDATE FinancialAid SET status = ?2 WHERE id = ?1")
    public void updateFinAidStatus(Integer id, Status status);

    public List<RetrievedFinancialAid> findByStatus(Status status);
}

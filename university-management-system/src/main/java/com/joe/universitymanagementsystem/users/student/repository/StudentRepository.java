package com.joe.universitymanagementsystem.users.student.repository;

import com.joe.universitymanagementsystem.users.enums.Status;
import com.joe.universitymanagementsystem.users.student.projections.RetrievedStudent;
import com.joe.universitymanagementsystem.users.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    @Modifying
    @Transactional
    @Query("UPDATE Student SET status = ?2 WHERE email = ?1")
    public void updateStudentStatus(String email, Status status);

    @Modifying
    @Transactional
    @Query("UPDATE Student SET balance = balance + ?2 WHERE email IN ?1")
    public int updateStudentsBalance(List<String> emails, int balance);

    public List<RetrievedStudent> findByStatus(Status status);

    public RetrievedStudent findByEmail(String email);

    @Query("UPDATE Student SET balance = ?2 WHERE email = ?1")
    public void updateStudentBalance(String email, Integer balance);
}

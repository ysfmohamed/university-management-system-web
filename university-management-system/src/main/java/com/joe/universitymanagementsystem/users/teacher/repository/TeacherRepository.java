package com.joe.universitymanagementsystem.users.teacher.repository;

import com.joe.universitymanagementsystem.users.enums.Status;
import com.joe.universitymanagementsystem.users.teacher.dto.RetrievedTeacherDto;
import com.joe.universitymanagementsystem.users.teacher.model.Teacher;
import com.joe.universitymanagementsystem.users.teacher.projections.RetrievedTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
    @Modifying
    @Transactional
    @Query("UPDATE Teacher t SET t.status = ?2 WHERE t.email = ?1")
    public void updateTeacherStatus(String email, Status status);

    public List<RetrievedTeacher> findByStatus(Status status);

    public RetrievedTeacher findByEmail(String email);
}

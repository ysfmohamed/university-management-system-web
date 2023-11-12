package com.joe.universitymanagementsystem.semesters.repository;

import com.joe.universitymanagementsystem.semesters.enums.SemesterStatus;
import com.joe.universitymanagementsystem.semesters.model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepository extends JpaRepository<Semester, Integer> {
    public Semester findBySemesterStatus(SemesterStatus semesterStatus);
}

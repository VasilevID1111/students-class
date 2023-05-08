package com.example.studentclass.shedule;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SheduleDAO extends JpaRepository<SheduleDTO, Integer> {
}

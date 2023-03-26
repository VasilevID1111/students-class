package com.example.studentclass.computers;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerDAO extends CrudRepository<ComputerDTO, Integer> {
    List<ComputerDTO> findByOrderByIsWorkableDesc();
}

package com.example.studentclass.computers;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ComputerDAO extends CrudRepository<ComputerDTO, Integer> {
}

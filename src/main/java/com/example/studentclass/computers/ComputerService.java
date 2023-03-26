package com.example.studentclass.computers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ComputerService {
    @Autowired
    private ComputerDAO computerDAO;

    public ComputerDTO getComputer(Integer compId) {
        Optional<ComputerDTO> computer = computerDAO.findById(compId);
        if (computer.isPresent()) {
            return computer.get();
        } else {
            throw new IllegalArgumentException(); //написать ошибки
        }
    }
    public Iterable<ComputerDTO> getComputersAllDesc() {
        return computerDAO.findByOrderByIsWorkableDesc();
    }

    public void saveComputer(ComputerDTO computer) {
        computerDAO.save(computer);
    }

    public void deleteComputer(Integer compId){
        computerDAO.deleteById(compId);
    }
}

package com.example.studentclass.shedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SheduleService {
    @Autowired
    private final SheduleDAO SheduleDAO;
    public SheduleDTO getComputer(Integer sheduleId) {
        Optional<SheduleDTO> visit = SheduleDAO.findById(sheduleId);
        if (visit.isPresent()) {
            return visit.get();
        } else {
            throw new IllegalArgumentException(); //написать ошибки
        }
    }
    public void saveShedule(SheduleDTO visit) {
        SheduleDAO.save(visit);
    }
    public void deleteSheduleById(Integer sheduleId){
        SheduleDAO.deleteById(sheduleId);
    }

}

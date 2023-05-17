package com.example.studentclass.check;

import com.example.studentclass.shedule.SheduleDAO;
import com.example.studentclass.shedule.SheduleDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CheckService {
    private final SheduleDAO sheduleDAO;

    public List<SheduleDTO> getShedulesByDateAndStatuses(Date day,List<String> statuses){
        return sheduleDAO.findSheduleDTOSByDateAndStatuses(day,statuses);
    }

    public void changeStatus(Integer visit_id, String status) {
        SheduleDTO visit = sheduleDAO.findSheduleDTOByVisit_id(visit_id);
        visit.setStatus(status);
        sheduleDAO.save(visit);
    }
}

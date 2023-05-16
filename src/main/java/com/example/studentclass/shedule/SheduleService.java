package com.example.studentclass.shedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class SheduleService {
    @Autowired
    private final SheduleDAO sheduleDAO;

    public SheduleDTO getComputer(Integer sheduleId) {
        Optional<SheduleDTO> visit = sheduleDAO.findById(sheduleId);
        if (visit.isPresent()) {
            return visit.get();
        } else {
            throw new IllegalArgumentException(); //написать ошибки
        }
    }
    public void saveShedule(SheduleDTO visit) {
        sheduleDAO.save(visit);
    }
    public void deleteSheduleById(Integer sheduleId){
        sheduleDAO.deleteById(sheduleId);
    }
    public Hashtable<String,String> getShedulesByCompIdOnDate(Integer compId, Date afterDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        List<SheduleDTO> visits = sheduleDAO.findByCompAfterDate(compId,afterDate);
        Hashtable<String,String> DateTimed = new Hashtable<String, String>();
        for (SheduleDTO visit : visits) {
            Date dateOfVisit = visit.getDate();
            String formattedDate = sdf.format(dateOfVisit);
            if (DateTimed.containsKey(formattedDate)) {
                DateTimed.put(formattedDate,
                        DateTimed.get(formattedDate) + parseRangeString(visit.getTimed_blocks()));

            } else {
                DateTimed.put(formattedDate, parseRangeString(visit.getTimed_blocks()));
            }
        }
        for (Map.Entry<String, String> entry : DateTimed.entrySet()) {
            if (entry.getValue().split(",").length == 37) {
                DateTimed.put(entry.getKey(), "out");
            }
        }
        return DateTimed;
    }

    public static String parseRangeString(String rangeString) {
        String result = "";
        String[] startEnd = rangeString.split("-");
        int start = Integer.parseInt(startEnd[0]);
        int end = Integer.parseInt(startEnd[1]);
        for (int i = start; i <= end; i++) {
            result += "," + i;
        }
        return result;
    }

    public List<SheduleDTO> getVisitsByUserId(Integer userId) {
        return sheduleDAO.findByUser(userId);
    }

    public List<SheduleDTO> getVisitsByUserIdAfterDate(Integer userId, Date date) {
        return sheduleDAO.findByUserIdAfterDate(userId, date);
    }
}

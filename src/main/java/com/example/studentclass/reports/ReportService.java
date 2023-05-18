package com.example.studentclass.reports;

import com.example.studentclass.shedule.SheduleDAO;
import com.example.studentclass.shedule.SheduleDTO;
import com.example.studentclass.shedule.SheduleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
@AllArgsConstructor
public class ReportService {
    @Autowired
    private SimpleDateFormat dateFormat;
    @Autowired
    private final SheduleDAO sheduleDAO;

    public List<SheduleDTO> getOneMonthShedule() {
        Date currentDate = new Date();
        long twoWeeksInMillis = 2L * 7 * 24 * 60 * 60 * 1000; // Two weeks in milliseconds

        // Subtract two weeks from the current date
        Date twoWeeksEarlier = new Date(currentDate.getTime() - twoWeeksInMillis);
        Date twoWeeksLater = new Date(currentDate.getTime() + twoWeeksInMillis);
        return sheduleDAO.findAllBetweenTwoDate(twoWeeksEarlier,twoWeeksLater);
    }

    public List<SheduleDTO> getSheduleWithSettings(String login, Integer compId, String dateFrom, String dateTo) throws ParseException {
        Date dateFromParsed = (dateFrom.isEmpty() || dateFrom == null) ? null : dateFormat.parse(dateFrom);
        Date dateToParsed = (dateTo.isEmpty() || dateTo == null) ? null : dateFormat.parse(dateTo);
        return sheduleDAO.findSheduleDTOSBySettings("%"+login+"%",compId,dateFromParsed,dateToParsed);
    }
}

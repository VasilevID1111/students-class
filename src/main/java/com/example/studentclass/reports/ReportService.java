package com.example.studentclass.reports;

import com.example.studentclass.shedule.SheduleDAO;
import com.example.studentclass.shedule.SheduleDTO;
import com.example.studentclass.users.UserDTO;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

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
        return sheduleDAO.findAllBetweenTwoDate(twoWeeksEarlier, twoWeeksLater);
    }

    public List<SheduleDTO> getSheduleWithSettings(String login, Integer compId, String dateFrom, String dateTo) throws ParseException {
        Date dateFromParsed = (dateFrom.isEmpty() || dateFrom == null) ? null : dateFormat.parse(dateFrom);
        Date dateToParsed = (dateTo.isEmpty() || dateTo == null) ? null : dateFormat.parse(dateTo);
        return sheduleDAO.findSheduleDTOSBySettings("%" + login + "%", compId, dateFromParsed, dateToParsed);
    }

    public Workbook createXslxFile(List<SheduleDTO> visits) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Отчет по студентам");

        // Create headers
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Активность");
        headerRow.createCell(1).setCellValue("Логин");
        headerRow.createCell(2).setCellValue("ФИО");
        headerRow.createCell(3).setCellValue("Почта");
        headerRow.createCell(4).setCellValue("Время");
        headerRow.createCell(5).setCellValue("Дата");
        headerRow.createCell(6).setCellValue("Id компьютера");
        headerRow.createCell(7).setCellValue("Имя компьютера");
        headerRow.createCell(8).setCellValue("Статус");

        // Populate data rows
        int rowNum = 1;
        for (SheduleDTO visit : visits) {
            Row row = sheet.createRow(rowNum++);
            UserDTO user = visit.getUser();
            row.createCell(0).setCellValue(user.isActive());
            row.createCell(1).setCellValue(user.getLogin());
            row.createCell(2).setCellValue(user.getFio());
            row.createCell(3).setCellValue(user.getEmail());
            row.createCell(4).setCellValue(convertTimeRange(visit.getTimed_blocks()));
            row.createCell(5).setCellValue(visit.getDate().toString());
            row.createCell(6).setCellValue(visit.getComputer().getCompId());
            row.createCell(7).setCellValue(visit.getComputer().getName());
            row.createCell(8).setCellValue(visit.getStatus());
        }

        // Auto-size columns
        for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
            sheet.autoSizeColumn(i);
        }
        return workbook;
    }

    public String convertTimeRange(String timeRange) {
        int startHour = 9;
        int startMinute = 0;
        int timeSlotMinutes = 15;

        String[] timeParts = timeRange.split("-");
        int start = Integer.parseInt(timeParts[0]);
        int end = Integer.parseInt(timeParts[1]);

        int startTime = startHour * 60 + startMinute + start * timeSlotMinutes;
        int endTime = startHour * 60 + startMinute + end * timeSlotMinutes + 15;

        String startHourStr = String.format("%02d", startTime / 60);
        String startMinuteStr = String.format("%02d", startTime % 60);
        String endHourStr = String.format("%02d", endTime / 60);
        String endMinuteStr = String.format("%02d", endTime % 60);

        return "c " + startHourStr + "-" + startMinuteStr + " до " + endHourStr + "-" + endMinuteStr;
    }
}

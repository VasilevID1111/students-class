package com.example.studentclass.reports;

import com.example.studentclass.computers.ComputerService;
import com.example.studentclass.shedule.SheduleDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasAuthority('ROLE_WORKER')")
public class ReportController {
    @Autowired
    private final ReportService reportService;
    private final ComputerService computerService;
    private List<SheduleDTO> globalVisits = new ArrayList<>();
    @GetMapping("/report")
    public String report(Model model,
                         RedirectAttributes redirectAttributes) {
        model.addAttribute("computers", computerService.getComputersAllDesc());
        List<SheduleDTO> visits = reportService.getOneMonthShedule();
        globalVisits = visits;
        model.addAttribute("visits",visits);
        return "report";
    }
    @PostMapping("/report")
    public String getReport(@RequestParam("login") String login,
                            @RequestParam("compId") Integer compId,
                            @RequestParam("dateFrom") String dateFrom,
                            @RequestParam("dateTo") String dateTo,
                            Model model,
                            RedirectAttributes redirectAttributes) throws ParseException {
        model.addAttribute("computers", computerService.getComputersAllDesc());
        List<SheduleDTO> visits = reportService.getSheduleWithSettings(login,compId,dateFrom,dateTo);
        globalVisits = visits;
        model.addAttribute("visits",visits);
        return "report";
    }
    @PostMapping("/report/xlsx")
    public ResponseEntity<byte[]> getXlsx(RedirectAttributes redirectAttributes,
                                          Model model)
            throws IOException {
        @SuppressWarnings("unchecked")
        List<SheduleDTO> visits = globalVisits;
        Workbook workbook = reportService.createXslxFile(visits);
        // Convert workbook to byte array
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        byte[] workbookBytes = byteArrayOutputStream.toByteArray();

        // Set response headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "report.xlsx");

        // Send workbook as a file download
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(workbookBytes);
    }
}

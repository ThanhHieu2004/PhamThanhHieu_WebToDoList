package edu.ute.PhamThanhHieu_WebToDoList.controller;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import edu.ute.PhamThanhHieu_WebToDoList.security.CustomUserDetails;
import edu.ute.PhamThanhHieu_WebToDoList.service.ReportService;

@Controller
public class ReportController {

    private final ReportService reportService;
    
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }
    
    @GetMapping("/bao-cao")
    public ResponseEntity<InputStreamResource> generateReport() {
        // Get current user ID
        int userId = getCurrentUserId();
        
        // Generate PDF report
        ByteArrayInputStream bis = reportService.generateTaskReport(userId);
        
        // Set HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=baocao_congviec.pdf");
        
        // Return PDF file
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
    
    private int getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getId();
    }
}

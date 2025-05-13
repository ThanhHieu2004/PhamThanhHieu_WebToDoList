package edu.ute.PhamThanhHieu_WebToDoList.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;

import edu.ute.PhamThanhHieu_WebToDoList.dto.TaskResponseDTO;
import edu.ute.PhamThanhHieu_WebToDoList.model.User;
import edu.ute.PhamThanhHieu_WebToDoList.repo.UserRepository;
import edu.ute.PhamThanhHieu_WebToDoList.utils.PRIORITY;

@Service
public class ReportService {

    private final TaskService taskService;
    private final UserRepository userRepository;
    
    public ReportService(TaskService taskService, UserRepository userRepository) {
        this.taskService = taskService;
        this.userRepository = userRepository;
    }
    
    public ByteArrayInputStream generateTaskReport(int userId) {
        // Get user information
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Không tìm thấy user"));
        
        // Get all tasks for the user
        List<TaskResponseDTO> tasks = taskService.getTasksByUserId(userId, "newest").getTasks();
        
        // Create PDF document
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        try {
            // Initialize PDF writer
            PdfWriter.getInstance(document, out);
            document.open();
            
            // Add title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Báo cáo công việc cần làm", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);
            
            // Add user information
            Font userInfoFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Paragraph userInfo = new Paragraph("Người dùng: " + user.getUsername(), userInfoFont);
            userInfo.setSpacingAfter(10);
            document.add(userInfo);
            
            // Add current date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            Paragraph dateInfo = new Paragraph("Ngày in báo cáo: " + java.time.LocalDate.now().format(formatter));
            dateInfo.setSpacingAfter(20);
            document.add(dateInfo);
            
            // Create tasks table
            PdfPTable table = new PdfPTable(5); // 5 columns
            table.setWidthPercentage(100);
            table.setWidths(new float[] {1.5f, 3.5f, 2.5f, 1.5f, 1f});
            table.setSpacingBefore(10f);
            
            // Add table headers
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
            PdfPCell headerCell;
            
            String[] headers = {"Công việc", "Mô tả", "Ngày đến hạn", "Ưu tiên", "Hoàn thành?"};
            for (String header : headers) {
                headerCell = new PdfPCell(new Phrase(header, headerFont));
                headerCell.setBackgroundColor(Color.LIGHT_GRAY);
                headerCell.setPadding(5);
                table.addCell(headerCell);
            }
            
            // Add data rows
            Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 9);
            
            for (TaskResponseDTO task : tasks) {
                // Title
                PdfPCell cell = new PdfPCell(new Phrase(task.getTitle(), dataFont));
                cell.setPadding(5);
                table.addCell(cell);
                
                // Description (truncate if too long)
                String description = task.getDescription();
                if (description != null && description.length() > 100) {
                    description = description.substring(0, 97) + "...";
                }
                cell = new PdfPCell(new Phrase(description, dataFont));
                cell.setPadding(5);
                table.addCell(cell);
                
                // Due Date
                cell = new PdfPCell(new Phrase(
                    task.getDueDate() != null ? task.getDueDate().format(formatter) : "N/A", 
                    dataFont));
                cell.setPadding(5);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                // Priority
                cell = new PdfPCell(new Phrase(task.getPriority(), dataFont));
                cell.setPadding(5);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                // Set cell background color based on priority
                if (PRIORITY.high.toString().equals(task.getPriority())) {
                    cell.setBackgroundColor(Color.PINK);
                } else if (PRIORITY.medium.toString().equals(task.getPriority())) {
                    cell.setBackgroundColor(Color.YELLOW);
                }
                
                table.addCell(cell);
                
                // Completed
                // cell = new PdfPCell(new Phrase(task.getIsCompleted() ? "Yes" : "No", dataFont));
                // cell.setPadding(5);
                // cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                // table.addCell(cell);
            }
            
            document.add(table);
            
            // Add summary
            Paragraph summary = new Paragraph("\nCông việc tổng cộng: " + tasks.size(), userInfoFont);
            summary.setSpacingBefore(20);
            document.add(summary);
            
            // Close document
            document.close();
            
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        
        return new ByteArrayInputStream(out.toByteArray());
    }
}

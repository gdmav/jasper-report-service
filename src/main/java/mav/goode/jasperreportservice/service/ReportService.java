package mav.goode.jasperreportservice.service;
//
//import com.jaspersoft.jasperserver.dto.reports.ReportExecutionRequest;
//import com.jaspersoft.jasperserver.dto.reports.ReportExecutionResponse;
import org.springframework.stereotype.Service;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import java.io.ByteArrayOutputStream;
import java.util.Map;

@Service
public class ReportService {

    private static final String REPORT_URL = "http://localhost:8080/jasperserver/rest_v2/reports/reports/{reportPath}";

    public byte[] generatePdfReport(String reportPath, Map<String, Object> parameters) throws Exception {
        // Configure the client to access JasperReports Server
        JasperPrint jasperPrint = JasperFillManager.fillReport(REPORT_URL, parameters);

        // Export report to PDF
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        exporter.exportReport();

        return outputStream.toByteArray();
    }
}

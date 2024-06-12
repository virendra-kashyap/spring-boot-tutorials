package com.spark.apache.ctl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spark.apache.service.CSVService;

@RestController
@RequestMapping("/api/spark")
public class CSVController {

	@Autowired
    private CSVService sparkService;

    @PostMapping("/processCsv")
    public ResponseEntity<String> processCsv(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file.");
        }
        
        try {
            sparkService.processCsvData(file);
            return ResponseEntity.ok().body("CSV data processing started.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process CSV data.");
        }
    }

}
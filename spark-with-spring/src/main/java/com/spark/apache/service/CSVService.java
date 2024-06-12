package com.spark.apache.service;

import java.io.File;
import java.io.IOException;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVService {

	@Autowired
    private SparkSession sparkSession;

    @Transactional
    public void processCsvData(MultipartFile file) throws IOException {
        // Check if the uploaded file is not empty
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Uploaded file is empty");
        }

        try {
            // Create a temporary file to save the uploaded content
            File tempFile = File.createTempFile("temp", null);
            file.transferTo(tempFile);

            // Read the CSV file using SparkSession
            Dataset<Row> df = sparkSession.read()
                    .format("csv")
                    .option("header", "true")
                    .option("inferSchema", "true")
                    .load(tempFile.getAbsolutePath());

            // Write the DataFrame to the database
            df.write()
                    .format("jdbc")
                    .option("url", "jdbc:mysql://localhost:3306/spark_with_spring")
                    .option("dbtable", "users")
                    .option("user", "root")
                    .option("password", "root")
                    .mode("append") // Choose write mode as per your requirement
                    .save();
        } catch (IOException e) {
            throw new IOException("Error reading the uploaded CSV file", e);
        }
    }

}

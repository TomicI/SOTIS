package com.controller;

import com.model.EyetrackerRecord;
import com.model.ReseniTest;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.service.FileService;
import com.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.core.io.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/file")
public class FileController
{
    @Autowired
    FileService fileService;

    @RequestMapping(value = "/exportFile", method = RequestMethod.GET)
    public ResponseEntity<Resource> exportCSV(@RequestParam(value="reseniTestId") Long reseniTestId) throws Exception
    {

        System.out.println("pogodilo");
        Resource file = fileService.createCSV(reseniTestId);
        Path path = file.getFile()
                .toPath();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);

    }
}

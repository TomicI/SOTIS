package com.service;

import com.model.EyetrackerRecord;
import com.model.ReseniTest;
import com.repository.ReseniTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
@Service
public class FileService
{

    @Autowired
    ReseniTestRepository reseniTestRepository;

    public Resource createCSV(Long reseniTestId) throws MalformedURLException {
        Optional<ReseniTest> reseniTest = reseniTestRepository.findById(reseniTestId);
        File file = null;

        if(reseniTest.isPresent())
        {
            PrintWriter pw = null;
            try {
                file = new File("data" + reseniTest.get().getUcenik().getUsername() + reseniTest.get().getId()+ ".csv");
                file.createNewFile();
                pw = new PrintWriter(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            StringBuilder builder = new StringBuilder();
            String columnNamesList = "TimeStamp, RightEyeX, RightEyeY, LeftEyeX, LeftEyeY";
            // No need give the headers Like: id, Name on builder.append
            builder.append(columnNamesList +"\n");

            for(EyetrackerRecord eyetrackerRecord: reseniTest.get().getRecords())
            {
                builder.append(eyetrackerRecord.getTimestamp() + "," + eyetrackerRecord.getRightEyeX() + "," + eyetrackerRecord.getRightEyeY() + ","
                        + eyetrackerRecord.getLeftEyeX() + "," + eyetrackerRecord.getLeftEyeY());

                builder.append('\n');

            }

            pw.write(builder.toString());
            pw.close();
            System.out.println("done! " + file.getPath());
        }

        if (file != null)
        {
            Path fileP = Paths.get("C:/Users/Ivic/IdeaProjects/novi/SOTIS")
                    .resolve(file.getName());
            Resource resource = new UrlResource(fileP.toUri());

            if (resource.exists() || resource.isReadable()) {
                System.out.println("postoji");
                return resource;
            }
            else
            {
                throw new RuntimeException("Could not read the file!");
            }
        }
        return null;

    }
}

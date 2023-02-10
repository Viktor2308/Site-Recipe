package me.sky.recipe.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.sky.recipe.services.FilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceImpl implements FilesService {
    @Value("${path.to.data.file}")
    private String filesDir;


    @Override
    public void saveToFile(Object object, String fileName) {
        Path path = Path.of(filesDir, fileName + ".json");
        try {
            String json = new ObjectMapper().writeValueAsString(object);
            Files.createDirectories(path.getParent());
            Files.deleteIfExists(path);
            Files.createFile(path);
            Files.writeString(path, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readFromFile(String fileName) {
        try {
            return Files.readString(Path.of(filesDir, fileName + ".json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

package me.sky.recipe.services;

import java.io.File;
import java.nio.file.Path;

/**
 * service for save and read data from files
 */
public interface FilesService {


    void saveToFile(Object object, String fileName);

    String readFromFile(String fileName);

    File getDataFile(String fileName);

    boolean cleanDataFile(String fileName);

    Path createTempFile(String suffix);

}

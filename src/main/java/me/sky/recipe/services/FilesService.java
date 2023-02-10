package me.sky.recipe.services;
/**
 * service for save and read data from files
 */
public interface FilesService {


    void saveToFile(Object object, String fileName);

    String readFromFile(String fileName);
}

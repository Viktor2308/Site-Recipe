package me.sky.recipe.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import me.sky.recipe.exeption.ValidationException;
import me.sky.recipe.model.Recipe;
import me.sky.recipe.services.FilesService;
import me.sky.recipe.services.RecipeService;
import me.sky.recipe.services.ValidationService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static int idRecipe = 0;
    private Map<Integer, Recipe> recipeMap = new LinkedHashMap<>();
    private final ValidationService validationService;
    private final FilesService filesService;
    private static final String DATA_FILE_NAME = "recipeData";

    public RecipeServiceImpl(ValidationService validationService, FilesService filesService) {
        this.validationService = validationService;
        this.filesService = filesService;
    }

    @Override
    public void addNewRecipe(Recipe recipe) {
        if (!validationService.validate(recipe)) {
            throw new ValidationException(recipe.toString());
        }
        recipeMap.put(idRecipe++, recipe);
        filesService.saveToFile(recipeMap, DATA_FILE_NAME);
    }



    @Override
    public List<Recipe> getAllRecipe() {
        return new ArrayList<>(recipeMap.values());
    }

    @Override
    public Recipe getRecipe(int id) {
        return recipeMap.get(id);
    }

    @Override
    public Recipe editRecipe(int id, Recipe recipe) {
        if (recipeMap.containsKey(id)) {
            recipeMap.put(id, recipe);
            filesService.saveToFile(recipeMap, DATA_FILE_NAME);
            return recipe;
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(int id) {
        if (recipeMap.containsKey(id)) {
            recipeMap.remove(id);
            return true;
        }
        return false;
    }
    @PostConstruct
    private void init() {
        readFromFile();
    }

    private void readFromFile() {
        try {
            String json = filesService.readFromFile(DATA_FILE_NAME);
            recipeMap = new ObjectMapper().readValue(json,
                    new TypeReference<LinkedHashMap<Integer, Recipe>>() {
                    });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

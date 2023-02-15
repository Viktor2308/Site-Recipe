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

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static int idRecipe = 0;
    private Map<Integer, Recipe> recipeMap = new HashMap<>();
    private final ValidationService validationService;
    private final FilesService filesService;
    public static final String RECIPE_DATA = "recipeData";

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
        filesService.saveToFile(recipeMap, RECIPE_DATA);
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
            filesService.saveToFile(recipeMap, RECIPE_DATA);
            return recipe;
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(int id) {
        if (recipeMap.containsKey(id)) {
            recipeMap.remove(id);
            filesService.saveToFile(recipeMap, RECIPE_DATA);
            return true;
        }
        return false;
    }

    @PostConstruct
    private void init() {
        try {
            readFromFile();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {
        try {
            String json = filesService.readFromFile(RECIPE_DATA);
            recipeMap = new ObjectMapper().readValue(json,
                    new TypeReference<HashMap<Integer, Recipe>>() {
                    });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Path createAllRecipeReport() throws IOException {
        Path path = filesService.createTempFile("allRecipe");
        for (Recipe recipe : getAllRecipe()) {
            try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                writer.append(recipe.getName() + "\n" +
                        "Время приготовления: " + recipe.getCookingTimeMin() + "\n" +
                        "Ингредиенты" + "\n" + recipe.getIngredientsList() + "\n" +
                        "Инструкция приготовления:" + "\n" + recipe.getCookingInstructionsList());
                writer.append("\n");

            }
        }
        return path;
    }


}

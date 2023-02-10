package me.sky.recipe.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import me.sky.recipe.exeption.ValidationException;
import me.sky.recipe.model.Ingredient;
import me.sky.recipe.services.FilesService;
import me.sky.recipe.services.IngredientService;
import me.sky.recipe.services.ValidationService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static int idIngredients = 0;
    private Map<Integer, Ingredient> ingredientsMap = new LinkedHashMap<>();
    private final ValidationService validationService;
    private final FilesService filesService;
    private static final String DATA_FILE_NAME = "ingredientsData";


    public IngredientServiceImpl(ValidationService validationService, FilesService filesService) {
        this.validationService = validationService;
        this.filesService = filesService;
    }

    @Override
    public void addNewIngredient(Ingredient ingredient) {
        if (!validationService.validate(ingredient)) {
            throw new ValidationException(ingredient.toString());
        }
        ingredientsMap.put(idIngredients++, ingredient);
        filesService.saveToFile(ingredientsMap, DATA_FILE_NAME);
    }

    @Override
    public List<Ingredient> getAllIngredient() {
        return new ArrayList<>(ingredientsMap.values());
    }

    @Override
    public Ingredient getIngredient(int id) {
        return ingredientsMap.get(id);
    }

    @Override
    public Ingredient editIngredient(int id, Ingredient ingredient) {
        if (ingredientsMap.containsKey(id)) {
            ingredientsMap.put(id, ingredient);
            filesService.saveToFile(ingredientsMap, DATA_FILE_NAME);
            return ingredient;
        }
        return null;
    }

    @Override
    public boolean deleteIngredient(int id) {
        if (ingredientsMap.containsKey(id)) {
            ingredientsMap.remove(id);
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
            ingredientsMap = new ObjectMapper().readValue(json,
                    new TypeReference<LinkedHashMap<Integer, Ingredient>>() {
                    });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}

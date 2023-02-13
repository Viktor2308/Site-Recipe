package me.sky.recipe.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.sky.recipe.services.FilesService;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import static me.sky.recipe.services.impl.IngredientServiceImpl.INGREDIENTS_DATA;
import static me.sky.recipe.services.impl.RecipeServiceImpl.RECIPE_DATA;

@RestController
@RequestMapping("/data")
@Tag(name = "Файлы", description = "Выгрузка файлов с данными")
public class FilesController {

    private final FilesService filesService;

    public FilesController(FilesService filesService) {
        this.filesService = filesService;
    }

    @GetMapping("/export/recipe")
    @Operation(
            summary = "Выгрузка рецептов",
            description = "Получения файла всех рецептов"
    )
    public ResponseEntity<InputStreamResource> downloadRecipeFile() throws FileNotFoundException {
        File dataFile = filesService.getDataFile(RECIPE_DATA);
        if (dataFile.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(dataFile));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(dataFile.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename =\"AllRecipe.json\" ")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import/recipe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Загрузка файла с рецептами",
            description = "Загрузка и сохранение .json файла с рецептами вместо существующего"
    )
    public ResponseEntity<Void> uploadRecipeData(@RequestParam MultipartFile file){
        filesService.cleanDataFile(RECIPE_DATA);
        File dataFile = filesService.getDataFile(RECIPE_DATA);

        try (FileOutputStream fos = new FileOutputStream(dataFile)){
            IOUtils.copy(file.getInputStream(),fos);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/import/ingredient", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Загрузка файла с ингредиентами",
            description = "Загрузка и сохранение .json файла с ингредиентами вместо существующего"
    )
    public ResponseEntity<Void> uploadIngredientData(@RequestParam MultipartFile file){
        filesService.cleanDataFile(INGREDIENTS_DATA);
        File dataFile = filesService.getDataFile(INGREDIENTS_DATA);

        try (FileOutputStream fos = new FileOutputStream(dataFile)){
            IOUtils.copy(file.getInputStream(),fos);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
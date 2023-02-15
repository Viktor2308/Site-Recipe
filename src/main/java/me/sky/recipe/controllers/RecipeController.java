package me.sky.recipe.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.sky.recipe.model.Recipe;
import me.sky.recipe.services.RecipeService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;



@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "CRUD - операции по работе с рецептами.")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/")
    @Operation(
            summary = "создание рецепта",
            description = "создание нового рецепта"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "рецепт создан"
            )
    }
    )
    public ResponseEntity<?> create(@RequestBody Recipe recipe) {
        recipeService.addNewRecipe(recipe);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/")
    @Operation(
            summary = "вся коллекция рецептов",
            description = "получения списка всех рецептов"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "рецепты найдены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = Recipe.class
                                            )
                                    )
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Рецепты не найдены"
            )
    }
    )
    public ResponseEntity<List<Recipe>> read() {
        final List<Recipe> recipe = recipeService.getAllRecipe();
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Поиск рецепта",
            description = "получение рецепта по id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "рецепт найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = Recipe.class
                                    )
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "рецепт не найден"
            )
    }
    )
    public ResponseEntity<Recipe> read(@PathVariable(name = "id") int id) {
        final Recipe recipe = recipeService.getRecipe(id);
        return recipe != null
                ? new ResponseEntity<>(recipe, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Редактирование рецепта",
            description = "поиск рецепта и его редактирование по id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт изменен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(

                                            implementation = Recipe.class


                                    )
                            )
                    }
            )
    }
    )
    public ResponseEntity<Recipe> create(@PathVariable int id, @RequestBody Recipe recipe) {
        final Recipe newRecipe = recipeService.editRecipe(id, recipe);
        return newRecipe != null
                ? new ResponseEntity<>(newRecipe, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление рецепта.",
            description = "Поиск рецепта по id и его удаление."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт с id удален."),
            @ApiResponse(
                    responseCode = "404",
                    description = "Нет данных - рецепта с таким id не существует.")
    }
    )
    public ResponseEntity<?> deleteRecipe(@PathVariable(name = "id") int id) {
        return recipeService.deleteRecipe(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/file")
    @Operation(
            summary = "вся коллекция рецептов в файле",
            description = "получения списка всех рецептов в файле"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "рецепты найдены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = Recipe.class
                                            )
                                    )
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Рецепты не найдены"
            )
    }
    )
    public ResponseEntity<?> getAllRecipeReportInFile() {
        try {
            Path path = recipeService.createAllRecipeReport();

            if (Files.size(path) != 0) {
                InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
                return ResponseEntity.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .contentLength(Files.size(path))
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename =\"RecipeReport.txt\" ")
                        .body(resource);
            } else {
                return ResponseEntity.noContent().build();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }
}


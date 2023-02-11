package me.sky.recipe.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.sky.recipe.model.Ingredient;
import me.sky.recipe.services.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингредиенты", description = "CRUD - операции по работе с ингредиентами.")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientsService) {
        this.ingredientService = ingredientsService;
    }

    @GetMapping(value = "/")
    @Operation(
            summary = "вся коллекция ингредиентов",
            description = "получения списка всех ингредиентов"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Ингредиенты найдены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = Ingredient.class
                                            )
                                    )
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ингредиенты не найдены"
            )
    }
    )
    public ResponseEntity<List<Ingredient>> read() {
        final List<Ingredient> ingredient = ingredientService.getAllIngredient();
        return new ResponseEntity<>(ingredient, HttpStatus.OK);
    }

    @PostMapping("/")
    @Operation(
            summary = "создание ингредиента",
            description = "создание нового ингредиента"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "ингредиент создан"
            )
    }
    )
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
        ingredientService.addNewIngredient(ingredient);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Поиск ингредиента",
            description = "получение ингредиента по id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "ингредиент найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = Ingredient.class
                                    )
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Рецепт не найден"
            )
    }
    )
    public ResponseEntity<Ingredient> getIngredient(@PathVariable(name = "id") int id) {
        final Ingredient ingredient = ingredientService.getIngredient(id);
        return ingredient != null
                ? new ResponseEntity<>(ingredient, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Редактирование ингредиента",
            description = "получение ингредиента по id и его редактирование"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "ингредиент изменен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = Ingredient.class
                                            )
                                    )
                            )
                    }
            ),
    }
    )
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int id, @RequestBody Ingredient ingredient) {
        final Ingredient newIngredient = ingredientService.editIngredient(id, ingredient);
        return newIngredient != null
                ? new ResponseEntity<>(newIngredient, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление игредиента.",
            description = "Поиск ингредиента по id и его удаление."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Игредиент с id удален."),
            @ApiResponse(
                    responseCode = "404",
                    description = "Нет данных - ингредиента с таким id не существует.")
    }
    )
    public ResponseEntity<?> deleteIngredient(@PathVariable(name = "id") int id) {
        return ingredientService.deleteIngredient(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

package me.sky.recipe.exeption;

public class ValidationException extends RuntimeException{
    public ValidationException(String entity){
        super("Validation error" + entity);
    }
}

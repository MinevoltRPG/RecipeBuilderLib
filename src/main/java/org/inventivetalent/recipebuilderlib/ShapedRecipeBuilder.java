package org.inventivetalent.recipebuilderlib;

import java.lang.reflect.*;
import org.bukkit.*;
import org.bukkit.material.*;
import java.util.*;
import org.bukkit.inventory.*;

public class ShapedRecipeBuilder extends RecipeBuilder
{
    static Field INGREDIENT_MAP;
    
    public ShapedRecipeBuilder() {
    }
    
    public ShapedRecipeBuilder(final ItemStack result) {
        super(result);
    }
    
    @SuppressWarnings("deprecation")
	@Override
    protected void initRecipe(final ItemStack result) {
        if (this.recipe == null) {
            this.recipe = (Recipe)new ShapedRecipe(result);
        }
    }
    
    private ShapedRecipe getRecipe() {
        return (ShapedRecipe)this.recipe;
    }
    
    @Override
    public ShapedRecipeBuilder forResult(final ItemStack result) {
        return (ShapedRecipeBuilder)super.forResult(result);
    }
    
    public ShapedRecipeBuilder withShape(final String... shape) {
        this.validateInit();
        this.getRecipe().shape(shape);
        return this;
    }
    
    public ShapedRecipeBuilder withIngredient(final char key, final Material ingredient) {
        this.validateInit();
        this.getRecipe().setIngredient(key, ingredient);
        return this;
    }
    
    public ShapedRecipeBuilder withIngredient(final char key, final MaterialData ingredient) {
        this.validateInit();
        this.getRecipe().setIngredient(key, ingredient);
        return this;
    }
    
    @SuppressWarnings("unchecked")
	public ShapedRecipeBuilder withIngredient(final char key, final ItemStack ingredient) {
        this.validateInit();
        try {
            ((Map<Character, ItemStack>)ShapedRecipeBuilder.INGREDIENT_MAP.get(this.getRecipe())).put(key, ingredient);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
    
    public ShapedRecipe build() {
        return (ShapedRecipe)super.build();
    }
    
    static {
        try {
            (ShapedRecipeBuilder.INGREDIENT_MAP = ShapedRecipe.class.getDeclaredField("ingredients")).setAccessible(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

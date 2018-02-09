package org.inventivetalent.recipebuilderlib;

import java.lang.reflect.*;
import org.bukkit.*;
import org.bukkit.material.*;
import java.util.*;
import org.bukkit.inventory.*;

public class ShapelessRecipeBuilder extends RecipeBuilder
{
    static Field RECIPE_LIST;
    
    public ShapelessRecipeBuilder() {
    }
    
    public ShapelessRecipeBuilder(final ItemStack result) {
        super(result);
    }
    
    @SuppressWarnings("deprecation")
	@Override
    protected void initRecipe(final ItemStack result) {
        if (this.recipe == null) {
            this.recipe = (Recipe)new ShapelessRecipe(result);
        }
    }
    
    private ShapelessRecipe getRecipe() {
        return (ShapelessRecipe)this.recipe;
    }
    
    @Override
    public ShapelessRecipeBuilder forResult(final ItemStack result) {
        return (ShapelessRecipeBuilder)super.forResult(result);
    }
    
    public ShapelessRecipeBuilder withIngredient(final Material ingredient) {
        this.validateInit();
        this.getRecipe().addIngredient(ingredient);
        return this;
    }
    
    public ShapelessRecipeBuilder withIngredient(final MaterialData ingredient) {
        this.validateInit();
        this.getRecipe().addIngredient(ingredient);
        return this;
    }
    
    public ShapelessRecipeBuilder withIngredient(final int count, final Material ingredient) {
        this.validateInit();
        this.getRecipe().addIngredient(count, ingredient);
        return this;
    }
    
    public ShapelessRecipeBuilder withIngredient(final int count, final MaterialData ingredient) {
        this.validateInit();
        this.getRecipe().addIngredient(count, ingredient);
        return this;
    }
    
    @SuppressWarnings("unchecked")
	public ShapelessRecipeBuilder withIngredient(final ItemStack ingredient) {
        this.validateInit();
        try {
            ((List<ItemStack>)ShapelessRecipeBuilder.RECIPE_LIST.get(this.getRecipe())).add(ingredient);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
    
    public ShapelessRecipe build() {
        return (ShapelessRecipe)super.build();
    }
    
    static {
        try {
            (ShapelessRecipeBuilder.RECIPE_LIST = ShapelessRecipe.class.getDeclaredField("ingredients")).setAccessible(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

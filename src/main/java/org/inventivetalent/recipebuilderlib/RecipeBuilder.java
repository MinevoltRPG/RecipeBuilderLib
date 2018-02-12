package org.inventivetalent.recipebuilderlib;

import org.bukkit.inventory.*;
import org.bukkit.*;

public abstract class RecipeBuilder
{
    protected Recipe recipe;
    
    protected RecipeBuilder() {
    }
    
    protected RecipeBuilder(final ItemStack result) {
        this.forResult(result);
    }
    
    protected abstract void initRecipe(final ItemStack p0);
    
    protected void validateInit() {
        if (this.recipe == null) {
            throw new IllegalStateException("Recipe not yet initiated");
        }
    }
    
    public RecipeBuilder forResult(final ItemStack result) {
        this.initRecipe(result);
        return this;
    }
    
    public Recipe build() {
        this.validateInit();
        return this.recipe;
    }
    
    public void register() {
        final Recipe recipe = this.build();
        if(recipe instanceof FurnaceRecipe) {
        	   Bukkit.addRecipe((FurnaceRecipe)recipe);
        }else {
        	   Bukkit.addRecipe(recipe);
        }       
    }
}

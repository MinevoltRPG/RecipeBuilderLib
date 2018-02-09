package org.inventivetalent.recipebuilderlib;

import org.bukkit.inventory.*;
import org.bukkit.*;

public abstract class AbstractFurnaceRecipeBuilder
{
	protected FurnaceRecipe recipe;
    
    protected AbstractFurnaceRecipeBuilder() {
    }
    
    protected AbstractFurnaceRecipeBuilder(final ItemStack result) {
        this.forResult(result);
    }
    
    protected abstract void initRecipe(final ItemStack p0);
    
    protected void validateInit() {
        if (this.recipe == null) {
            throw new IllegalStateException("Recipe not yet initiated");
        }
    }
    
    public AbstractFurnaceRecipeBuilder forResult(final ItemStack result) {
        this.initRecipe(result);
        return this;
    }
    
    public FurnaceRecipe build() {
        this.validateInit();
        return this.recipe;
    }
    
    public void register() {
        final FurnaceRecipe recipe = this.build();
        Bukkit.addRecipe(recipe);
    }
}
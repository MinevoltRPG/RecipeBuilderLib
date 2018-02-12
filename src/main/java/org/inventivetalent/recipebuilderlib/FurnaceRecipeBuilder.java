package org.inventivetalent.recipebuilderlib;

import java.lang.reflect.Field;
import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public class FurnaceRecipeBuilder extends RecipeBuilder
{
    static Field RECIPE_ITEM;
    
    public FurnaceRecipeBuilder(final ItemStack result) {
        super(result);
    }
    
    public FurnaceRecipeBuilder() {
	}

	@Override
    protected void initRecipe(final ItemStack result) {
        if (this.recipe == null) {
            this.recipe = (FurnaceRecipe)(new FurnaceRecipe(result, Material.AIR));
        }
    }
    
    private FurnaceRecipe getRecipe() {
        return (FurnaceRecipe)this.recipe;
    }
    
    @Override
    public FurnaceRecipeBuilder forResult(final ItemStack result) {
        return (FurnaceRecipeBuilder)super.forResult(result);
    }
    
    public FurnaceRecipeBuilder withInput(final Material ingredient) {
        this.validateInit();
        this.getRecipe().setInput(ingredient);
        return this;
    }
    
    public FurnaceRecipeBuilder withInput(final MaterialData ingredient) {
        this.validateInit();
        this.getRecipe().setInput(ingredient);
        return this;
    }
    
    public FurnaceRecipeBuilder withInput(final ItemStack ingredient) {
        this.validateInit();
        try {
        	FurnaceRecipeBuilder.RECIPE_ITEM.set(this.getRecipe(), ingredient);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
    
    public FurnaceRecipeBuilder withExp(final float experience) {
    	this.getRecipe().setExperience(experience);
        return this;
    } 
    
    public FurnaceRecipe build() {
        return (FurnaceRecipe)super.build();
    }
    
    static {
        try {
            FurnaceRecipeBuilder.RECIPE_ITEM = FurnaceRecipe.class.getDeclaredField("ingredient");
            FurnaceRecipeBuilder.RECIPE_ITEM.setAccessible(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

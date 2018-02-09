package org.inventivetalent.recipebuilderlib;

import java.lang.reflect.Field;
import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import me.drkmatr1984.reflectionutils.ReflectionUtils;

public class FurnaceRecipeBuilder extends AbstractFurnaceRecipeBuilder
{
    static Field RECIPE_ITEM;
    
    public FurnaceRecipeBuilder() {
    }
    
    public FurnaceRecipeBuilder(final ItemStack result) {
        super(result);
    }
    
    @Override
    protected void initRecipe(final ItemStack result) {
        if (this.recipe == null) {
            this.recipe = (FurnaceRecipe)new FurnaceRecipe(result, Material.AIR);
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
        	ReflectionUtils.setField(FurnaceRecipeBuilder.RECIPE_ITEM, "ingredient", ingredient);
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
            (FurnaceRecipeBuilder.RECIPE_ITEM = FurnaceRecipe.class.getDeclaredField("ingredient")).setAccessible(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
package org.inventivetalent.recipebuilderlib;

import java.lang.reflect.Field;
import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.inventory.Recipe;

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
            this.recipe = (Recipe)(new FurnaceRecipe(result, Material.AIR));
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
        	setField(FurnaceRecipeBuilder.RECIPE_ITEM, "ingredient", ingredient);
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
    
    public Recipe build() {
        return (Recipe)super.build();
    }
    
    static {
        try {
            (FurnaceRecipeBuilder.RECIPE_ITEM = FurnaceRecipe.class.getDeclaredField("ingredient")).setAccessible(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Sets a field value on a given object
     *
     * @param targetObject the object to set the field value on
     * @param fieldName    exact name of the field
     * @param fieldValue   value to set on the field
     * @return true if the value was successfully set, false otherwise
     */
     public static boolean setField(Object targetObject, String fieldName, Object fieldValue) {
        Field field;
        try {
            field = targetObject.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            field = null;
        }
        @SuppressWarnings("rawtypes")
		  Class superClass = targetObject.getClass().getSuperclass();
        while (field == null && superClass != null) {
            try {
                field = superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                superClass = superClass.getSuperclass();
            }
        }
        if (field == null) {
            return false;
        }
        field.setAccessible(true);
        try {
            field.set(targetObject, fieldValue);
            return true;
        } catch (IllegalAccessException e) {
            return false;
        }
     }	
}

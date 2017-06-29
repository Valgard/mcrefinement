package com.trontheim.mcrefinement.util;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public final class RecipePrinter
{

  private RecipePrinter()
  {
  }

  public static String print(IRecipe recipe)
  {
    String representation;

    List<String> inputs = new ArrayList<String>();

    if("net.minecraft.item.crafting.ShapedRecipes".equals(recipe.getClass().getName())) {
      ShapedRecipes shapedRecipe = (ShapedRecipes) recipe;
      for (ItemStack input : shapedRecipe.recipeItems) {
        inputs.add(input.getItem().getUnlocalizedName());
      }
      representation = "(shaped) " + StringUtils.join(inputs, " + ") + " => " + recipe.getRecipeOutput().getItem().getUnlocalizedName();
    }
    else if("net.minecraft.item.crafting.ShapelessRecipes".equals(recipe.getClass().getName())) {
      ShapelessRecipes shapelessRecipe = (ShapelessRecipes) recipe;
      for (Object recipeItem : shapelessRecipe.recipeItems) {
        ItemStack input = (ItemStack) recipeItem;
        inputs.add(input.getItem().getUnlocalizedName());
      }
      representation = "(shapeless) " + StringUtils.join(inputs, " + ") + " -> " + recipe.getRecipeOutput().getItem().getUnlocalizedName();
    }
    else if("net.minecraftforge.oredict.ShapelessOreRecipe".equals(recipe.getClass().getName())) {
      ShapelessOreRecipe shapelessOreRecipe = (ShapelessOreRecipe) recipe;
      for (Object o : shapelessOreRecipe.getInput()) {
        ItemStack input = (ItemStack) o;
        inputs.add(input.getItem().getUnlocalizedName());
      }
      representation = "(shapeless ore) " + StringUtils.join(inputs, " + ") + " -> " + recipe.getRecipeOutput().getItem().getUnlocalizedName();
    }
    else {
      representation = recipe.toString();
    }

    return representation;
  }

  public static String print(ItemStack input, ItemStack output)
  {
    return "(furnace) " + input.getItem().getUnlocalizedName() + " => " + output.getItem().getUnlocalizedName();
  }

}

package me.joshua.bedrockthings.util;

import me.joshua.bedrockthings.BedrockThings;
import net.minecraft.item.crafting.IRecipeType;

public class RecipeTypeStarRecipe implements IRecipeType<StarRecipe> {

	@Override
	public String toString() {
		BedrockThings.log(BedrockThings.MOD_ID + ":star_recipe");
		return BedrockThings.MOD_ID + ":star_recipe";

	}

}
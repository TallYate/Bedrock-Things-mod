package me.joshua.bedrockthings.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.unimi.dsi.fastutil.ints.IntList;
import me.joshua.bedrockthings.BedrockThings;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class StarRecipe implements ICraftingRecipe {
	
	public static final Serializer SERIALIZER = new Serializer();
	
	private final ResourceLocation id;
	private final String group;
	private final ItemStack recipeOutput;
	private final NonNullList<Ingredient> recipeItems;
	private final boolean isSimple;
	private final Item keep;

	public StarRecipe(ResourceLocation idIn, String groupIn, ItemStack recipeOutputIn,
			NonNullList<Ingredient> recipeItemsIn, Item keepIn) {
		this.id = idIn;
		this.group = groupIn;
		this.recipeOutput = recipeOutputIn;
		this.recipeItems = recipeItemsIn;
		this.isSimple = recipeItemsIn.stream().allMatch(Ingredient::isSimple);
		this.keep = keepIn;
	}

	public ResourceLocation getId() {
		return this.id;
	}

	public IRecipeSerializer<?> getSerializer() {
		return SERIALIZER;
	}

	public String getGroup() {
		return this.group;
	}

	public ItemStack getRecipeOutput() {
		return this.recipeOutput;
	}

	public NonNullList<Ingredient> getIngredients() {
		return this.recipeItems;
	}

	public Item getKeep() {
		return this.keep;
	}

	public boolean matches(CraftingInventory inv, World worldIn) {
	      RecipeItemHelper recipeitemhelper = new RecipeItemHelper();
	      java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
	      int i = 0;

	      for(int j = 0; j < inv.getSizeInventory(); ++j) {
	         ItemStack itemstack = inv.getStackInSlot(j);
	         if (!itemstack.isEmpty()) {
	            ++i;
	            if (isSimple)
	            recipeitemhelper.func_221264_a(itemstack, 1);
	            else inputs.add(itemstack);
	         }
	      }
	      return i == this.recipeItems.size() && (isSimple ? recipeitemhelper.canCraft(this, (IntList)null) : net.minecraftforge.common.util.RecipeMatcher.findMatches(inputs,  this.recipeItems) != null);
	   }
	
	public ItemStack getCraftingResult(CraftingInventory inv) {
		ItemStack toReturn = this.recipeOutput.copy();
		for (int i = 0; i < inv.getSizeInventory(); ++i) {
			ItemStack itemstack = inv.getStackInSlot(i);
			if (itemstack.getItem() == this.keep) {
				toReturn.setTag(itemstack.getTag().copy());
				toReturn.getOrCreateTag().putInt(BedrockThings.MOD_ID + ":stored_damage", toReturn.getTag().getInt("Damage"));
				toReturn.getTag().remove("Damage");
				return toReturn;
			}
		}
		
		return toReturn;
	}

	public boolean canFit(int width, int height) {
		return width * height >= this.recipeItems.size();
	}

	private static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<StarRecipe> {
		Serializer() {
			this.setRegistryName(new ResourceLocation(BedrockThings.MOD_ID, "star_recipe"));
		}

		public StarRecipe read(ResourceLocation recipeId, JsonObject json) {
			String s = JSONUtils.getString(json, "group", "");
			NonNullList<Ingredient> nonnulllist = readIngredients(  JSONUtils.getJsonArray(json, "ingredients")  );
        	ItemStack itemstack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
 			Item keep = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "keep")).getItem();
 			return new StarRecipe(recipeId, s, itemstack, nonnulllist, keep);
		}
		
		public StarRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
	         String s = buffer.readString(32767);
	         ItemStack itemstack = buffer.readItemStack();
	         Item keep = buffer.readItemStack().getItem();
	         int i = buffer.readVarInt();
	         
	         NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);
	         for(int j = 0; j < nonnulllist.size(); ++j) {
	            nonnulllist.set(j, Ingredient.read(buffer));
	         }

	         return new StarRecipe(recipeId, s, itemstack, nonnulllist, keep);
	      }

		private static NonNullList<Ingredient> readIngredients(JsonArray json) {
			NonNullList<Ingredient> nonnulllist = NonNullList.create();

			for (int i = 0; i < json.size(); ++i) {
				Ingredient ingredient = Ingredient.deserialize(json.get(i));
				if (!ingredient.hasNoMatchingItems()) {
					nonnulllist.add(ingredient);
				}
			}

			return nonnulllist;
		}

		@Override
		public void write(PacketBuffer buffer, StarRecipe recipe) {
			buffer.writeString(recipe.group);
			buffer.writeItemStack(recipe.recipeOutput);
			buffer.writeItemStack(new ItemStack(recipe.keep));
			buffer.writeVarInt(recipe.recipeItems.size());
			
			for (Ingredient ingredient : recipe.recipeItems) {
				ingredient.write(buffer);
			}
			
		}

	}

	/*
	@Override
	public IRecipeType<?> getType() {
		FirstMod.log("the RECIPE type is " + FirstMod.STAR_RECIPE.toString());
		return FirstMod.STAR_RECIPE;
	}
	*/
}

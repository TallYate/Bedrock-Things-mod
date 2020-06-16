package me.joshua.bedrockthings;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.joshua.bedrockthings.itemtypes.util;
import me.joshua.bedrockthings.util.KeyboardHelper;
import me.joshua.bedrockthings.util.RecipeTypeStarRecipe;
import me.joshua.bedrockthings.util.StarRecipe;
import me.joshua.bedrockthings.util.init.EntityInit;
import me.joshua.bedrockthings.util.init.ItemInit;
import me.joshua.bedrockthings.world.gen.ModOreGen;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.gui.widget.ModListWidget.ModEntry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("bedrockthings")
@Mod.EventBusSubscriber(modid = BedrockThings.MOD_ID, bus = Bus.MOD)
public class BedrockThings {
	// Directly reference a log4j logger.
	private static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "bedrockthings";
	public static BedrockThings instance;

	public static final IRecipeType<StarRecipe> STAR_RECIPE = new RecipeTypeStarRecipe();

	public BedrockThings() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::setup);
		modEventBus.addListener(this::doClientStuff);
		modEventBus.addGenericListener(IRecipeSerializer.class, this::registerRecipeSerializers);
		instance = this;
		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
		
		EntityInit.ENTITY_TYPES.register(modEventBus);
	}

	private void setup(final FMLCommonSetupEvent event) {

	}

	private void doClientStuff(final FMLClientSetupEvent event) {

	}

	@SubscribeEvent
	public static void loadCompleteEvent(FMLLoadCompleteEvent event) {
		ModOreGen.generateOre();
	}

	public static void log(String info) {
		LOGGER.info(info);
	}
	
	private void registerRecipeSerializers(Register<IRecipeSerializer<?>> event) {
		Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(STAR_RECIPE.toString()), STAR_RECIPE);
		event.getRegistry().register(StarRecipe.SERIALIZER);
	}
	
	
	
	
	private Map<ResourceLocation, IRecipe<?>> getRecipes(IRecipeType<?> recipeType, RecipeManager manager) {

		final Map<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>> recipesMap = ObfuscationReflectionHelper
				.getPrivateValue(RecipeManager.class, manager, "field_199522_d");

		return recipesMap.get(recipeType);

	}
	/*
	 * public static class BedrockTab extends ItemGroup { public static final
	 * BedrockTab instance = new BedrockTab(ItemGroup.GROUPS.length, "TAB"); private
	 * BedrockTab(int index, String label) { super(index, label); }
	 * 
	 * @Override public ItemStack createIcon() { ItemStack icon = new
	 * ItemStack(ItemInit.test_item); return icon; } }
	 */
}
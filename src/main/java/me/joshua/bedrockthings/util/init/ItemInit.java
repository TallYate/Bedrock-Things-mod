package me.joshua.bedrockthings.util.init;

import me.joshua.bedrockthings.BedrockThings;
import me.joshua.bedrockthings.itemtypes.BedrockPickaxeItem;
import me.joshua.bedrockthings.itemtypes.BedrockSwordItem;
import me.joshua.bedrockthings.itemtypes.ChargedDiamondArmorItem;
import me.joshua.bedrockthings.itemtypes.ChargedDiamondAxeItem;
import me.joshua.bedrockthings.itemtypes.ChargedDiamondHoeItem;
import me.joshua.bedrockthings.itemtypes.ChargedDiamondPickaxeItem;
import me.joshua.bedrockthings.itemtypes.ChargedDiamondShovelItem;
import me.joshua.bedrockthings.itemtypes.ChargedDiamondSwordItem;
import me.joshua.bedrockthings.util.enums.ModArmorMaterial;
import me.joshua.bedrockthings.util.enums.ModItemTier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.EnchantedGoldenAppleItem;
import net.minecraft.item.Food;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.item.ShovelItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = BedrockThings.MOD_ID, bus = Bus.MOD)
@ObjectHolder(BedrockThings.MOD_ID)
public class ItemInit {
	// Items
	public static final Item test_item = null;
	public static final Item nether_star_chunk = null;

	// Foods
	public static final Item bedrock_apple = null;

	// Tools
	public static final Item bedrock_sword = null;
	public static final Item bedrock_shovel = null;
	public static final Item bedrock_pickaxe = null;
	public static final Item bedrock_axe = null;
	public static final Item bedrock_hoe = null;

	public static final Item charged_diamond_sword = null;
	public static final Item charged_diamond_shovel = null;
	public static final Item charged_diamond_pickaxe = null;
	public static final Item charged_diamond_axe = null;
	public static final Item charged_diamond_hoe = null;

	// Armor
	public static final Item bedrock_helmet = null;
	public static final Item bedrock_chestplate = null;
	public static final Item bedrock_leggings = null;
	public static final Item bedrock_boots = null;

	public static final Item charged_diamond_helmet = null;
	public static final Item charged_diamond_chestplate = null;
	public static final Item charged_diamond_leggings = null;
	public static final Item charged_diamond_boots = null;

	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {

		// Items
		event.getRegistry().register(new Item(new Item.Properties()).setRegistryName("test_item"));
		event.getRegistry().register(
				new Item(new Item.Properties().group(ItemGroup.MATERIALS).rarity(Rarity.UNCOMMON).maxStackSize(4))
						.setRegistryName("nether_star_chunk"));

		// Foods EnchantedGoldenAppleItem is just for enchanted glint
		event.getRegistry()
				.register(new EnchantedGoldenAppleItem(new Item.Properties().group(ItemGroup.FOOD).rarity(Rarity.EPIC)
						.food(new Food.Builder().hunger(10).saturation(10).setAlwaysEdible().fastToEat()
								.effect(() -> new EffectInstance(Effects.RESISTANCE, 2400, 4), 1).build()))
										.setRegistryName("bedrock_apple"));

		// Tools
		event.getRegistry()
				.register(new BedrockSwordItem(ModItemTier.BEDROCK, 15, -2.2F, new Item.Properties().group(ItemGroup.COMBAT))
						.setRegistryName("bedrock_sword"));
		event.getRegistry()
				.register(new ShovelItem(ModItemTier.BEDROCK, 7, -3.0F, new Item.Properties().group(ItemGroup.TOOLS))
						.setRegistryName("bedrock_shovel"));
		event.getRegistry()
				.register(new BedrockPickaxeItem(ModItemTier.BEDROCK, 9, -3.0F, new Item.Properties().group(ItemGroup.TOOLS))
						.setRegistryName("bedrock_pickaxe"));
		event.getRegistry()
				.register(new AxeItem(ModItemTier.BEDROCK, 30, -3.5F, new Item.Properties().group(ItemGroup.TOOLS))
						.setRegistryName("bedrock_axe"));
		event.getRegistry().register(new HoeItem(ModItemTier.BEDROCK, -2F, new Item.Properties().group(ItemGroup.TOOLS))
				.setRegistryName("bedrock_hoe"));

		event.getRegistry().register(new ChargedDiamondSwordItem(ModItemTier.CHARGED_DIAMOND, 15, -2.2F,
				new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName("charged_diamond_sword"));
		event.getRegistry().register(new ChargedDiamondShovelItem(ModItemTier.CHARGED_DIAMOND, 7, -3.0F,
				new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName("charged_diamond_shovel"));
		event.getRegistry().register(new ChargedDiamondPickaxeItem(ModItemTier.CHARGED_DIAMOND, 9, -3.0F,
				new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName("charged_diamond_pickaxe"));
		event.getRegistry().register(new ChargedDiamondAxeItem(ModItemTier.CHARGED_DIAMOND, 30, -3.5F,
				new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName("charged_diamond_axe"));
		event.getRegistry().register(new ChargedDiamondHoeItem(ModItemTier.CHARGED_DIAMOND, -2F,
				new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName("charged_diamond_hoe"));

		// Armor
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.BEDROCK, EquipmentSlotType.HEAD,
				new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName("bedrock_helmet"));
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.BEDROCK, EquipmentSlotType.CHEST,
				new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName("bedrock_chestplate"));
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.BEDROCK, EquipmentSlotType.LEGS,
				new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName("bedrock_leggings"));
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.BEDROCK, EquipmentSlotType.FEET,
				new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName("bedrock_boots"));

		event.getRegistry()
				.register(new ChargedDiamondArmorItem(ModArmorMaterial.CHARGED_DIAMOND, EquipmentSlotType.HEAD,
						new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName("charged_diamond_helmet"));
		event.getRegistry()
				.register(new ChargedDiamondArmorItem(ModArmorMaterial.CHARGED_DIAMOND, EquipmentSlotType.CHEST,
						new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName("charged_diamond_chestplate"));
		event.getRegistry()
				.register(new ChargedDiamondArmorItem(ModArmorMaterial.CHARGED_DIAMOND, EquipmentSlotType.LEGS,
						new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName("charged_diamond_leggings"));
		event.getRegistry()
				.register(new ChargedDiamondArmorItem(ModArmorMaterial.CHARGED_DIAMOND, EquipmentSlotType.FEET,
						new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName("charged_diamond_boots"));
	}

}
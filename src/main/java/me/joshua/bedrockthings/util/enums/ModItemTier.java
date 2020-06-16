package me.joshua.bedrockthings.util.enums;

import java.util.function.Supplier;

import me.joshua.bedrockthings.util.init.ItemInit;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

public enum ModItemTier implements IItemTier {
	BEDROCK(10, 1000000, 25.0F, -1.0F, 250, () -> {
		return Ingredient.fromItems(Items.BEDROCK);
	}),

	CHARGED_DIAMOND(10, 4, 25.0F, -1.0F, 250, () -> {
		return Ingredient.fromItems(ItemInit.nether_star_chunk);
	});

	private final int harvestLevel;
	private final int maxUses;
	private final float efficiency;
	private final float attackDamage;
	private final int enchantibility;
	private final LazyValue<Ingredient> repairMaterial;

	private ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantibility,
			Supplier<Ingredient> repairMaterial) {
		this.harvestLevel = harvestLevel;
		this.maxUses = maxUses;
		this.efficiency = efficiency;
		this.attackDamage = attackDamage;
		this.enchantibility = enchantibility;
		this.repairMaterial = new LazyValue<Ingredient>(repairMaterial);
	}

	@Override
	public int getMaxUses() {
		return this.maxUses;
	}

	@Override
	public float getEfficiency() {
		return this.efficiency;
	}

	@Override
	public float getAttackDamage() {
		return this.attackDamage;
	}

	@Override
	public int getHarvestLevel() {
		return this.harvestLevel;
	}

	@Override
	public int getEnchantability() {
		return this.enchantibility;
	}

	@Override
	public Ingredient getRepairMaterial() {
		return this.repairMaterial.getValue();
	}
}
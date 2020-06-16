package me.joshua.bedrockthings.itemtypes;

import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import me.joshua.bedrockthings.BedrockThings;
import me.joshua.bedrockthings.util.KeyboardHelper;
import me.joshua.bedrockthings.util.init.ItemInit;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ChargedDiamondArmorItem extends ArmorItem {
	public ChargedDiamondArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Item.Properties builder) {
		super(materialIn, slot, builder);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new TranslationTextComponent(
					"\u00A7e" + "Charged Uses Left " + "\u00A7f" + (stack.getMaxDamage() - stack.getDamage())));
			tooltip.add(new TranslationTextComponent("\u00A77" + "Uses Left " + "\u00A7f" + (util.resultItem(stack.getItem()).getMaxDamage()
					- stack.getTag().getInt(BedrockThings.MOD_ID + ":stored_damage"))));
		}
	}

	@Override
	public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
		if (amount + stack.getDamage() < stack.getMaxDamage()) {
			return amount;
		} else {
			ItemStack newstack = new ItemStack(util.resultItem(stack.getItem()));
			newstack.setTag(stack.getTag());
			newstack.setDamage(newstack.getTag().getInt(BedrockThings.MOD_ID + ":stored_damage"));
			newstack.getTag().remove(BedrockThings.MOD_ID + ":stored_damage");
			if(stack.getItem()==ItemInit.charged_diamond_helmet) {
				entity.setItemStackToSlot(EquipmentSlotType.HEAD, newstack);
			}
			else if(stack.getItem()==ItemInit.charged_diamond_chestplate) {
				entity.setItemStackToSlot(EquipmentSlotType.CHEST, newstack);
			}
			else if(stack.getItem()==ItemInit.charged_diamond_leggings) {
				entity.setItemStackToSlot(EquipmentSlotType.LEGS, newstack);
			}
			else if(stack.getItem()==ItemInit.charged_diamond_boots) {
				entity.setItemStackToSlot(EquipmentSlotType.FEET, newstack);
			}
		}
		return 0;
	}
}
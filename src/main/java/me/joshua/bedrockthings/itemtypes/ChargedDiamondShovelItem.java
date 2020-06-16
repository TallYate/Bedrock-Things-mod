package me.joshua.bedrockthings.itemtypes;

import java.util.List;

import javax.annotation.Nullable;

import me.joshua.bedrockthings.BedrockThings;
import me.joshua.bedrockthings.util.KeyboardHelper;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ChargedDiamondShovelItem extends ShovelItem
{
	public ChargedDiamondShovelItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, Item.Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
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
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
			if(stack.getDamage()>=(stack.getMaxDamage()-2)){
				attacker.sendBreakAnimation(EquipmentSlotType.MAINHAND);
    	  		ItemStack newStack = new ItemStack(Items.DIAMOND_SHOVEL);
    	  		newStack.setTag(stack.getTag());
    	  		newStack.setDamage(stack.getTag().getInt(BedrockThings.MOD_ID + ":stored_damage"));
    	  		newStack.getTag().remove(BedrockThings.MOD_ID + ":stored_damage");
    	  		attacker.setHeldItem(Hand.MAIN_HAND, newStack);
		  	}
			else {
		      stack.damageItem(2, attacker, (p_220039_0_) -> {
		         p_220039_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
		      });
			}
	      return true;
	   }
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
	   
      if (!worldIn.isRemote && state.getBlockHardness(worldIn, pos) != 0.0F) {
    	  	if(stack.getDamage()>=(stack.getMaxDamage()-1)){
    	  		entityLiving.sendBreakAnimation(EquipmentSlotType.MAINHAND);
    	  		ItemStack newStack = new ItemStack(Items.DIAMOND_SHOVEL);
    	  		newStack.setTag(stack.getTag());
    	  		newStack.setDamage(stack.getTag().getInt(BedrockThings.MOD_ID + ":stored_damage"));
    	  		newStack.getTag().remove(BedrockThings.MOD_ID + ":stored_damage");
    	  		entityLiving.setHeldItem(Hand.MAIN_HAND, newStack);
    	  	}
    	  	else {
	    		stack.damageItem(1, entityLiving, (p_220038_0_) -> {
	            p_220038_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
    		});
		}
      }
      return true;
   }
	
}
package me.joshua.bedrockthings.itemtypes;

import java.util.List;

import javax.annotation.Nullable;

import me.joshua.bedrockthings.BedrockThings;
import me.joshua.bedrockthings.util.KeyboardHelper;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ChargedDiamondHoeItem extends HoeItem
{
	public ChargedDiamondHoeItem(IItemTier tier, float attackSpeedIn, Item.Properties builder) {
		super(tier, attackSpeedIn, builder);
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
			if(stack.getDamage()>=(stack.getMaxDamage()-1)){
				attacker.sendBreakAnimation(EquipmentSlotType.MAINHAND);
    	  		ItemStack newStack = new ItemStack(Items.DIAMOND_HOE);
    	  		newStack.setTag(stack.getTag());
    	  		newStack.setDamage(stack.getTag().getInt(BedrockThings.MOD_ID + ":stored_damage"));
    	  		newStack.getTag().remove(BedrockThings.MOD_ID + ":stored_damage");
    	  		attacker.setHeldItem(Hand.MAIN_HAND, newStack);
		  	}
			else {
		      stack.damageItem(1, attacker, (p_220039_0_) -> {
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
    	  		ItemStack newStack = new ItemStack(Items.DIAMOND_HOE);
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
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
	      World world = context.getWorld();
	      BlockPos blockpos = context.getPos();
	      int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(context);
	      if (hook != 0) return hook > 0 ? ActionResultType.SUCCESS : ActionResultType.FAIL;
	      if (context.getFace() != Direction.DOWN && world.isAirBlock(blockpos.up())) {
	         BlockState blockstate = HOE_LOOKUP.get(world.getBlockState(blockpos).getBlock());
	         if (blockstate != null) {
	            PlayerEntity playerentity = context.getPlayer();
	            world.playSound(playerentity, blockpos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
	            if (!world.isRemote) {
	               world.setBlockState(blockpos, blockstate, 11);
	               if (playerentity != null) {
	            	  if(context.getItem().getDamage()>=context.getItem().getMaxDamage()-1) {
	            		context.getPlayer().sendBreakAnimation(EquipmentSlotType.MAINHAND);
	          	  		ItemStack newStack = new ItemStack(Items.DIAMOND_HOE);
	          	  		newStack.setTag(context.getItem().getTag());
	          	  		newStack.setDamage(context.getItem().getTag().getInt(BedrockThings.MOD_ID + ":stored_damage"));
	          	  		newStack.getTag().remove(BedrockThings.MOD_ID + ":stored_damage");
	          	  		context.getPlayer().setHeldItem(context.getHand(), newStack);
	            	  }
	            	  else {
	                  context.getItem().damageItem(1, playerentity, (p_220043_1_) -> {
	                     p_220043_1_.sendBreakAnimation(context.getHand());
	                  });
	            	  }
	               }
	            }

	            return ActionResultType.SUCCESS;
	         }
	      }

	      return ActionResultType.PASS;
	   }
	
}
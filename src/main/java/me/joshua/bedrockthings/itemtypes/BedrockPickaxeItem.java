package me.joshua.bedrockthings.itemtypes;

import java.util.List;

import javax.annotation.Nullable;

import me.joshua.bedrockthings.BedrockThings;
import me.joshua.bedrockthings.util.KeyboardHelper;
import me.joshua.bedrockthings.util.init.BlockInit;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BedrockPickaxeItem extends PickaxeItem{
	public BedrockPickaxeItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
      super(tier, attackDamageIn, attackSpeedIn, builder);
   }
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent(
				"\u00A7b" + "Shift + Right-Click: Bust Bedrock"));
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new TranslationTextComponent(
					"\u00A77" + "  Cost: 1 000 Durability"));
			tooltip.add(new TranslationTextComponent(
					"\u00A77" + "Uses Left " + "\u00A7f" + (stack.getMaxDamage() - stack.getDamage())));
		}
	}
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		if( context.getPlayer().isSneaking() && (!context.getWorld().isRemote)) {
			if(context.getWorld().getBlockState(context.getPos()).getBlock()==Blocks.BEDROCK) {
				context.getWorld().setBlockState(context.getPos(), BlockInit.busted_bedrock.getDefaultState(), 3);
				context.getItem().damageItem(10000, context.getPlayer(), player -> {});
			}
			return ActionResultType.SUCCESS;
		}
		else
		{
			return ActionResultType.FAIL;
		}
   }
}

package me.joshua.bedrockthings.itemtypes;

import java.util.List;

import javax.annotation.Nullable;

import com.ibm.icu.util.ULocale.Category;

import me.joshua.bedrockthings.BedrockThings;
import me.joshua.bedrockthings.util.AccurateSkull;
import me.joshua.bedrockthings.util.KeyboardHelper;
import me.joshua.bedrockthings.util.init.BlockInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.EntitySelectionContext;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;


public class BedrockSwordItem extends SwordItem
{
	public BedrockSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent(
				"\u00A7b" + "Shift + Right-Click: Launch Wither Skull"));
		if (KeyboardHelper.isHoldingShift()) {
			tooltip.add(new TranslationTextComponent(
					"\u00A77" + "  Cost: 1 000 Durability"));
			tooltip.add(new TranslationTextComponent(
					"\u00A77" + "Uses Left " + "\u00A7f" + (stack.getMaxDamage() - stack.getDamage())));
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		if(player.isSneaking()&&(!world.isRemote)) {
			Vec3d look = player.getLookVec();
			Vec3d pos = player.getPositionVec();
			AccurateSkull skull = new AccurateSkull(world, player, look.x*2, look.y*2, look.z*2);
			skull.setPosition(pos.x, player.getPosYEye(), pos.z);
			world.playSound(player, player.getPosition(), SoundEvents.ENTITY_WITHER_SHOOT, SoundCategory.PLAYERS , 1.0F, 1.0F);
			world.addEntity(skull);
			player.getHeldItem(hand).damageItem(100, player, Player -> {});
			return ActionResult.resultSuccess(player.getHeldItem(hand));
		}
		else {
			return ActionResult.resultFail(player.getHeldItem(hand));
		}
	}
	
}
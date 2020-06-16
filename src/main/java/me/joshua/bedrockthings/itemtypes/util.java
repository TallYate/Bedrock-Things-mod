package me.joshua.bedrockthings.itemtypes;

import me.joshua.bedrockthings.BedrockThings;
import me.joshua.bedrockthings.util.KeyboardHelper;
import me.joshua.bedrockthings.util.init.ItemInit;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = BedrockThings.MOD_ID, bus = Bus.MOD)
public class util{
	
	
	public static Item resultItem(Item input) {
		if(input==ItemInit.charged_diamond_sword){
			return Items.DIAMOND_SWORD;
		}
		else if(input==ItemInit.charged_diamond_pickaxe){
			return Items.DIAMOND_PICKAXE;
		}
		else if(input==ItemInit.charged_diamond_axe){
			return Items.DIAMOND_AXE;
		}
		else if(input==ItemInit.charged_diamond_shovel){
			return Items.DIAMOND_SHOVEL;
		}
		else if(input==ItemInit.charged_diamond_hoe){
			return Items.DIAMOND_HOE;
		}
		else if(input==ItemInit.charged_diamond_helmet){
			return Items.DIAMOND_HELMET;
		}
		else if(input==ItemInit.charged_diamond_chestplate){
			return Items.DIAMOND_CHESTPLATE;
		}
		else if(input==ItemInit.charged_diamond_leggings){
			return Items.DIAMOND_LEGGINGS;
		}
		else if(input==ItemInit.charged_diamond_boots){
			return Items.DIAMOND_BOOTS;
		}
		
		else {
			return ItemInit.test_item;
		}
	}
}
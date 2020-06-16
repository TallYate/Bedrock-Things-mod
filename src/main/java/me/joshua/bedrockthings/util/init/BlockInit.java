package me.joshua.bedrockthings.util.init;

import me.joshua.bedrockthings.BedrockThings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = BedrockThings.MOD_ID, bus = Bus.MOD)
@ObjectHolder(BedrockThings.MOD_ID)
public class BlockInit{
	public static final Block busted_bedrock = null;
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(360.0F, 1000000.0F).harvestLevel(10).sound(SoundType.STONE)).setRegistryName("busted_bedrock"));
	}
	
	@SubscribeEvent
	public static void registerBlockItems(final RegistryEvent.Register<Item> event)
	{	
		event.getRegistry().register(new BlockItem(busted_bedrock, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("busted_bedrock"));
	}
}

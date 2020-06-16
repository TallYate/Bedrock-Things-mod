package me.joshua.bedrockthings.world.gen;

import javax.tools.DocumentationTool.Location;

import me.joshua.bedrockthings.util.init.BlockInit;

import javax.tools.DocumentationTool.Location;

import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.command.arguments.LocationInput;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class ModOreGen {
	public static void generateOre() {
		ConfiguredPlacement lowConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(200, 3, 1, 5));
		ConfiguredPlacement highConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(400, 121, 3, 126));
		for (Biome biome : ForgeRegistries.BIOMES) {
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
					Feature.ORE
							.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.create("bedrock", "BEDROCK", new BlockMatcher(Blocks.BEDROCK)),
									BlockInit.busted_bedrock.getDefaultState(), 3))
							.withPlacement(lowConfig));
			
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
					Feature.ORE
							.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.create("bedrock", "BEDROCK", new BlockMatcher(Blocks.BEDROCK)),
									BlockInit.busted_bedrock.getDefaultState(), 3))
							.withPlacement(highConfig));
		}	
	}
}

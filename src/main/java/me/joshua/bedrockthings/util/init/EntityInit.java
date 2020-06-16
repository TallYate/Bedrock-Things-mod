package me.joshua.bedrockthings.util.init;

import me.joshua.bedrockthings.BedrockThings;
import me.joshua.bedrockthings.util.AccurateSkull;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityInit {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, BedrockThings.MOD_ID);
	
	public static final RegistryObject<EntityType<AccurateSkull>> ACCURATE_SKULL = ENTITY_TYPES.register("accurate_skull", () -> EntityType.Builder.<AccurateSkull>create(AccurateSkull::new, EntityClassification.MISC).size(0.3125F, 0.3125F).build(new ResourceLocation(BedrockThings.MOD_ID, "accurate_skull").toString()));
}

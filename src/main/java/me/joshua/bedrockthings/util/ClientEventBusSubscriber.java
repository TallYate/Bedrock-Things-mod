package me.joshua.bedrockthings.util;

import me.joshua.bedrockthings.BedrockThings;
import me.joshua.bedrockthings.renderer;
import me.joshua.bedrockthings.util.init.EntityInit;
import me.joshua.bedrockthings.util.init.ItemInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = BedrockThings.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.ACCURATE_SKULL.get(), renderer::new);
		
		ItemInit.nether_star_chunk.addPropertyOverride(new ResourceLocation(BedrockThings.MOD_ID, "count"),
				new IItemPropertyGetter() {
					@Override
					public float call(ItemStack stack, World worldIn, LivingEntity entityIn) {
						switch (stack.getCount()) {
						case 1:
							return 0.25f;
						case 2:
							return 0.5f;
						case 3:
							return 0.75f;
						case 4:
							return 1.0f;
						default:
							return 0.25f;
						}
					}
				});
	}
}

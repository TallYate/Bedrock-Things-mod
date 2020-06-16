package me.joshua.bedrockthings;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import me.joshua.bedrockthings.util.AccurateSkull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.WitherSkullRenderer;
import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class renderer extends AccurateSkullRenderer{

	public renderer(EntityRendererManager renderManager) {
		super(renderManager);
	}
	
	public void render(AccurateSkull entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		if(entityIn.shootingEntity==null) {
		}
		else if(entityIn.ticksExisted<3) {
			return;
		}
		else if( Minecraft.getInstance().player.getEntityId() == entityIn.shootingEntity.getEntityId() && Minecraft.getInstance().gameSettings.thirdPersonView==0 && entityIn.ticksExisted<5) {
			return;
		}
		else {
			super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		}
	}

}


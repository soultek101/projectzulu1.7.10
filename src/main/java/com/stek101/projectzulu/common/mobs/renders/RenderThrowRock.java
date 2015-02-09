package com.stek101.projectzulu.common.mobs.renders;



	import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.stek101.projectzulu.common.api.ItemList;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.core.ItemGenerics;
import com.stek101.projectzulu.common.mobs.entity.EntityThrowingRock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

	@SideOnly(Side.CLIENT)
	public class RenderThrowRock extends Render implements RenderWrapper
	{
	    private static final ResourceLocation thrownRockTextures = new ResourceLocation(DefaultProps.blockKey, "textures/items/throwingrock.png");

	    /**
	     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
	     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
	     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
	     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
	     */
	    public void doRender(EntityThrowingRock p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
	    {
	    	
			IIcon icon;
	        if (ItemList.genericCraftingItems.isPresent()) {
	            icon = ItemGenerics.Properties.ThrowingRock.getIcon();
	        } else {
	            icon = Items.fire_charge.getIconFromDamage(0);
	        }
	        
		//	if (icon != null)
		//	{
				this.bindEntityTexture(p_76986_1_);
		        GL11.glPushMatrix();
		        GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
		        GL11.glRotatef(p_76986_1_.prevRotationYaw + (p_76986_1_.rotationYaw - p_76986_1_.prevRotationYaw) * p_76986_9_ - 90.0F, 0.0F, 1.0F, 0.0F);
		        GL11.glRotatef(p_76986_1_.prevRotationPitch + (p_76986_1_.rotationPitch - p_76986_1_.prevRotationPitch) * p_76986_9_, 0.0F, 0.0F, 1.0F);
		        
		        Tessellator tessellator = Tessellator.instance;
		        
		        this.func_77026_a(tessellator, icon);
				GL11.glDisable(GL12.GL_RESCALE_NORMAL);
				GL11.glPopMatrix();
		/*        byte b0 = 0;
		        float f2 = 0.0F;
		        float f3 = 0.5F;
		        float f4 = (float)(0 + b0 * 10) / 32.0F;
		        float f5 = (float)(5 + b0 * 10) / 32.0F;
		        float f6 = 0.0F;
		        float f7 = 0.15625F;
		        float f8 = (float)(5 + b0 * 10) / 32.0F;
		        float f9 = (float)(10 + b0 * 10) / 32.0F;
		        float f10 = 0.05625F;
		        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		    //    float f11 = (float)p_76986_1_.rockShake - p_76986_9_;
	
		    //    if (f11 > 0.0F)
		    //    {
		   //         float f12 = -MathHelper.sin(f11 * 3.0F) * f11;
		    //        GL11.glRotatef(f12, 0.0F, 0.0F, 1.0F);
		    //    }
	
		        GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
		        GL11.glScalef(f10, f10, f10);
		        GL11.glTranslatef(-4.0F, 0.0F, 0.0F);
		        GL11.glNormal3f(f10, 0.0F, 0.0F);
		        tessellator.startDrawingQuads();
		        tessellator.addVertexWithUV(-7.0D, -2.0D, -2.0D, (double)f6, (double)f8);
		        tessellator.addVertexWithUV(-7.0D, -2.0D, 2.0D, (double)f7, (double)f8);
		        tessellator.addVertexWithUV(-7.0D, 2.0D, 2.0D, (double)f7, (double)f9);
		        tessellator.addVertexWithUV(-7.0D, 2.0D, -2.0D, (double)f6, (double)f9);
		        tessellator.draw();
		        GL11.glNormal3f(-f10, 0.0F, 0.0F);
		        tessellator.startDrawingQuads();
		        tessellator.addVertexWithUV(-7.0D, 2.0D, -2.0D, (double)f6, (double)f8);
		        tessellator.addVertexWithUV(-7.0D, 2.0D, 2.0D, (double)f7, (double)f8);
		        tessellator.addVertexWithUV(-7.0D, -2.0D, 2.0D, (double)f7, (double)f9);
		        tessellator.addVertexWithUV(-7.0D, -2.0D, -2.0D, (double)f6, (double)f9);
		        tessellator.draw();
	
		     //   for (int i = 0; i < 4; ++i)
		     //   {
		            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
		            GL11.glNormal3f(0.0F, 0.0F, f10);
		            tessellator.startDrawingQuads();
		            tessellator.addVertexWithUV(-8.0D, -2.0D, 0.0D, (double)f2, (double)f4);
		            tessellator.addVertexWithUV(8.0D, -2.0D, 0.0D, (double)f3, (double)f4);
		            tessellator.addVertexWithUV(8.0D, 2.0D, 0.0D, (double)f3, (double)f5);
		            tessellator.addVertexWithUV(-8.0D, 2.0D, 0.0D, (double)f2, (double)f5);
		            tessellator.draw();
		    //    }
	*/
		        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		        GL11.glPopMatrix();
			//}
	    }
	    
	    /**
	     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	     */
	    protected ResourceLocation getEntityTexture(EntityThrowingRock p_110775_1_)
	    {
	        return thrownRockTextures;
	    }

	    /**
	     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	     */
	    @Override
	    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	    {
	    	///return TextureMap.locationItemsTexture;
	    	return this.getEntityTexture((EntityThrowingRock)p_110775_1_);
	    	//return thrownRockTextures;
	    }

	    /**
	     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
	     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
	     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
	     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
	     */
	    public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
	    {
	        this.doRender((EntityThrowingRock)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	    }

		@Override
		public Render getRender() {
			return this;
		}
		
		private void func_77026_a(Tessellator par1Tessellator, IIcon par2Icon){
			float f = par2Icon.getMinU();
			float f1 = par2Icon.getMaxU();
			float f2 = par2Icon.getMinV();
			float f3 = par2Icon.getMaxV();
			float f4 = 1.0F;
			float f5 = 0.5F;
			float f6 = 0.25F;
			GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
			par1Tessellator.startDrawingQuads();
			par1Tessellator.setNormal(0.0F, 1.0F, 0.0F);
			par1Tessellator.addVertexWithUV((double)(0.0F - f5), (double)(0.0F - f6), 0.0D, (double)f, (double)f3);
			par1Tessellator.addVertexWithUV((double)(f4 - f5), (double)(0.0F - f6), 0.0D, (double)f1, (double)f3);
			par1Tessellator.addVertexWithUV((double)(f4 - f5), (double)(f4 - f6), 0.0D, (double)f1, (double)f2);
			par1Tessellator.addVertexWithUV((double)(0.0F - f5), (double)(f4 - f6), 0.0D, (double)f, (double)f2);
			par1Tessellator.draw();
		}

}

package com.stek101.projectzulu.common.mobs.renders;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.stek101.projectzulu.common.api.ItemList;
import com.stek101.projectzulu.common.core.ItemGenerics;
import com.stek101.projectzulu.common.mobs.entity.EntityDuckEgg;

public class RenderDuckEgg extends Render implements RenderWrapper  {
	
		private Item field_94151_a;
		private int field_94150_f;
		private float field_77002_a;

	    public RenderDuckEgg(float par1) {
	        this.field_77002_a = par1;
	    }

		/**
		* Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
		* handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
		* (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
		* double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
		*/
	public void doRenderDuckEgg(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		//IIcon icon = ItemGenerics.Properties.DuckEgg.getIcon(); 
		//this.field_94151_a.getIconFromDamage(this.field_94150_f);
			
			IIcon icon;
	        if (ItemList.genericCraftingItems.isPresent()) {
	            icon = ItemGenerics.Properties.DuckEgg.getIcon();
	        } else {
	            icon = Items.fire_charge.getIconFromDamage(0);
	        }

		if (icon != null)
		{
			GL11.glPushMatrix();
			GL11.glTranslatef((float)par2, (float)par4, (float)par6);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			// this.func_110777_b(par1Entity); // worked in Forge 804, but no longer; use this:
			this.bindEntityTexture(par1Entity);
			Tessellator tessellator = Tessellator.instance;
	
			// You can remove this whole section if you want, it's just for Potions
			if (icon == ItemPotion.func_94589_d("bottle_splash"))
				{
				int i = PotionHelper.func_77915_a(((EntityPotion)par1Entity).getPotionDamage(), false);
				float f2 = (float)(i >> 16 & 255) / 255.0F;
				float f3 = (float)(i >> 8 & 255) / 255.0F;
				float f4 = (float)(i & 255) / 255.0F;
				GL11.glColor3f(f2, f3, f4);
				GL11.glPushMatrix();
				this.func_77026_a(tessellator, ItemPotion.func_94589_d("overlay"));
				GL11.glPopMatrix();
				GL11.glColor3f(1.0F, 1.0F, 1.0F);
				}

			this.func_77026_a(tessellator, icon);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
		}
	}
		// For Forge 804:
		/*
		protected ResourceLocation func_110775_a(Entity par1Entity)
		{
		return TextureMap.field_110576_c;
		}
		*/
		// For Forge 871:
	    @Override
	protected ResourceLocation getEntityTexture(Entity entity) {
	        return TextureMap.locationItemsTexture;
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
		
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
	        this.doRenderDuckEgg((EntityDuckEgg) par1Entity, par2, par4, par6, par8, par9);
	 }

	@Override
	public Render getRender() {
			// TODO Auto-generated method stub
			return this;
	}
 }
package com.stek101.projectzulu.common.mobs.renders;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.mobs.entity.EntityGenericCreature;

public class RenderGenericModelBipedMT extends RenderGenericLiving {
    protected  ModelBiped modelBipedMain;
	public final List <ResourceLocation> entityTexture = new ArrayList <ResourceLocation>();

    public RenderGenericModelBipedMT(ModelBase modelBase, float shadowSize, List <String> wildTextures) {
        super(modelBase, shadowSize,  new ResourceLocation(DefaultProps.mobKey, wildTextures.get(0)));
        
        for (int i = 0; i < wildTextures.size(); i++) {
            entityTexture.add(new ResourceLocation(DefaultProps.mobKey, wildTextures.get(i)));
        }
    	
        this.modelBipedMain = (ModelBiped) modelBase;
    }

    public void renderGenModelBiped(EntityGenericCreature entity, double par2, double par4, double par6,
            float par8, float par9) {
        super.doRender(entity, par2, par4, par6, par8, par9);
        //BossHealthDisplayTicker.registerEntity(entityMummyPharaoh);
    }

    @Override
    protected void renderEquippedItems(EntityLivingBase par1EntityLiving, float par2) {
        super.renderEquippedItems(par1EntityLiving, par2);
        ItemStack var3 = par1EntityLiving.getHeldItem();
        if (var3 != null) {
            GL11.glPushMatrix();            
            this.modelBipedMain.bipedRightArm.postRender(0.0425F);
            GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
            float var4;

            if (var3.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(var3.getItem()).getRenderType())) {
                var4 = 0.5F;
                GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
                var4 *= 0.75F;
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(var4, -var4, var4);
            } else if (var3.getItem() == Items.bow) {
                var4 = 0.625F;
                GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(var4, -var4, var4);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            } else if (var3.getItem().isFull3D()) {
                var4 = 0.625F;
                GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
                GL11.glScalef(var4, -var4, var4);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            } else {
                var4 = 0.375F;
                GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
                GL11.glScalef(var4, var4, var4);
                GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
            }

            this.renderManager.itemRenderer.renderItem(par1EntityLiving, var3, 0);

            if (var3.getItem().requiresMultipleRenderPasses()) {
                this.renderManager.itemRenderer.renderItem(par1EntityLiving, var3, 1);
            }

            GL11.glPopMatrix();
        }
    }

    @Override
    public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8,
            float par9) {
        this.renderGenModelBiped((EntityGenericCreature) par1EntityLiving, par2, par4, par6, par8, par9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probability, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderGenModelBiped((EntityGenericCreature) par1Entity, par2, par4, par6, par8, par9);
    }
    
	 protected ResourceLocation getTexture(EntityGenericCreature par1Entity)
	    {
		 return entityTexture.get(par1Entity.textureID);
	    }
	 
	 @Override
	    protected ResourceLocation getEntityTexture(Entity par1Entity)
	    {
	        return this.getTexture((EntityGenericCreature)par1Entity);
	    }
}

package com.stek101.projectzulu.common.mobs.renders;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.stek101.projectzulu.common.mobs.entity.EntityHauntedArmor;
import com.stek101.projectzulu.common.mobs.models.ModelHauntedArmor;

public class RenderHauntedArmor extends RenderGenericLiving {

    protected ModelHauntedArmor hauntedModel;

    public RenderHauntedArmor(ModelBase modelBase, float shadowSize, ResourceLocation textureLocation) {
        super(modelBase, shadowSize, textureLocation);        
        // Unused Resource Location so It doesn't yell in the log
        hauntedModel = (ModelHauntedArmor) modelBase;        
    }
    
    public void renderHauntedArmor(EntityHauntedArmor entityHA, double par2, double par4, double par6,
            float par8, float par9) {
        super.doRender(entityHA, par2, par4, par6, par8, par9);
        //BossHealthDisplayTicker.registerEntity(entityHA);
    }

    @Override
    protected void renderEquippedItems(EntityLivingBase par1EntityLiving, float par2) {
        renderItemInHand(par1EntityLiving);
    }

    private void renderItemInHand(EntityLivingBase par1EntityLiving) {
        ItemStack var3 = par1EntityLiving.getHeldItem();
        if (var3 != null) {
            GL11.glPushMatrix();
            hauntedModel.swordhand.postRender(0.0625F);
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
        this.renderHauntedArmor((EntityHauntedArmor) par1EntityLiving, par2, par4, par6, par8, par9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probability, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderHauntedArmor((EntityHauntedArmor) par1Entity, par2, par4, par6, par8, par9);
    }
}

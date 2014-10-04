package com.stek101.projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import com.stek101.projectzulu.common.mobs.entity.EntityCrow;
import com.stek101.projectzulu.common.mobs.entity.EntityStates;

public class ModelCrow extends ModelBase {

	//fields

    ModelRenderer HEAD;
    ModelRenderer BILL;
    ModelRenderer neck;
    ModelRenderer BODY;
    ModelRenderer RWING;
    ModelRenderer LWING;
    ModelRenderer RLEG;
    ModelRenderer LLEG;
    ModelRenderer tail;
  
  public ModelCrow()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      HEAD = new ModelRenderer(this, 1, 0);
      HEAD.addBox(-2F, -3F, -3F, 3, 3, 4);
      HEAD.setRotationPoint(0.5F, 14.5F, -2F);
      HEAD.setTextureSize(64, 64);
      HEAD.mirror = true;
      setRotation(HEAD, 0F, 0F, 0F);
      BILL = new ModelRenderer(this, 1, 31);
      BILL.addBox(-1F, -1F, -1F, 2, 2, 4);
      BILL.setRotationPoint(-0.5F, 0.0F, -5F);
      BILL.setTextureSize(64, 64);
      BILL.mirror = true;
      setRotation(BILL, 0F, 0F, 0F);
      HEAD.addChild(BILL);
      neck = new ModelRenderer(this, 40, 3);
      neck.addBox(-1F, -1F, -1F, 3, 3, 4);
      neck.setRotationPoint(-0.5F, 14F, -2F);
      neck.setTextureSize(64, 64);
      neck.mirror = true;
      setRotation(neck, -1.029744F, 0F, 0F);
      BODY = new ModelRenderer(this, 1, 10);
      BODY.addBox(-3F, -4F, -3F, 6, 4, 8);
      BODY.setRotationPoint(0F, 19F, 0F);
      BODY.setTextureSize(64, 64);
      BODY.mirror = true;
      setRotation(BODY, 0F, 0F, 0F);
      RWING = new ModelRenderer(this, 30, 11);
      RWING.addBox(-1F, -1F, -3F, 1, 5, 8);
      RWING.setRotationPoint(-3F, 16F, 1F);
      RWING.setTextureSize(64, 64);
      RWING.mirror = true;
      setRotation(RWING, 0F, 0F, 0F);
      LWING = new ModelRenderer(this, 30, 26);
      LWING.addBox(0F, -1F, -3F, 1, 5, 8);
      LWING.setRotationPoint(3F, 16F, 1F);
      LWING.setTextureSize(64, 64);
      LWING.mirror = true;
      setRotation(LWING, 0F, 0F, 0F);
      RLEG = new ModelRenderer(this, 23, 0);
      RLEG.addBox(-1F, 0F, -3F, 3, 5, 3);
      RLEG.setRotationPoint(-2F, 17F, 2F);
      RLEG.setTextureSize(64, 64);
      RLEG.mirror = true;
      setRotation(RLEG, 0F, 0F, 0F);
      LLEG = new ModelRenderer(this, 23, 0);
      LLEG.addBox(-1F, 0F, -3F, 3, 5, 3);
      LLEG.setRotationPoint(1F, 17F, 2F);
      LLEG.setTextureSize(64, 64);
      LLEG.mirror = true;
      setRotation(LLEG, 0F, 0F, 0F);
      tail = new ModelRenderer(this, 1, 25);
      tail.addBox(-1.5F, -2F, 0F, 3, 1, 4);
      tail.setRotationPoint(-0.5F, 18F, 4F);
      tail.setTextureSize(64, 64);
      tail.mirror = true;
      setRotation(tail, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
   
    super.render(entity, f, f1, f2, f3, f4, f5);
	setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	float field_78145_g = 8.0F;
	float field_78151_h = 4.0F;

	if (this.isChild) {
	    float var8 = 2.0F;
	    GL11.glPushMatrix();
	    GL11.glTranslatef(0.0F, field_78145_g * f5, field_78151_h * f5);
	    HEAD.render(f5);
	    GL11.glPopMatrix();
	    GL11.glPushMatrix();
	    GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
	    GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
	    BODY.render(f5);
	    BODY.render(f5);
	    RWING.render(f5);
	    LWING.render(f5);
	    tail.render(f5);	   
	    GL11.glPopMatrix();
	} else {
	    HEAD.render(f5);
	    //BILL.render(f5);
	    neck.render(f5);
	    BODY.render(f5);
	    RWING.render(f5);
	    LWING.render(f5);
	    RLEG.render(f5);
	    LLEG.render(f5);
	    tail.render(f5);
	}
  }
  
  @Override
  public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
	EntityCrow var5 = (EntityCrow) par1EntityLiving;
	if (var5.getEntityState() == EntityStates.posture) {
	    /* On Ground Idle Standing Animation */
	} else {
	    LWING.rotateAngleZ = MathHelper.cos(var5.worldObj.getWorldTime() * 0.6662F * 2f) * 1.8F * 0.5f;
	    RWING.rotateAngleZ = MathHelper.cos(var5.worldObj.getWorldTime() * 0.6662F * 2f
		    + (float) Math.PI) * 1.8F * 0.5f;
	    tail.rotateAngleX = MathHelper.cos(var5.worldObj.getWorldTime() * 0.6662F * 2f + (float) Math.PI) * 0.3f;
	}
  }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
	model.rotateAngleX = x;
	model.rotateAngleY = y;
	model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity) {
	super.setRotationAngles(f, f1, f2, f3, f4, f5, par7Entity);
	HEAD.rotateAngleX = Math.min(Math.max(f4, -15), +15) * (float) (Math.PI / 180f);
	HEAD.rotateAngleY = Math.min(Math.max(f3, -45), +45) * (float) (Math.PI / 180f);
    }
}

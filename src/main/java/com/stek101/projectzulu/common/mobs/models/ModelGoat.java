package com.stek101.projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class ModelGoat extends ModelBase
{
    float heightToRaise = 2f;
    float renderScale = 1.0f;
  //fields
    ModelRenderer HEADROT;
    ModelRenderer hornLeft;
    ModelRenderer hornRight;
    ModelRenderer earLeft;
    ModelRenderer earRight;
    ModelRenderer goatBeard;
    ModelRenderer neck;
    ModelRenderer BODYROT;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer tail;
  
  public ModelGoat()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      HEADROT = new ModelRenderer(this, 1, 1);
      HEADROT.addBox(-1.5F, -5F, -7F, 5, 4, 7);
      HEADROT.setRotationPoint(-2F, 7F  - heightToRaise, -6F);
      HEADROT.setTextureSize(64, 64);
      HEADROT.mirror = true;
      setRotation(HEADROT, 0.2230717F, 0F, 0F);
      hornLeft = new ModelRenderer(this, 28, 5);
      hornLeft.addBox(0F, -3F, 0F, 1, 4, 1);      
    //hornLeft.setRotationPoint(0F, 2F, -9F);
      hornLeft.setRotationPoint(2F, -5F, -3F);
      hornLeft.setTextureSize(64, 64);
      hornLeft.mirror = true;
      setRotation(hornLeft, -0.4833166F, 0F, 0F);
      HEADROT.addChild(hornLeft);
      hornRight = new ModelRenderer(this, 34, 5);
      hornRight.addBox(-1F, -3F, 0F, 1, 4, 1);
    //hornRight.setRotationPoint(-2F, 2F, -9F);
      hornRight.setRotationPoint(0F, -5F, -3F);
      hornRight.setTextureSize(64, 64);
      hornRight.mirror = true;
      setRotation(hornRight, -0.4833166F, 0F, 0F);
      HEADROT.addChild(hornRight);
      earLeft = new ModelRenderer(this, 43, 6);
      earLeft.addBox(0F, 1F, 0F, 1, 2, 1);
      //earLeft.setRotationPoint(0F, 3.5F, -9F);
      earLeft.setRotationPoint(2F, -3.5F, -3F);
      earLeft.setTextureSize(64, 64);
      earLeft.mirror = true;
      setRotation(earLeft, 0F, 0F, -1.256637F);
      HEADROT.addChild(earLeft);
      earRight = new ModelRenderer(this, 50, 6);
      earRight.addBox(0F, 1F, 0F, 1, 2, 1);
       //earRight.setRotationPoint(-2.5F, 2.5F, -9F);
      earRight.setRotationPoint(-0.5F, -4.5F, -3F);
      earRight.setTextureSize(64, 64);
      earRight.mirror = true;
      setRotation(earRight, 0F, 0F, 1.256637F);
      HEADROT.addChild(earRight);
      goatBeard = new ModelRenderer(this, 0, 0);
      goatBeard.addBox(0F, 1F, 0F, 1, 3, 2);
       //goatBeard.setRotationPoint(-1.5F, 5F, -12F);
      goatBeard.setRotationPoint(-0.0F, -2F, -6F);
      goatBeard.setTextureSize(64, 64);
      goatBeard.mirror = true;
      setRotation(goatBeard, -0.2541571F, 0F, 0F);
      HEADROT.addChild(goatBeard);
      neck = new ModelRenderer(this, 28, 12);
      neck.addBox(0F, -2F, -2F, 4, 3, 4);
      neck.setRotationPoint(-3F, 7F  - heightToRaise, -7F);
      neck.setTextureSize(64, 64);
      neck.mirror = true;
      setRotation(neck, -0.9294653F, 0F, 0F);
      BODYROT = new ModelRenderer(this, 28, 32);
      BODYROT.addBox(-4F, -8F, -7F, 8, 14, 6);
      BODYROT.setRotationPoint(-1F, 6F  - heightToRaise, 1F);
      BODYROT.setTextureSize(64, 64);
      BODYROT.mirror = true;
      setRotation(BODYROT, 1.570796F, 0F, 0F);
      leg1 = new ModelRenderer(this, 0, 16);
      leg1.addBox(-1F, 0F, -1F, 3, 8, 3);
      leg1.setRotationPoint(-4F, 13F  - heightToRaise, 5F);
      leg1.setTextureSize(64, 64);
      leg1.mirror = true;
      setRotation(leg1, 0F, 0F, 0F);
      leg2 = new ModelRenderer(this, 13, 16);
      leg2.addBox(-1F, 0F, -1F, 3, 8, 3);
      leg2.setRotationPoint(1F, 13F  - heightToRaise, 5F);
      leg2.setTextureSize(64, 64);
      leg2.mirror = true;
      setRotation(leg2, 0F, 0F, 0F);
      leg3 = new ModelRenderer(this, 13, 32);
      leg3.addBox(-2F, 0F, -1F, 3, 8, 3);
      leg3.setRotationPoint(-3F, 13F  - heightToRaise, -6F);
      leg3.setTextureSize(64, 64);
      leg3.mirror = true;
      setRotation(leg3, 0F, 0F, 0F);
      leg4 = new ModelRenderer(this, 0, 32);
      leg4.addBox(-1F, 0F, -1F, 3, 8, 3);
      leg4.setRotationPoint(1F, 13F  - heightToRaise, -6F);
      leg4.setTextureSize(64, 64);
      leg4.mirror = true;
      setRotation(leg4, 0F, 0F, 0F);
      tail = new ModelRenderer(this, 28, 24);
      tail.addBox(-1F, 0F, 0F, 2, 1, 5);
      tail.setRotationPoint(-1F, 9F  - heightToRaise, 6F);
      tail.setTextureSize(64, 64);
      tail.mirror = true;
      setRotation(tail, 0.3892394F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);  
  
    float field_78145_x = 0.0f;
    float field_78145_g = 2.2F;
    float field_78151_h = 3.4F;

    if (this.isChild) {
    	 float var8 = 2.0F;
         GL11.glPushMatrix();
         GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
         GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
         //GL11.glTranslatef(field_78145_x, field_78145_g * f5, field_78151_h * f5);
         HEADROT.render(renderScale * f5);
         neck.render(renderScale * f5);
         BODYROT.render(renderScale * f5);
         leg1.render(renderScale * f5);
		 leg2.render(renderScale * f5);
		 leg3.render(renderScale * f5);
		 leg4.render(renderScale * f5);
		 tail.render(renderScale * f5);		    
         GL11.glPopMatrix();
    } else {
    	HEADROT.render(renderScale * f5);
        neck.render(renderScale * f5);
        BODYROT.render(renderScale * f5);
        leg1.render(renderScale * f5);
		 leg2.render(renderScale * f5);
		 leg3.render(renderScale * f5);
		 leg4.render(renderScale * f5);
		 tail.render(renderScale * f5);		
        //hornLeft.render(f5);
        //hornRight.render(f5);
        //earRight.render(f5);
        //earLeft.render(f5);
        //goatBeard.render(f5);
    }    
  }  
    
  @Override
  public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
	/* Constant Animation Rotations */
	leg1.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f) * 1.2F * par3;
	leg3.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f + (float) Math.PI) * 1.2F * par3;
	leg2.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f + (float) Math.PI) * 1.2F * par3;
	leg4.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f) * 1.2F * par3;
	super.setLivingAnimations(par1EntityLiving, par2, par3, par4);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    HEADROT.rotateAngleX = Math.min(Math.max(f4, -14), +15) * (float) (Math.PI / 180f);
	HEADROT.rotateAngleY = Math.min(Math.max(f3, -15), +15) * (float) (Math.PI / 180f);
	
  }

}

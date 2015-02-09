package com.stek101.projectzulu.common.mobs.models;

import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import com.stek101.projectzulu.common.mobs.entity.EntityGenericAnimal;
import com.stek101.projectzulu.common.mobs.entity.EntityStates;

public class ModelBloomDoom extends ModelBase
{
	 //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer armLeftA1;
    ModelRenderer armLeftA2;
    ModelRenderer armLeftB1;
    ModelRenderer armRightA1;
    ModelRenderer armRightA2;
    ModelRenderer armRightB1;
    ModelRenderer lowerBody;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer leg5;
    ModelRenderer leg6;
    ModelRenderer leg7;
    ModelRenderer leg8;
  
  public ModelBloomDoom()
  {
	  textureWidth = 128;
	    textureHeight = 32;
	    
	      Shape1 = new ModelRenderer(this, 69, 1);
	      Shape1.addBox(-8F, 0F, 0F, 16, 30, 0);
	      Shape1.setRotationPoint(-1F, -8F, 0F);
	      Shape1.setTextureSize(128, 32);
	      Shape1.mirror = true;
	      setRotation(Shape1, 0F, 0F, 0F);
	      Shape2 = new ModelRenderer(this, 0, 1);
	      Shape2.addBox(-8F, 0F, 0F, 16, 30, 0);
	      Shape2.setRotationPoint(0F, -8F, 0F);
	      Shape2.setTextureSize(128, 32);
	      Shape2.mirror = true;
	      setRotation(Shape2, 0F, 1.570796F, 0F);
	      armLeftA1 = new ModelRenderer(this, 51, 12);
	      armLeftA1.addBox(-1.5F, -1F, 0F, 3, 9, 0);
	      armLeftA1.setRotationPoint(3.5F, 10F, -1F);
	      armLeftA1.setTextureSize(128, 32);
	      armLeftA1.mirror = true;
	      setRotation(armLeftA1, -1.396263F, 0F, 0F);
	      armLeftA2 = new ModelRenderer(this, 61, 15);
	      armLeftA2.addBox(-1.5F, -1F, 0F, 3, 7, 0);
	      armLeftA2.setRotationPoint(0F, 8.5F, 0F);
	      armLeftA2.setTextureSize(128, 32);
	      armLeftA2.mirror = true;
	      setRotation(armLeftA2, 0F, 0F, 0F);
	      armLeftA1.addChild(armLeftA2);
	      armLeftB1 = new ModelRenderer(this, 55, 1);
	      armLeftB1.addBox(0.5F, -7F, 0F, 1, 7, 0);
	      armLeftB1.setRotationPoint(2F, 0F, 0F);
	      armLeftB1.setTextureSize(128, 32);
	      armLeftB1.mirror = true;
	      setRotation(armLeftB1, 0F, 0F, 0F);
	      armRightA1 = new ModelRenderer(this, 51, 22);
	      armRightA1.addBox(-1.5F, -1F, 0F, 3, 9, 0);
	      armRightA1.setRotationPoint(-4.5F, 10F, -1F);
	      armRightA1.setTextureSize(128, 32);
	      armRightA1.mirror = true;
	      setRotation(armRightA1, -1.396263F, 0F, 0F);
	      armRightA2 = new ModelRenderer(this, 61, 23);
	      armRightA2.addBox(-1.5F, -1F, 0F, 3, 7, 0);
	      armRightA2.setRotationPoint(0F, 8.5F, 0F);
	      armRightA2.setTextureSize(128, 32);
	      armRightA2.mirror = true;
	      setRotation(armRightA2, 0F, 0F, 0F);
	      armRightA1.addChild(armRightA2);
	      armRightB1 = new ModelRenderer(this, 51, 1);
	      armRightB1.addBox(-1.5F, -7F, 0F, 1, 7, 0);
	      armRightB1.setRotationPoint(-2F, 0F, -1F);
	      armRightB1.setTextureSize(128, 32);
	      armRightB1.mirror = true;
	      setRotation(armRightB1, 0F, 0F, 0F);
	      lowerBody = new ModelRenderer(this, 102, 22);
	      lowerBody.addBox(-4F, 0F, -2F, 6, 3, 6);
	      lowerBody.setRotationPoint(1F, 20F, -1F);
	      lowerBody.setTextureSize(128, 32);
	      lowerBody.mirror = true;
	      setRotation(lowerBody, 0F, 0F, 0F);
	      leg1 = new ModelRenderer(this, 36, 12);
	      leg1.addBox(-5F, 0F, -1F, 5, 1, 1);
	      leg1.setRotationPoint(-2F, 21F, 2F);
	      leg1.setTextureSize(128, 32);
	      leg1.mirror = true;
	      setRotation(leg1, 0F, 0.4363323F, -0.3839724F);
	      leg2 = new ModelRenderer(this, 36, 18);
	      leg2.addBox(0F, 0F, -1F, 5, 1, 1);
	      leg2.setRotationPoint(2F, 21F, 2F);
	      leg2.setTextureSize(128, 32);
	      leg2.mirror = true;
	      setRotation(leg2, 0F, -0.4363323F, 0.3839724F);
	      leg3 = new ModelRenderer(this, 36, 0);
	      leg3.addBox(-5F, 0F, -1F, 5, 1, 1);
	      leg3.setRotationPoint(-2F, 21F, -1F);
	      leg3.setTextureSize(128, 32);
	      leg3.mirror = true;
	      setRotation(leg3, 0F, -0.4363323F, -0.3839724F);
	      leg4 = new ModelRenderer(this, 36, 6);
	      leg4.addBox(0F, 0F, -1F, 5, 1, 1);
	      leg4.setRotationPoint(2F, 21F, -1.033333F);
	      leg4.setTextureSize(128, 32);
	      leg4.mirror = true;
	      setRotation(leg4, 0F, 0.4363323F, 0.3839724F);
	      leg5 = new ModelRenderer(this, 36, 23);
	      leg5.addBox(-1F, 0F, -2F, 1, 5, 1);
	      leg5.setRotationPoint(2F, 22F, -1F);
	      leg5.setTextureSize(128, 32);
	      leg5.mirror = true;
	      setRotation(leg5, -0.9424778F, -0.1745329F, 0F);
	      leg6 = new ModelRenderer(this, 36, 23);
	      leg6.addBox(-1F, 0F, 0F, 1, 5, 1);
	      leg6.setRotationPoint(-1F, 21F, 1F);
	      leg6.setTextureSize(128, 32);
	      leg6.mirror = true;
	      setRotation(leg6, 0.9424778F, -0.1745329F, 0F);
	      leg7 = new ModelRenderer(this, 41, 23);
	      leg7.addBox(-1F, 0F, -2F, 1, 5, 1);
	      leg7.setRotationPoint(-1F, 22F, -1F);
	      leg7.setTextureSize(128, 32);
	      leg7.mirror = true;
	      setRotation(leg7, -0.9424778F, 0.1745329F, 0F);
	      leg8 = new ModelRenderer(this, 41, 23);
	      leg8.addBox(-1F, 0F, 0F, 1, 5, 1);
	      leg8.setRotationPoint(2F, 21F, 1F);
	      leg8.setTextureSize(128, 32);
	      leg8.mirror = true;
	      setRotation(leg8, 0.9424778F, 0.1745329F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape1.render(f5);
    Shape2.render(f5);
    armLeftA1.render(f5);
    //armLeftA2.render(f5);
    //armLeftB1.render(f5);
    armRightA1.render(f5);
    //armRightA2.render(f5);
    //armRightB1.render(f5);
    lowerBody.render(f5);
    leg1.render(f5);
    leg2.render(f5);
    leg3.render(f5);
    leg4.render(f5);
    leg5.render(f5);
    leg6.render(f5);
    leg7.render(f5);
    leg8.render(f5);
  }
  
  @Override
  public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
	
	  EntityGenericAnimal var5 = (EntityGenericAnimal) par1EntityLiving;

    /* State Based Animations */
    if (var5.getEntityState() != EntityStates.idle) {
    	 float heightToRaise = 0.0f;
    	    Shape1.rotationPointY = -8F - heightToRaise;
    	    Shape2.rotationPointY = -8F - heightToRaise;
    	    armLeftA1.rotationPointY = 10F - heightToRaise;
    	    armLeftA2.rotationPointY = 8.5F - heightToRaise;
    	    armLeftB1.rotationPointY = 0F  - heightToRaise;
    	    armRightA1.rotationPointY = 10F - heightToRaise;
    	    armRightA2.rotationPointY = 8.5F - heightToRaise;
    	    armRightB1.rotationPointY = 0F - heightToRaise;
    	    lowerBody.rotationPointY = 20F - heightToRaise;
    	    leg1.rotationPointY = 21F - heightToRaise;
    	    leg2.rotationPointY = 21F - heightToRaise;
    	    leg3.rotationPointY = 21F - heightToRaise;
    	    leg4.rotationPointY =21F - heightToRaise;
    	    leg5.rotationPointY =22F - heightToRaise;
    	    leg6.rotationPointY =21F - heightToRaise;
    	    leg7.rotationPointY =22F - heightToRaise;
    	    leg8.rotationPointY =21F - heightToRaise;    	 
    	    
    		leg1.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
    		leg2.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
    		leg3.rotateAngleY = MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.4F * par3;
    		leg4.rotateAngleY = MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.4F * par3; //MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
    		leg5.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
    		leg7.rotateAngleY = MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.4F * par3;
    		leg8.rotateAngleY = MathHelper.cos(par2 * 0.6662F * 2f + 1 / 4 * (float) Math.PI) * 0.1F * par3;
    		leg6.rotateAngleY = MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.4F * par3;
    }
    else
    {
   	 float heightToRaise = -4.15f;
	    Shape1.rotationPointY = -8F - heightToRaise;
	    Shape2.rotationPointY = -8F - heightToRaise;
	    armLeftA1.rotationPointY = 10F - heightToRaise;
	    armLeftA2.rotationPointY = 8.5F - heightToRaise;
	    armLeftB1.rotationPointY = 0F  - heightToRaise;
	    armRightA1.rotationPointY = 10F - heightToRaise;
	    armRightA2.rotationPointY = 8.5F - heightToRaise;
	    armRightB1.rotationPointY = 0F - heightToRaise;
	    lowerBody.rotationPointY = 20F - heightToRaise;
	    leg1.rotationPointY = 21F - heightToRaise;
	    leg2.rotationPointY = 21F - heightToRaise;
	    leg3.rotationPointY = 21F - heightToRaise;
	    leg4.rotationPointY =21F - heightToRaise;
	    leg5.rotationPointY =22F - heightToRaise;
	    leg6.rotationPointY =21F - heightToRaise;
	    leg7.rotationPointY =22F - heightToRaise;
	    leg8.rotationPointY =21F - heightToRaise;    
    }
    

	
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
    Random rand1 = new Random();
    
    int randActLeft = rand1.nextInt(3);
    int randActRight = rand1.nextInt(3);
    
    if (randActLeft == 1) {
    armLeftA1.rotateAngleX = (float) (-85 * Math.PI / 180 + 50 * Math.PI / 180
	* MathHelper.cos(f * 0.6662F + (float) Math.PI));
    armLeftA1.rotateAngleZ = 0F;
    
    
    
    armLeftA2.rotateAngleZ = 0F;
    armLeftA2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI);
    }
    
    if (randActRight == 0) {
	armRightA1.rotateAngleX = (float) (-85 * Math.PI / 180 + 50 * Math.PI / 180
	* MathHelper.cos(f * 0.6662F));
	armRightA2.rotateAngleX = MathHelper.cos(f * 0.6662F);
    } 
  }
  
}

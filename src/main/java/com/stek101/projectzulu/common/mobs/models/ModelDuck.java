package com.stek101.projectzulu.common.mobs.models;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelDuck extends ModelBase
{
  //fields
    ModelRenderer TAIL;
    ModelRenderer HEAD;
    ModelRenderer BILL;
    ModelRenderer BODY;
    ModelRenderer RLEG;
    ModelRenderer LLEG;
    ModelRenderer RWING;
    ModelRenderer LWING;
  
  public ModelDuck()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      HEAD = new ModelRenderer(this, 0, 0);
      HEAD.addBox(-2F, -6F, -2F, 4, 6, 3);
      HEAD.setRotationPoint(0F, 16F, -3F);
      HEAD.setTextureSize(64, 32);
      HEAD.mirror = true;
      setRotation(HEAD, 0F, 0F, 0F);
      BILL = new ModelRenderer(this, 40, 3);
      BILL.addBox(-2F, -4F, -4F, 4, 2, 3);
      //BILL.setRotationPoint(0F, 15F, -5F);
      BILL.setRotationPoint(0F, 0F, -1F);
      BILL.setTextureSize(64, 32);
      BILL.mirror = true;
      setRotation(BILL, 0F, 0F, 0F);
      HEAD.addChild(BILL);
      BODY = new ModelRenderer(this, 0, 10);
      BODY.addBox(-3F, -4F, -3F, 6, 7, 8);
      BODY.setRotationPoint(0F, 17F, 1F);
      BODY.setTextureSize(64, 32);
      BODY.mirror = true;
      setRotation(BODY, 0F, 0F, 0F);
      RLEG = new ModelRenderer(this, 23, 0);
      RLEG.addBox(-1F, 0F, -3F, 3, 5, 3);
      RLEG.setRotationPoint(-2F, 18F, 3F);
      RLEG.setTextureSize(64, 32);
      RLEG.mirror = true;
      setRotation(RLEG, 0F, 0F, 0F);
      LLEG = new ModelRenderer(this, 23, 0);
      LLEG.addBox(-1F, 0F, -3F, 3, 5, 3);
      LLEG.setRotationPoint(1F, 18F, 3F);
      LLEG.setTextureSize(64, 32);
      LLEG.mirror = true;
      setRotation(LLEG, 0F, 0F, 0F);
      RWING = new ModelRenderer(this, 31, 13);
      RWING.addBox(0F, 0F, -3F, 1, 4, 7);
      RWING.setRotationPoint(-4F, 14F, 1F);
      RWING.setTextureSize(64, 32);
      RWING.mirror = true;
      setRotation(RWING, 0F, 0F, 0F);
      LWING = new ModelRenderer(this, 31, 13);
      LWING.addBox(-1F, 0F, -3F, 1, 4, 7);
      LWING.setRotationPoint(4F, 14F, 1F);
      LWING.setTextureSize(64, 32);
      LWING.mirror = true;
      setRotation(LWING, 0F, 0F, 0F);
      TAIL = new ModelRenderer(this, 30, 20);
      TAIL.addBox(-1.5F, -2F, 0F, 3, 1, 2);
      TAIL.setRotationPoint(0F, 16F, 5F);
      TAIL.setTextureSize(64, 32);
      TAIL.mirror = true;
      setRotation(TAIL, 0F, 0F, 0F);
  }
  
  public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
  {
	  this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);

      if (this.isChild)
      {
          float f6 = 2.0F;
          GL11.glPushMatrix();
          GL11.glTranslatef(0.0F, 5.0F * p_78088_7_, 2.0F * p_78088_7_);
          this.HEAD.render(p_78088_7_);
          //this.BILL.render(p_78088_7_);          
          GL11.glPopMatrix();
          GL11.glPushMatrix();
          GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
          GL11.glTranslatef(0.0F, 24.0F * p_78088_7_, 0.0F);
          this.BODY.render(p_78088_7_);
          this.RLEG.render(p_78088_7_);
          this.LLEG.render(p_78088_7_);
          this.RWING.render(p_78088_7_);
          this.LWING.render(p_78088_7_);
          this.TAIL.render(p_78088_7_);
          GL11.glPopMatrix();
      }
      else
      {
          this.HEAD.render(p_78088_7_);
          //this.BILL.render(p_78088_7_);
          this.BODY.render(p_78088_7_);
          this.RLEG.render(p_78088_7_);
          this.LLEG.render(p_78088_7_);
          this.RWING.render(p_78088_7_);
          this.LWING.render(p_78088_7_);
          this.TAIL.render(p_78088_7_);
      }
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
  {
      this.HEAD.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
      this.HEAD.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
      //this.BILL.rotateAngleX = this.HEAD.rotateAngleX;
      //this.BILL.rotateAngleY = this.HEAD.rotateAngleY;     
      //this.BODY.rotateAngleX = ((float)Math.PI / 2F);
      this.RLEG.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
      this.LLEG.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
      //this.RWING.rotateAngleZ = p_78087_3_;
      //this.LWING.rotateAngleZ = -p_78087_3_;
  }

}
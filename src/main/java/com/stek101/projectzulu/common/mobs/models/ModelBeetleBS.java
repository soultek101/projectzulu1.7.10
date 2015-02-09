package com.stek101.projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class ModelBeetleBS extends ModelBase
{
	  //fields
		float heightToLower = 21f;
		float renderScale = 0.50f;
	    ModelRenderer antennaeLeft;
	    ModelRenderer antennaeRight;
	    ModelRenderer HEAD;
	    ModelRenderer thorax;
	    ModelRenderer BODY;
	    ModelRenderer BODY2;
	    ModelRenderer leg1;
	    ModelRenderer leg2;
	    ModelRenderer leg3;
	    ModelRenderer leg4;
	    ModelRenderer leg5;
	    ModelRenderer leg6;
	  
	  public ModelBeetleBS()
	  {
	    textureWidth = 64;
	    textureHeight = 32;
	    
	      antennaeLeft = new ModelRenderer(this, 10, 10);
	      antennaeLeft.addBox(0F, -7F, -1.5F, 0, 8, 3);
	      antennaeLeft.setRotationPoint(1.5F, 19F + heightToLower, -13F);
	      antennaeLeft.setTextureSize(64, 32);
	      antennaeLeft.mirror = true;
	      setRotation(antennaeLeft, 0.6981317F, -0.1745329F, 0.7853982F);
	      antennaeRight = new ModelRenderer(this, 2, 10);
	      antennaeRight.addBox(0F, -7F, -1.5F, 0, 8, 3);
	      antennaeRight.setRotationPoint(-3F, 19F + heightToLower, -13F);
	      antennaeRight.setTextureSize(64, 32);
	      antennaeRight.mirror = true;
	      setRotation(antennaeRight, 0.6632251F, 0.1745329F, -0.7853982F);
	      HEAD = new ModelRenderer(this, 1, 1);
	      HEAD.addBox(-2F, -2F, -3F, 3, 3, 3);
	      HEAD.setRotationPoint(-0.5F, 21.5F + heightToLower, -10F);
	      HEAD.setTextureSize(64, 32);
	      HEAD.mirror = true;
	      setRotation(HEAD, 0F, 0F, 0F);
	      thorax = new ModelRenderer(this, 20, 17);
	      thorax.addBox(-3F, -3F, -6F, 6, 4, 3);
	      thorax.setRotationPoint(-1F, 22F + heightToLower, -4F);
	      thorax.setTextureSize(64, 32);
	      thorax.mirror = true;
	      setRotation(thorax, 0F, 0F, 0F);
	      BODY = new ModelRenderer(this, 26, 1);
	      BODY.addBox(-5F, -3F, -6F, 7, 5, 9);
	      BODY.setRotationPoint(0.5F, 21F + heightToLower, -1F);
	      BODY.setTextureSize(64, 32);
	      BODY.mirror = true;
	      setRotation(BODY, 0F, 0F, 0F);
	      BODY2 = new ModelRenderer(this, 42, 17);
	      BODY2.addBox(-4F, -2F, -1F, 6, 4, 4);
	      BODY2.setRotationPoint(0F, 20.5F + heightToLower, 3F);
	      BODY2.setTextureSize(64, 32);
	      BODY2.mirror = true;
	      setRotation(BODY2, 0F, 0F, 0F);
	      leg1 = new ModelRenderer(this, 2, 28);
	      leg1.addBox(-5F, -1F, -1F, 5, 1, 1);
	      leg1.setRotationPoint(-4F, 23F + heightToLower, 0F);
	      leg1.setTextureSize(64, 32);
	      leg1.mirror = true;
	      setRotation(leg1, 0F, 0F, 0F);
	      leg2 = new ModelRenderer(this, 2, 28);
	      leg2.addBox(-1F, -1F, -1F, 5, 1, 1);
	      leg2.setRotationPoint(3F, 23F + heightToLower, 0F);
	      leg2.setTextureSize(64, 32);
	      leg2.mirror = true;
	      setRotation(leg2, 0F, 0F, 0F);
	      leg3 = new ModelRenderer(this, 2, 25);
	      leg3.addBox(-5F, -1F, -1F, 5, 1, 1);
	      leg3.setRotationPoint(-4F, 23F + heightToLower, -4F);
	      leg3.setTextureSize(64, 32);
	      leg3.mirror = true;
	      setRotation(leg3, 0F, 0F, 0F);
	      leg4 = new ModelRenderer(this, 2, 25);
	      leg4.addBox(-1F, -1F, -1F, 5, 1, 1);
	      leg4.setRotationPoint(3F, 23F + heightToLower, -4F);
	      leg4.setTextureSize(64, 32);
	      leg4.mirror = true;
	      setRotation(leg4, 0F, 0F, 0F);
	      leg5 = new ModelRenderer(this, 2, 22);
	      leg5.addBox(-5F, -1F, -1F, 5, 1, 1);
	      leg5.setRotationPoint(-4F, 23F + heightToLower, -7F);
	      leg5.setTextureSize(64, 32);
	      leg5.mirror = true;
	      setRotation(leg5, 0F, 0F, 0F);
	      leg6 = new ModelRenderer(this, 2, 22);
	      leg6.addBox(0F, -1F, -1F, 5, 1, 1);
	      leg6.setRotationPoint(2F, 23F + heightToLower, -7F);
	      leg6.setTextureSize(64, 32);
	      leg6.mirror = true;
	      setRotation(leg6, 0F, 0F, 0F);
	  }
	  
	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
		    super.render(entity, f, f1, f2, f3, f4, f5);
		    setRotationAngles(f, f1, f2, f3, f4, f5, entity);  
		    float var8 = 2.0F;
		    GL11.glPushMatrix();
		    GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
		    GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
		    antennaeLeft.render(f5 * renderScale);
		    antennaeRight.render(f5 * renderScale);
		    HEAD.render(f5 * renderScale);
		    thorax.render(f5 * renderScale);
		    BODY.render(f5 * renderScale);
		    BODY2.render(f5 * renderScale);
		    leg1.render(f5 * renderScale);
		    leg2.render(f5 * renderScale);
		    leg3.render(f5 * renderScale);
		    leg4.render(f5 * renderScale);
		    leg5.render(f5 * renderScale);
		    leg6.render(f5 * renderScale);
		    GL11.glPopMatrix();
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
	      this.HEAD.rotateAngleY = f3 / (180F / (float)Math.PI);
	      this.HEAD.rotateAngleX = f4 / (180F / (float)Math.PI);
	      float f6 = ((float)Math.PI / 4F);
	      this.leg1.rotateAngleZ = -f6;
	      this.leg2.rotateAngleZ = f6;
	      this.leg3.rotateAngleZ = -f6 * 0.74F;
	      this.leg4.rotateAngleZ = f6 * 0.74F;
	      this.leg5.rotateAngleZ = -f6 * 0.74F;
	      this.leg6.rotateAngleZ = f6 * 0.74F;
	      //this.spiderLeg7.rotateAngleZ = -f6;
	      //this.spiderLeg8.rotateAngleZ = f6;
	      float f7 = -0.0F;
	      float f8 = 0.3926991F;
	      this.leg1.rotateAngleY = f8 * 2.0F + f7;
	      this.leg2.rotateAngleY = -f8 * 2.0F - f7;
	      this.leg3.rotateAngleY = f8 * 1.0F + f7;
	      this.leg4.rotateAngleY = -f8 * 1.0F - f7;
	      this.leg5.rotateAngleY = -f8 * 1.0F + f7;
	      this.leg6.rotateAngleY = f8 * 1.0F - f7;
	      //this.spiderLeg7.rotateAngleY = -f8 * 2.0F + f7;
	      //this.spiderLeg8.rotateAngleY = f8 * 2.0F - f7;
	      float f9 = -(MathHelper.cos(f * 0.6662F * 2.0F + 0.0F) * 0.4F) * f1;
	      float f10 = -(MathHelper.cos(f * 0.6662F * 2.0F + (float)Math.PI) * 0.4F) * f1;
	      float f11 = -(MathHelper.cos(f * 0.6662F * 2.0F + ((float)Math.PI / 2F)) * 0.4F) * f1;
	      float f12 = -(MathHelper.cos(f * 0.6662F * 2.0F + ((float)Math.PI * 3F / 2F)) * 0.4F) * f1;
	      float f13 = Math.abs(MathHelper.sin(f * 0.6662F + 0.0F) * 0.4F) * f1;
	      float f14 = Math.abs(MathHelper.sin(f * 0.6662F + (float)Math.PI) * 0.4F) * f1;
	      float f15 = Math.abs(MathHelper.sin(f * 0.6662F + ((float)Math.PI / 2F)) * 0.4F) * f1;
	      float f16 = Math.abs(MathHelper.sin(f * 0.6662F + ((float)Math.PI * 3F / 2F)) * 0.4F) * f1;
	      this.leg1.rotateAngleY += f9;
	      this.leg2.rotateAngleY += -f9;
	      this.leg3.rotateAngleY += f10;
	      this.leg4.rotateAngleY += -f10;
	      this.leg5.rotateAngleY += f11;
	      this.leg6.rotateAngleY += -f11;
	      //this.spiderLeg7.rotateAngleY += f12;
	      //this.spiderLeg8.rotateAngleY += -f12;
	      this.leg1.rotateAngleZ += f13;
	      this.leg2.rotateAngleZ += -f13;
	      this.leg3.rotateAngleZ += f14;
	      this.leg4.rotateAngleZ += -f14;
	      this.leg5.rotateAngleZ += f15;
	      this.leg6.rotateAngleZ += -f15;
	      //this.spiderLeg7.rotateAngleZ += f16;
	      //this.spiderLeg8.rotateAngleZ += -f16;
	  }

	}

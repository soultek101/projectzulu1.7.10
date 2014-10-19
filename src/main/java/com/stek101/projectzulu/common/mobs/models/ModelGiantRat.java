package com.stek101.projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelGiantRat extends ModelBase
{
	  //fields
    ModelRenderer HEADROT;
    ModelRenderer nose;
    ModelRenderer earLeft;
    ModelRenderer earRight;
    ModelRenderer frontBODY;
    ModelRenderer backBODY;
    ModelRenderer frontLegLeft;
    ModelRenderer backLegLeft;
    ModelRenderer frontLegRight;
    ModelRenderer backLegRight;
    ModelRenderer tail1;
    ModelRenderer tail2;
    ModelRenderer tail3;
    ModelRenderer tail4;
    ModelRenderer whiskersRight;
    ModelRenderer whiskersLeft;
  
  public ModelGiantRat()
  {
    textureWidth = 128;
    textureHeight = 32;
    
      HEADROT = new ModelRenderer(this, 49, 1);
      HEADROT.addBox(-2F, -2F, -4F, 4, 4, 4);
      HEADROT.setRotationPoint(0.5F, 19F, -4F);
      HEADROT.setTextureSize(128, 32);
      HEADROT.mirror = true;
      setRotation(HEADROT, 0.0872665F, 0F, 0F);
      nose = new ModelRenderer(this, 1, 1);
      nose.addBox(-1F, -1F, -4F, 2, 3, 5);
      nose.setRotationPoint(0.0F, 0F, -3F);
      nose.setTextureSize(128, 32);
      nose.mirror = true;
      setRotation(nose, 0.0872665F, 0F, 0F);
      HEADROT.addChild(nose);
      earLeft = new ModelRenderer(this, 17, 1);
      earLeft.addBox(0F, -4F, 0F, 3, 3, 1);
      earLeft.setRotationPoint(0.75F, 0F, -1.5F);
      earLeft.setTextureSize(128, 32);
      earLeft.mirror = true;
      setRotation(earLeft, 0F, 0F, 0F);
      HEADROT.addChild(earLeft);
      earRight = new ModelRenderer(this, 27, 1);
      earRight.addBox(-2F, -4F, 0F, 3, 3, 1);
      earRight.setRotationPoint(-1.25F, 0F, -1.5F);
      earRight.setTextureSize(128, 32);
      earRight.mirror = true;
      setRotation(earRight, 0F, 0F, 0F);
      HEADROT.addChild(earRight);      
      whiskersRight = new ModelRenderer(this, 16, 6);
      whiskersRight.addBox(-5F, -2F, 0F, 6, 6, 0);
      //whiskersRight.setRotationPoint(-1F, 19F, -10.5F);
      whiskersRight.setRotationPoint(-1.5F, 0F, -3.5F);
      whiskersRight.setTextureSize(128, 32);
      whiskersRight.mirror = true;
      setRotation(whiskersRight, 0.0872665F, 0.4363323F, 0F);
      nose.addChild(whiskersRight);      
      whiskersLeft = new ModelRenderer(this, 30, 6);
      whiskersLeft.addBox(0F, -3F, 0F, 6, 6, 0);
      whiskersLeft.setRotationPoint(1F, 1.0F, -3.5F);
      whiskersLeft.setTextureSize(128, 32);
      whiskersLeft.mirror = true;
      setRotation(whiskersLeft, 0.0698132F, -0.4363323F, 0F);
      nose.addChild(whiskersLeft);
      frontBODY = new ModelRenderer(this, 1, 13);
      frontBODY.addBox(-2.5F, -2F, 0F, 6, 5, 8);
      frontBODY.setRotationPoint(0F, 18.5F, -4F);
      frontBODY.setTextureSize(128, 32);
      frontBODY.mirror = true;
      setRotation(frontBODY, 0.1047198F, 0F, 0F);
      backBODY = new ModelRenderer(this, 30, 13);
      backBODY.addBox(-3F, -2F, 0F, 7, 6, 7);
      backBODY.setRotationPoint(0F, 17F, 2F);
      backBODY.setTextureSize(128, 32);
      backBODY.mirror = true;
      setRotation(backBODY, 0.0872665F, 0F, 0F);
      frontLegLeft = new ModelRenderer(this, 74, 13);
      frontLegLeft.addBox(0F, -1F, -1F, 2, 4, 2);
      frontLegLeft.setRotationPoint(2.5F, 20F, -2F);
      frontLegLeft.setTextureSize(128, 32);
      frontLegLeft.mirror = true;
      setRotation(frontLegLeft, 0F, 0F, 0F);
      backLegLeft = new ModelRenderer(this, 84, 13);
      backLegLeft.addBox(0F, -1F, -1F, 2, 5, 3);
      backLegLeft.setRotationPoint(3F, 19F, 6F);
      backLegLeft.setTextureSize(128, 32);
      backLegLeft.mirror = true;
      setRotation(backLegLeft, 0F, 0F, 0F);
      frontLegRight = new ModelRenderer(this, 61, 13);
      frontLegRight.addBox(-2F, -1F, -1F, 2, 4, 2);
      frontLegRight.setRotationPoint(-1.5F, 20F, -2F);
      frontLegRight.setTextureSize(128, 32);
      frontLegRight.mirror = true;
      setRotation(frontLegRight, 0F, 0F, 0F);
      backLegRight = new ModelRenderer(this, 98, 13);
      backLegRight.addBox(-3F, -1F, -1F, 2, 5, 3);
      backLegRight.setRotationPoint(-1F, 19F, 6F);
      backLegRight.setTextureSize(128, 32);
      backLegRight.mirror = true;
      setRotation(backLegRight, 0F, 0F, 0F);
      tail1 = new ModelRenderer(this, 66, 1);
      tail1.addBox(-0.5F, -1F, 1F, 2, 2, 4);
      tail1.setRotationPoint(0F, 16F, 6F);
      tail1.setTextureSize(128, 32);
      tail1.mirror = true;
      setRotation(tail1, -0.4537856F, 0F, 0F);
      tail2 = new ModelRenderer(this, 79, 1);
      tail2.addBox(-0.5F, 0F, 0F, 1, 1, 4);
      tail2.setRotationPoint(0.5F, 17.5F, 10.5F);
      tail2.setTextureSize(128, 32);
      tail2.mirror = true;
      setRotation(tail2, -0.8028515F, 0F, 0F);
      tail3 = new ModelRenderer(this, 101, 1);
      tail3.addBox(-0.5F, 0F, 0F, 1, 1, 4);
      tail3.setRotationPoint(0.5F, 21.5F, 15.5F);
      tail3.setTextureSize(128, 32);
      tail3.mirror = true;
      setRotation(tail3, -0.0698132F, 0F, 0F);
      tail4 = new ModelRenderer(this, 90, 1);
      tail4.addBox(0F, 0F, 0F, 1, 1, 4);
      tail4.setRotationPoint(0F, 19.8F, 12.5F);
      tail4.setTextureSize(128, 32);
      tail4.mirror = true;
      setRotation(tail4, -0.5235988F, 0F, 0F);      
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    HEADROT.render(f5);
    //nose.render(f5);
    //earLeft.render(f5);
    //earRight.render(f5);
    frontBODY.render(f5);
    backBODY.render(f5);
    frontLegLeft.render(f5);
    backLegLeft.render(f5);
    frontLegRight.render(f5);
    backLegRight.render(f5);
    tail1.render(f5);
    tail2.render(f5);
    tail3.render(f5);
    tail4.render(f5);
    //whiskersRight.render(f5);
    //whiskersLeft.render(f5);
  }
  
	  
	  @Override
	  public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
		/* Constant Animation Rotations */
		frontLegLeft.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f) * 1.2F * par3;
		backLegLeft.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f) * 1.2F * par3;
		frontLegRight.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f + (float) Math.PI) * 1.2F * par3;
		backLegRight.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f + (float) Math.PI) * 1.2F * par3;
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

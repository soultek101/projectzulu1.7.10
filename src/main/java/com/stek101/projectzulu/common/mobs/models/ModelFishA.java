package com.stek101.projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelFishA extends ModelBase
{
	//fields
    ModelRenderer HEAD;
    ModelRenderer BODY;
    ModelRenderer finSideLeft;
    ModelRenderer finSideRight;
    ModelRenderer topFin;
    ModelRenderer bottomFin;
    ModelRenderer backFinBase;
    ModelRenderer backFinBottom;
    ModelRenderer backFinTop;
  
  public ModelFishA()
  {
    textureWidth = 32;
    textureHeight = 32;
    
      HEAD = new ModelRenderer(this, 1, 1);
      HEAD.addBox(0F, -1.5F, -3F, 1, 3, 3);
      HEAD.setRotationPoint(-0.5F, 20F, 0F);
      HEAD.setTextureSize(32, 32);
      HEAD.mirror = true;
      setRotation(HEAD, 0F, 0F, 0F);
      BODY = new ModelRenderer(this, 1, 8);
      BODY.addBox(-1F, -2F, 0F, 2, 4, 8);
      BODY.setRotationPoint(0F, 20F, 0F);
      BODY.setTextureSize(32, 32);
      BODY.mirror = true;
      setRotation(BODY, 0F, 0F, 0F);
      finSideLeft = new ModelRenderer(this, 22, 16);
      finSideLeft.addBox(1F, -1F, 0F, 0, 2, 4);
      finSideLeft.setRotationPoint(-0.5F, 20F, 2F);
      finSideLeft.setTextureSize(32, 32);
      finSideLeft.mirror = true;
      setRotation(finSideLeft, 0F, 0.6632251F, 0F);
      finSideRight = new ModelRenderer(this, 22, 23);
      finSideRight.addBox(-1F, -1F, 0F, 0, 2, 4);
      finSideRight.setRotationPoint(0.5F, 20F, 2F);
      finSideRight.setTextureSize(32, 32);
      finSideRight.mirror = true;
      setRotation(finSideRight, 0F, -0.6632251F, 0F);
      topFin = new ModelRenderer(this, 10, 1);
      topFin.addBox(0F, -1F, 0F, 0, 2, 4);
      topFin.setRotationPoint(0F, 19F, 2F);
      topFin.setTextureSize(32, 32);
      topFin.mirror = true;
      setRotation(topFin, 0.7435722F, 0F, 0F);
      bottomFin = new ModelRenderer(this, 19, 1);
      bottomFin.addBox(0F, 0F, 0F, 0, 2, 4);
      bottomFin.setRotationPoint(0F, 20F, 2F);
      bottomFin.setTextureSize(32, 32);
      bottomFin.mirror = true;
      setRotation(bottomFin, -0.5576792F, 0F, 0F);
      backFinBase = new ModelRenderer(this, 1, 22);
      backFinBase.addBox(-0.5F, -2F, 0F, 1, 3, 6);
      backFinBase.setRotationPoint(0F, 20.5F, 7F);
      backFinBase.setTextureSize(32, 32);
      backFinBase.mirror = true;
      setRotation(backFinBase, 0F, 0F, 0F);
      
      backFinBottom = new ModelRenderer(this, 22, 9);
      backFinBottom.addBox(0F, 0F, -2F, 0, 4, 2);
      backFinBottom.setRotationPoint(0F, -0.5F, 6F);
      backFinBottom.setTextureSize(32, 32);
      backFinBottom.mirror = true;
      setRotation(backFinBottom, 0.8179294F, 0F, 0F);
      backFinBase.addChild(backFinBottom);
      
      backFinTop = new ModelRenderer(this, 27, 9);
      backFinTop.addBox(0F, 0F, 0F, 0, 4, 2);
      backFinTop.setRotationPoint(0F, 0.0F, 6F);
      backFinTop.setTextureSize(32, 32);
      backFinTop.mirror = true;
      setRotation(backFinTop, 2.330376F, 0F, 0F);
      backFinBase.addChild(backFinTop);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    HEAD.render(f5);
    BODY.render(f5);
    finSideLeft.render(f5);
    finSideRight.render(f5);
    topFin.render(f5);
    bottomFin.render(f5);
    backFinBase.render(f5);
    //backFinBottom.render(f5);
    //backFinTop.render(f5);
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
	    this.backFinBase.rotateAngleY = MathHelper.cos(f3 * 0.6662F) * 0.8F * f;
	  }

	}
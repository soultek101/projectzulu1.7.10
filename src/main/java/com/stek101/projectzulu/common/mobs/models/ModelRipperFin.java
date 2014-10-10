package com.stek101.projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class ModelRipperFin extends ModelBase
{
	//fields
    ModelRenderer headTop;
    ModelRenderer headBottom;
    ModelRenderer BODY;
    ModelRenderer finSideLeft;
    ModelRenderer finSideRight;
    ModelRenderer topFin;
    ModelRenderer bottomFin;
    ModelRenderer backFinBase;
    ModelRenderer backFinTop1;
    ModelRenderer backFinTop2;
    ModelRenderer backFinBottom1;
    ModelRenderer backFinBottom2;
    ModelRenderer finSideLeft2;
    ModelRenderer finSideRight2;
    ModelRenderer jawTeeth0;
    ModelRenderer jawTeeth2;
    ModelRenderer jawTeeth3;
    ModelRenderer jawTeeth4;
    ModelRenderer jawTeeth5;
    
    float heightToRaise = 4f;
    float renderScale = 1.2f;
  
  public ModelRipperFin()
  {
	  textureWidth = 64;
	    textureHeight = 32;
	    
	      headTop = new ModelRenderer(this, 1, 1);
	      headTop.addBox(0F, -1.5F, -5F, 1, 2, 5);
	      headTop.setRotationPoint(-0.5F, 19.5F  - heightToRaise, 1F);
	      headTop.setTextureSize(64, 32);
	      headTop.mirror = true;
	      setRotation(headTop, 0F, 0F, 0F);
	      headBottom = new ModelRenderer(this, 1, 23);
	      headBottom.addBox(0F, -1.5F, -4F, 1, 1, 5);
	      headBottom.setRotationPoint(-0.5F, 22F  - heightToRaise, 1.5F);
	      headBottom.setTextureSize(64, 32);
	      headBottom.mirror = true;
	      setRotation(headBottom, 0F, 0F, 0F);
	      BODY = new ModelRenderer(this, 34, 13);
	      BODY.addBox(-1F, -2F, 0F, 2, 4, 9);
	      BODY.setRotationPoint(0F, 20F  - heightToRaise, 0F);
	      BODY.setTextureSize(64, 32);
	      BODY.mirror = true;
	      setRotation(BODY, 0F, 0F, 0F);
      finSideLeft = new ModelRenderer(this, 22, 16);
      finSideLeft.addBox(1F, -1F, 0F, 0, 2, 4);
      finSideLeft.setRotationPoint(-0.5F, 20F  - heightToRaise, 3F);
      finSideLeft.setTextureSize(64, 32);
      finSideLeft.mirror = true;
      setRotation(finSideLeft, 0F, 0.6632251F, 0F);
      finSideRight = new ModelRenderer(this, 22, 23);
      finSideRight.addBox(-1F, -1F, 0F, 0, 2, 4);
      finSideRight.setRotationPoint(0.5F, 20F  - heightToRaise, 3F);
      finSideRight.setTextureSize(64, 32);
      finSideRight.mirror = true;
      setRotation(finSideRight, 0F, -0.6632251F, 0F);
      topFin = new ModelRenderer(this, 14, 1);
      topFin.addBox(0F, -1F, 0F, 0, 2, 4);
      topFin.setRotationPoint(0F, 19F  - heightToRaise, 4F);
      topFin.setTextureSize(64, 32);
      topFin.mirror = true;
      setRotation(topFin, 0.7435722F, 0F, 0F);
      bottomFin = new ModelRenderer(this, 23, 1);
      bottomFin.addBox(0F, 0F, 0F, 0, 2, 4);
      bottomFin.setRotationPoint(0F, 20F  - heightToRaise, 2F);
      bottomFin.setTextureSize(64, 32);
      bottomFin.mirror = true;
      setRotation(bottomFin, -0.5576792F, 0F, 0F);
      backFinBase = new ModelRenderer(this, 34, 1);
      backFinBase.addBox(-0.5F, -1.5F, 0F, 1, 3, 8);
      backFinBase.setRotationPoint(0F, 20F  - heightToRaise, 9F);
      backFinBase.setTextureSize(64, 32);
      backFinBase.mirror = true;
      setRotation(backFinBase, 0F, 0F, 0F);
      
      backFinTop1 = new ModelRenderer(this, 7, 13);
      backFinTop1.addBox(0F, 0F, 0F, 0, 4, 2);
      backFinTop1.setRotationPoint(0F, 1F, 2F);
      backFinTop1.setTextureSize(64, 32);
      backFinTop1.mirror = true;      
      setRotation(backFinTop1, 2.330376F, 0F, 0F);
      backFinBase.addChild(backFinTop1);
      
      backFinTop2 = new ModelRenderer(this, 27, 9);
      backFinTop2.addBox(0F, 0F, 0F, 0, 4, 2);
      backFinTop2.setRotationPoint(0F, 0F, 8F);
      backFinTop2.setTextureSize(64, 32);
      backFinTop2.mirror = true;
      setRotation(backFinTop2, 2.330376F, 0F, 0F);
      backFinBase.addChild(backFinTop2);
      
      backFinBottom1 = new ModelRenderer(this, 1, 13);
      backFinBottom1.addBox(0F, 0F, -2F, 0, 4, 2);
      backFinBottom1.setRotationPoint(0F, 0F, 2F);
      backFinBottom1.setTextureSize(64, 32);
      backFinBottom1.mirror = true;
      setRotation(backFinBottom1, 0.8179294F, 0F, 0F);
      backFinBase.addChild(backFinBottom1);
      
      backFinBottom2 = new ModelRenderer(this, 22, 9);
      backFinBottom2.addBox(0F, 0F, 0F, 0, 4, 2);
      backFinBottom2.setRotationPoint(0.0F, 1.0F, 7F);
      backFinBottom2.setTextureSize(64, 32);
      backFinBottom2.mirror = true;
      setRotation(backFinBottom2, 0.8179294F, 0F, 0F);
      backFinBase.addChild(backFinBottom2);
      
      finSideLeft2 = new ModelRenderer(this, 14, 16);
      finSideLeft2.addBox(0F, -1F, 0F, 0, 2, 3);
      finSideLeft2.setRotationPoint(0F, 20F  - heightToRaise, 12F);
      finSideLeft2.setTextureSize(64, 32);
      finSideLeft2.mirror = true;
      setRotation(finSideLeft2, 0F, 0.6632251F, 0F);
      finSideRight2 = new ModelRenderer(this, 14, 23);
      finSideRight2.addBox(0F, -1F, 0F, 0, 2, 3);
      finSideRight2.setRotationPoint(0F, 20F  - heightToRaise, 12F);
      finSideRight2.setTextureSize(64, 32);
      finSideRight2.mirror = true;
      setRotation(finSideRight2, 0F, -0.6632251F, 0F);
      jawTeeth0 = new ModelRenderer(this, 7, 20);
      jawTeeth0.addBox(0F, -1F, -1F, 0, 1, 1);
      jawTeeth0.setRotationPoint(0F, 19F  - heightToRaise, -3.5F);
      jawTeeth0.setTextureSize(64, 32);
      jawTeeth0.mirror = true;
      setRotation(jawTeeth0, 2.330376F, 0F, 0F);
      jawTeeth2 = new ModelRenderer(this, 7, 20);
      jawTeeth2.addBox(0F, 0F, -2F, 0, 1, 1);
      jawTeeth2.setRotationPoint(0F, 19F  - heightToRaise, -4F);
      jawTeeth2.setTextureSize(64, 32);
      jawTeeth2.mirror = true;
      setRotation(jawTeeth2, 2.330376F, 0F, 0F);
      jawTeeth3 = new ModelRenderer(this, 7, 10);
      jawTeeth3.addBox(0F, 1F, -1F, 0, 1, 1);
      jawTeeth3.setRotationPoint(0F, 21.5F  - heightToRaise, -3.5F);
      jawTeeth3.setTextureSize(64, 32);
      jawTeeth3.mirror = true;
      setRotation(jawTeeth3, 2.330376F, 0F, 0F);
      jawTeeth4 = new ModelRenderer(this, 7, 20);
      jawTeeth4.addBox(0F, 0F, -2F, 0, 1, 1);
      jawTeeth4.setRotationPoint(0F, 19F  - heightToRaise, -2.5F);
      jawTeeth4.setTextureSize(64, 32);
      jawTeeth4.mirror = true;
      setRotation(jawTeeth4, 2.330376F, 0F, 0F);
      jawTeeth5 = new ModelRenderer(this, 7, 10);
      jawTeeth5.addBox(0F, 0F, 0F, 0, 1, 1);
      jawTeeth5.setRotationPoint(0F, 21.5F  - heightToRaise, -0.5F);
      jawTeeth5.setTextureSize(64, 32);
      jawTeeth5.mirror = true;
      setRotation(jawTeeth5, 2.330376F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    
    if (this.isChild) {
   	 float var8 = 2.0F;
        GL11.glPushMatrix();
        GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
        GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
        headTop.render(renderScale * f5);
        headBottom.render(renderScale *  f5);
        BODY.render(renderScale *  f5);
        finSideLeft.render(renderScale *  f5);
        finSideRight.render(renderScale *  f5);
        topFin.render(renderScale *  f5);
        bottomFin.render(renderScale *  f5);
        backFinBase.render(renderScale *  f5);
        //backFinTop1.render(f5);
        //backFinTop2.render(f5);
        //backFinBottom1.render(f5);
        //backFinBottom2.render(f5);
        //finSideLeft2.render(f5);
        //finSideRight2.render(f5);
        jawTeeth0.render(renderScale * f5);    
        jawTeeth2.render(renderScale * f5);
        jawTeeth3.render(renderScale * f5);
        jawTeeth4.render(renderScale * f5);
        jawTeeth5.render(renderScale * f5);	    
        GL11.glPopMatrix();
   } else {
	    headTop.render(renderScale * f5);
	    headBottom.render(renderScale *  f5);
	    BODY.render(renderScale *  f5);
	    finSideLeft.render(renderScale *  f5);
	    finSideRight.render(renderScale *  f5);
	    topFin.render(renderScale *  f5);
	    bottomFin.render(renderScale *  f5);
	    backFinBase.render(renderScale *  f5);
	    //backFinTop1.render(f5);
	    //backFinTop2.render(f5);
	    //backFinBottom1.render(f5);
	    //backFinBottom2.render(f5);
	    //finSideLeft2.render(f5);
	    //finSideRight2.render(f5);
	    jawTeeth0.render(renderScale * f5);    
	    jawTeeth2.render(renderScale * f5);
	    jawTeeth3.render(renderScale * f5);
	    jawTeeth4.render(renderScale * f5);
	    jawTeeth5.render(renderScale * f5);
   }    
  }
  
//  @Override
//  public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
//	this.backFinBase.rotateAngleY = MathHelper.cos(par2 * 0.3332F) * 0.6F * par3;
//	super.setLivingAnimations(par1EntityLiving, par2, par3, par4);
//  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    this.backFinBase.rotateAngleY = MathHelper.cos(f * 0.4443F) * 0.8F * f1; 
  }

}

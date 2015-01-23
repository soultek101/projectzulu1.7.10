package com.stek101.projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import com.stek101.projectzulu.common.core.ModelHelper;

public class ModelCamel extends ModelBase
{
    float heightToRaise = 33f;
    float renderScale = 1.0f;
	 //fields
  //fields
    ModelRenderer earLeft;
    ModelRenderer earRight;
    ModelRenderer HEADbump;
    ModelRenderer HEAD;
    ModelRenderer neckTop;
    ModelRenderer neckBase;
    ModelRenderer humpMain;
    ModelRenderer humpMain2;
    ModelRenderer saddleBase;
    ModelRenderer saddleHorn1;
    ModelRenderer saddleHorn2;
    ModelRenderer Body;
    ModelRenderer hip1;
    ModelRenderer Leg1toProt;
    ModelRenderer Leg1BotRot;
    ModelRenderer hip2;
    ModelRenderer Leg2toProt;
    ModelRenderer Leg2BotRot;
    ModelRenderer hip3;
    ModelRenderer Leg3toProt;
    ModelRenderer Leg3BotRot;
    ModelRenderer hip4;
    ModelRenderer Leg4toProt;
    ModelRenderer Leg4BotRot;
    ModelRenderer tail;
  
  public ModelCamel()
  {
    textureWidth = 128;
    textureHeight = 64;    

    HEAD = new ModelRenderer(this, 1, 1);
    HEAD.addBox(-2F, -3F, -4F, 5, 4, 7);
    HEAD.setRotationPoint(0F, 3F - heightToRaise, -15F);
    HEAD.setTextureSize(128, 64);
    HEAD.mirror = true;
    setRotation(HEAD, 0.2268928F, 0F, 0F);
    HEADbump = new ModelRenderer(this, 105, 42);
    HEADbump.addBox(-2F, -2F, -3F, 3, 2, 6);
    //HEADbump.setRotationPoint(1F, 1.5F, -15.5F);
    HEADbump.setRotationPoint(1F, -1.5F, -0.5F);
    HEADbump.setTextureSize(128, 64);
    HEADbump.mirror = true;
    setRotation(HEADbump, 0.296706F, 0F, 0F);
    HEAD.addChild(HEADbump);
    earLeft = new ModelRenderer(this, 60, 6);
    earLeft.addBox(0F, -3F, -1F, 1, 4, 1);
    //earLeft.setRotationPoint(1.5F, 1F, -13F);
    earLeft.setRotationPoint(1.5F, -1.0F, 2.5F);
    earLeft.setTextureSize(128, 64);
    earLeft.mirror = true;
    setRotation(earLeft, 0F, 0F, 0.5235988F);
    HEAD.addChild(earLeft);    
    earRight = new ModelRenderer(this, 60, 1);
    earRight.addBox(-1F, -3F, -1F, 1, 4, 1);
    //earRight.setRotationPoint(-0.5F, 1F, -13F);
    earRight.setRotationPoint(-0.5F, -1.0F, 2.5F);
    earRight.setTextureSize(128, 64);
    earRight.mirror = true;
    setRotation(earRight, 0F, 0F, -0.5235988F);
    HEAD.addChild(earRight);
    
    neckTop = new ModelRenderer(this, 29, 1);
    neckTop.addBox(-1.5F, 0F, -1.5F, 3, 6, 3);
    neckTop.setRotationPoint(0.5F, 1.8F  - heightToRaise , -14F);
    neckTop.setTextureSize(128, 64);
    neckTop.mirror = true;
    setRotation(neckTop, 0.6457718F, 0F, 0F);
    neckBase = new ModelRenderer(this, 43, 1);
    neckBase.addBox(-1.5F, 0F, -1.5F, 3, 6, 4);
    neckBase.setRotationPoint(0.5F, 6F  - heightToRaise, -11.5F);
    neckBase.setTextureSize(128, 64);
    neckBase.mirror = true;
    setRotation(neckBase, 1.117011F, 0F, 0F);
    humpMain = new ModelRenderer(this, 1, 41);
    humpMain.addBox(-3.5F, -2F, -2F, 8, 4, 9);
    humpMain.setRotationPoint(0F, 3F - heightToRaise, -0.5F);
    humpMain.setTextureSize(128, 64);
    humpMain.mirror = true;
    setRotation(humpMain, 0F, 0F, 0F);
    humpMain2 = new ModelRenderer(this, 37, 42);
    humpMain2.addBox(-3F, -2F, -2F, 7, 4, 7);
    humpMain2.setRotationPoint(0F, 0.75F - heightToRaise, 0.5F);
    humpMain2.setTextureSize(128, 64);
    humpMain2.mirror = true;
    setRotation(humpMain2, 0F, 0F, 0F);
    saddleBase = new ModelRenderer(this, 68, 52);
    saddleBase.addBox(-3.5F, -2F, -2F, 6, 1, 5);
    saddleBase.setRotationPoint(1F, 4F - heightToRaise, -5F);
    saddleBase.setTextureSize(128, 64);
    saddleBase.mirror = true;
    setRotation(saddleBase, 0F, 0F, 0F);
    saddleHorn1 = new ModelRenderer(this, 67, 1);
    saddleHorn1.addBox(-1F, -3F, -1F, 1, 5, 1);
    saddleHorn1.setRotationPoint(1F, 1F - heightToRaise, -6F);
    saddleHorn1.setTextureSize(128, 64);
    saddleHorn1.mirror = true;
    setRotation(saddleHorn1, 0.3346075F, 0F, 0F);
    saddleHorn2 = new ModelRenderer(this, 73, 5);
    saddleHorn2.addBox(-2F, -1F, -1F, 4, 1, 1);
    saddleHorn2.setRotationPoint(0.5F, 0F - heightToRaise, -7F);
    saddleHorn2.setTextureSize(128, 64);
    saddleHorn2.mirror = true;
    setRotation(saddleHorn2, 0F, 0F, 0F);
    Body = new ModelRenderer(this, 1, 14);
    Body.addBox(-3.5F, -2F, -10F, 9, 9, 16);
    Body.setRotationPoint(-0.5F, 5F  - heightToRaise, 3F);
    Body.setTextureSize(128, 64);
    Body.mirror = true;
    setRotation(Body, 0F, 0F, 0F);
    hip1 = new ModelRenderer(this, 68, 42);
    hip1.addBox(-1F, -3F, -1F, 4, 4, 4);
    hip1.setRotationPoint(-4F, 12F  - heightToRaise, -5.5F);
    hip1.setTextureSize(128, 64);
    hip1.mirror = true;
    setRotation(hip1, 0.0698132F, 0F, 0F);
    Leg1toProt = new ModelRenderer(this, 65, 13);
    Leg1toProt.addBox(-1.5F, 0F, -1.5F, 3, 6, 3);
    //Leg1toProt.setRotationPoint(-3F, 12F, -4.5F);
    Leg1toProt.setRotationPoint(1F, 0F, 0.5F);
    Leg1toProt.setTextureSize(128, 64);
    Leg1toProt.mirror = true;
    setRotation(Leg1toProt, 0.0698132F, 0F, 0F);
    hip1.addChild(Leg1toProt);
    Leg1BotRot = new ModelRenderer(this, 65, 25);
    Leg1BotRot.addBox(-1.5F, 0F, -1.5F, 3, 7, 3);
    //Leg1BotRot.setRotationPoint(-3F, 17F, -4F);
    Leg1BotRot.setRotationPoint(0F, 6F, 0F);
    Leg1BotRot.setTextureSize(128, 64);
    Leg1BotRot.mirror = true;
    setRotation(Leg1BotRot, -0.0698132F, 0F, 0F);
    Leg1toProt.addChild(Leg1BotRot);
    hip2 = new ModelRenderer(this, 68, 42);
    hip2.addBox(-1F, -1F, -1F, 4, 4, 4);
    hip2.setRotationPoint(3F, 10F  - heightToRaise, -5.5F);
    hip2.setTextureSize(128, 64);
    hip2.mirror = true;
    setRotation(hip2, 0.0698132F, 0F, 0F);
    Leg2toProt = new ModelRenderer(this, 52, 13);
    Leg2toProt.addBox(-1.5F, 0F, -1.5F, 3, 6, 3);
    //Leg2toProt.setRotationPoint(4F, 11F, -4.5F);
    Leg2toProt.setRotationPoint(1F, 2F, 0.5F);
    Leg2toProt.setTextureSize(128, 64);
    Leg2toProt.mirror = true;
    setRotation(Leg2toProt, 0.0698132F, 0F, 0F);
    hip2.addChild(Leg2toProt);
    Leg2BotRot = new ModelRenderer(this, 52, 25);
    Leg2BotRot.addBox(-1.5F, 0F, -1.5F, 3, 7, 3);
    //Leg2BotRot.setRotationPoint(4F, 17F, -4F);
    Leg2BotRot.setRotationPoint(0F, 6F, 0F);
    Leg2BotRot.setTextureSize(128, 64);
    Leg2BotRot.mirror = true;
    setRotation(Leg2BotRot, -0.0698132F, 0F, 0F);
    Leg2toProt.addChild(Leg2BotRot);
    hip3 = new ModelRenderer(this, 87, 42);
    hip3.addBox(-1F, -1F, -1F, 4, 5, 4);
    hip3.setRotationPoint(-4F, 9F  - heightToRaise, 5F);
    hip3.setTextureSize(128, 64);
    hip3.mirror = true;
    setRotation(hip3, 0.0698132F, 0F, 0F);
    Leg3toProt = new ModelRenderer(this, 95, 13);
    Leg3toProt.addBox(-1.5F, 0F, -1.5F, 3, 6, 3);
    //Leg3toProt.setRotationPoint(-3F, 12F, 6F);
    Leg3toProt.setRotationPoint(1F, 2F, 1F);
    Leg3toProt.setTextureSize(128, 64);
    Leg3toProt.mirror = true;
    setRotation(Leg3toProt, 0.0872665F, 0F, 0F);
    hip3.addChild(Leg3toProt);
    Leg3BotRot = new ModelRenderer(this, 95, 25);
    Leg3BotRot.addBox(-1.5F, 0F, -1.5F, 3, 7, 3);
    //Leg3BotRot.setRotationPoint(-3F, 17F, 6.5F);
    Leg3BotRot.setRotationPoint(0F, 6F, 0.5F);
    Leg3BotRot.setTextureSize(128, 64);
    Leg3BotRot.mirror = true;
    setRotation(Leg3BotRot, -0.0698132F, 0F, 0F);
    Leg3toProt.addChild(Leg3BotRot);
    
    hip4 = new ModelRenderer(this, 87, 42);
    hip4.addBox(-1F, -1F, -1F, 4, 5, 4);
    hip4.setRotationPoint(3F, 9F  - heightToRaise, 5F);
    hip4.setTextureSize(128, 64);
    hip4.mirror = true;
    setRotation(hip4, 0.0698132F, 0F, 0F);
    Leg4toProt = new ModelRenderer(this, 81, 13);
    Leg4toProt.addBox(-1.5F, 0F, -1.5F, 3, 6, 3);
    //Leg4toProt.setRotationPoint(4F, 12F, 6F);
    Leg4toProt.setRotationPoint(1F, 2F, 1F);
    Leg4toProt.setTextureSize(128, 64);
    Leg4toProt.mirror = true;
    setRotation(Leg4toProt, 0.0872665F, 0F, 0F);
    hip4.addChild(Leg4toProt);
    Leg4BotRot = new ModelRenderer(this, 81, 25);
    Leg4BotRot.addBox(-1.5F, 0F, -1.5F, 3, 7, 3);
    //Leg4BotRot.setRotationPoint(4F, 17F, 6.5F);
    Leg4BotRot.setRotationPoint(0F, 6F, 0.5F);
    Leg4BotRot.setTextureSize(128, 64);
    Leg4BotRot.mirror = true;
    setRotation(Leg4BotRot, -0.0698132F, 0F, 0F);
    Leg4toProt.addChild(Leg4BotRot);
    tail = new ModelRenderer(this, 109, 13);
    tail.addBox(-1F, -1F, 0F, 2, 7, 2);
    tail.setRotationPoint(0F, 7F  - heightToRaise, 7.6F);
    tail.setTextureSize(128, 64);
    tail.mirror = true;
    setRotation(tail, 0.5585054F, 0F, 0F);
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
	            HEAD.render(renderScale * f5);
	            neckTop.render(renderScale * f5);
	            neckBase.render(renderScale * f5);
	            humpMain.render(renderScale * f5);
	            humpMain2.render(renderScale * f5);
	            saddleBase.render(renderScale * f5);
	            saddleHorn1.render(renderScale * f5);
	            saddleHorn2.render(renderScale * f5);
	            Body.render(renderScale * f5);
	            hip1.render(renderScale * f5);
	            //Leg1toProt.render(f5);
	            //Leg1BotRot.render(f5);
	            hip2.render(renderScale * f5);
	            //Leg2toProt.render(f5);
	            //Leg2BotRot.render(f5);
	            hip3.render(renderScale * f5);
	            //Leg3toProt.render(f5);
	            //Leg3BotRot.render(f5);
	            hip4.render(renderScale * f5);
	            //Leg4toProt.render(f5);
	            //Leg4BotRot.render(f5);
	            tail.render(renderScale * f5);
	            GL11.glPopMatrix();		   
	    } else {
	    	GL11.glPushMatrix();
            GL11.glScalef(1.6F, 1.6F, 1.6F);
            GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
            HEAD.render(renderScale * f5);
            neckTop.render(renderScale * f5);
            neckBase.render(renderScale * f5);
            humpMain.render(renderScale * f5);
            humpMain2.render(renderScale * f5);
            saddleBase.render(renderScale * f5);
            saddleHorn1.render(renderScale * f5);
            saddleHorn2.render(renderScale * f5);
            Body.render(renderScale * f5);
            hip1.render(renderScale * f5);
            hip2.render(renderScale * f5);
            hip3.render(renderScale * f5);
            hip4.render(renderScale * f5);
            tail.render(renderScale * f5);
            GL11.glPopMatrix();
	    }  
  }
  
  @Override
  public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
	/* Tail Rotation */
	//NECKROT.rotateAngleX = (float) (45 * Math.PI / 180 + 7 * Math.PI / 180
		//* MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper.abs(Math.log(par3 + 1)));
	/* Tail Rotation */
	tail.rotateAngleZ = (float) (0.5f * MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper
		.abs(Math.log(par3 + 1)));
	/* Leg Animation */
	hip1.rotateAngleX = (float) (MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper
			.abs(Math.log(par3 + 1)));
	//Leg1toProt.rotateAngleX = (float) (MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper
	//	.abs(Math.log(par3 + 1)));
	hip2.rotateAngleX = (float) (MathHelper.cos(par2 * 0.6662F) * 1.8F * ModelHelper.abs(Math.log(par3 + 1)));
	
	//Leg2toProt.rotateAngleX = (float) (MathHelper.cos(par2 * 0.6662F) * 1.8F * ModelHelper.abs(Math.log(par3 + 1)));
	
	Leg1BotRot.rotateAngleX = (float) Math.abs(MathHelper.cos(par2 * 0.6662F / 2 + (float) Math.PI) * 1.8F
		* ModelHelper.abs(Math.log(par3 + 1)));
	Leg2BotRot.rotateAngleX = (float) Math.abs(MathHelper.cos(par2 * 0.6662F / 2) * 1.8F
		* ModelHelper.abs(Math.log(par3 + 1)));

	hip3.rotateAngleX = (float) (MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper
			.abs(Math.log(par3 + 1)));
	
	//Leg3toProt.rotateAngleX = (float) (MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper
	//	.abs(Math.log(par3 + 1)));
	hip4.rotateAngleX = (float) (MathHelper.cos(par2 * 0.6662F) * 1.8F * ModelHelper.abs(Math.log(par3 + 1)));
			
	//Leg4toProt.rotateAngleX = (float) (MathHelper.cos(par2 * 0.6662F) * 1.8F * ModelHelper.abs(Math.log(par3 + 1)));
	Leg3BotRot.rotateAngleX = (float) Math.abs(MathHelper.cos(par2 * 0.6662F / 2 + (float) Math.PI) * 1.8F
		* ModelHelper.abs(Math.log(par3 + 1)));
	Leg4BotRot.rotateAngleX = (float) Math.abs(MathHelper.cos(par2 * 0.6662F / 2) * 1.8F
		* ModelHelper.abs(Math.log(par3 + 1)));

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
    HEAD.rotateAngleX = Math.min(Math.max(f4, -14), +15) * (float) (Math.PI / 180f);
	HEAD.rotateAngleY = Math.min(Math.max(f3, -15), +15) * (float) (Math.PI / 180f);
  }

	}

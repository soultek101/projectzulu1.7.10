package com.stek101.projectzulu.common.mobs.entityai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import com.stek101.projectzulu.common.mobs.entity.EntityGenericAnimal;
import com.stek101.projectzulu.common.mobs.entity.EntityStates;

public class EntityAIFollowOwner extends EntityAIBase{
    private EntityGenericAnimal thePet;
    private EntityLivingBase theOwner;
    World theWorld;
    private float moveSpeed;
    private PathNavigate petPathfinder;
    private int field_75343_h;
    float maxDist;
    float minDist;
    private boolean field_75344_i;

    public EntityAIFollowOwner(EntityGenericAnimal par1EntityTameable, float par2, float par3, float par4){
        this.thePet = par1EntityTameable;
        this.theWorld = par1EntityTameable.worldObj;
        this.moveSpeed = par2;
        this.petPathfinder = par1EntityTameable.getNavigator();
        this.minDist = par3;
        this.maxDist = par4;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute(){
        EntityLivingBase var1 = this.thePet.getOwner();

        if (var1 == null){
            return false;
        }
        else if (this.thePet.getEntityState() != EntityStates.following){
            return false;
        }
        else if (this.thePet.getDistanceSqToEntity(var1) < (double)(this.minDist * this.minDist)){
            return false;
        }
        else{
            this.theOwner = var1;
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting(){
        return !this.petPathfinder.noPath() && this.thePet.getDistanceSqToEntity(this.theOwner) > (double)(this.maxDist * this.maxDist) 
        		&& !this.thePet.isSitting();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting(){
        this.field_75343_h = 0;
        this.field_75344_i = this.thePet.getNavigator().getAvoidsWater();
        this.thePet.getNavigator().setAvoidsWater(false);
    }

    /**
     * Resets the task
     */
    public void resetTask(){
        this.theOwner = null;
        this.petPathfinder.clearPathEntity();
        this.thePet.getNavigator().setAvoidsWater(this.field_75344_i);
    }

    /**
     * Updates the task
     */
    public void updateTask(){
    	this.thePet.getLookHelper().setLookPositionWithEntity(this.theOwner, 10.0F, (float)this.thePet.getVerticalFaceSpeed());

    	if (!this.thePet.isSitting() && --this.field_75343_h <= 0){
    		this.field_75343_h = 10;

    		if (!this.petPathfinder.tryMoveToEntityLiving(this.theOwner, this.moveSpeed) && this.thePet.getDistanceSqToEntity(this.theOwner) >= 144.0D){
    			int var1 = MathHelper.floor_double(this.theOwner.posX) - 2;
    			int var2 = MathHelper.floor_double(this.theOwner.posZ) - 2;
    			int var3 = MathHelper.floor_double(this.theOwner.boundingBox.minY);

    			for (int var4 = 0; var4 <= 4; ++var4){
    				for (int var5 = 0; var5 <= 4; ++var5){
    					if ((var4 < 1 || var5 < 1 || var4 > 3 || var5 > 3) 
    							&& World.doesBlockHaveSolidTopSurface(theWorld, var1 + var4, var3 - 1, var2 + var5) 
    							&& !theWorld.getBlock(var1 + var4, var3, var2 + var5).isNormalCube() 
    							&& !theWorld.getBlock(var1 + var4, var3 + 1, var2 + var5).isNormalCube()){
    						this.thePet.setLocationAndAngles(
    								(double)((float)(var1 + var4) + 0.5F),
    								(double)var3,
    								(double)((float)(var2 + var5) + 0.5F),
    								this.thePet.rotationYaw, this.thePet.rotationPitch);
    						this.petPathfinder.clearPathEntity();
    						return;
    					}
    				}
    			}
    		}
    	}
    }
}


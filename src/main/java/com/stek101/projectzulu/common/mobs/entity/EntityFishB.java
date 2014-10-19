package com.stek101.projectzulu.common.mobs.entity;

import java.util.Random;

import net.minecraft.entity.EntityList;
import net.minecraft.world.World;

import com.stek101.projectzulu.common.api.CustomEntityList;
import com.stek101.projectzulu.common.mobs.EntityAFightorFlight;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIPanicSwim;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIWanderSwim;

public class EntityFishB extends EntityGenericWaterMob { 
	private EntityAFightorFlight EAFF;
	private CustomEntityList entityEntry;
	private float aggroLevel;
	private double aggroRange;

	public EntityFishB(World par1World) {
        super(par1World); 
        setSize(1.2f, 0.9f);
        Random rand1 = new Random();
        this.textureID = rand1.nextInt(3);

        this.entityEntry = CustomEntityList.getByName(EntityList.getEntityString(this));
        
        /* Check if aggroLevel and aggroRange have valid values to activate AFoF */
   	  	if (entityEntry != null && entityEntry.modData.get().entityProperties != null) {
           this.aggroLevel = entityEntry.modData.get().entityProperties.aggroLevel;
           this.aggroRange = entityEntry.modData.get().entityProperties.aggroRange;                    
   	  	}
   	  
   	  	if (Math.round(this.aggroRange) != 0) {
   	  		EAFF = new EntityAFightorFlight().setEntity(this, worldObj, this.aggroLevel, this.aggroRange);
   	  	}
   	 
        this.maxFlightHeight =5;
        this.tasks.addTask(1, new EntityAIWanderSwim(this, 0.3f, 60D));
        this.tasks.addTask(2, new EntityAIPanicSwim(this, 1.25f, 60D));
    }
	
    @Override
    public int getTotalArmorValue() {
        return 0;
    }
    
    @Override
    public boolean defaultGrounded() {
        return false;
    }

    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    @Override
    protected void fall(float par1) {
    }

    /**
     * Takes in the distance the entity has fallen this tick and whether its on the ground to update the fall distance
     * and deal fall damage if landing on the ground. Args: distanceFallenThisTick, onGround
     */
    @Override
    protected void updateFallState(double par1, boolean par3) {
    }

    @Override
    public void onLivingUpdate() {
    	super.onLivingUpdate();
    	if (Math.round(this.aggroRange) != 0) {
    		EAFF.updateEntityAFF(worldObj);
    	}
    } 
    
   
    /**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	@Override
	public boolean getCanSpawnHere() {
		return this.posY > 45.0D && this.posY < 63.0D && this.worldObj.checkNoEntityCollision(this.boundingBox);
	}
}
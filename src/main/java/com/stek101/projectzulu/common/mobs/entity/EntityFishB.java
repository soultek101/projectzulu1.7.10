package com.stek101.projectzulu.common.mobs.entity;

import java.util.Random;

import net.minecraft.entity.EntityList;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
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
   	 
        this.maxFlightHeight = 5;
        this.getNavigator().setCanSwim(true);
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
    protected boolean isValidLocation(World world, int xCoord, int yCoord, int zCoord) {
        return worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
    }

    @Override
    public void onLivingUpdate() {
    	super.onLivingUpdate();
    	if (Math.round(this.aggroRange) != 0) {
    		EAFF.updateEntityAFF(worldObj);
    	}
    }
    
    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound()
    {
        return null;
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound()
    {
        return null;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected String getDeathSound()
    {
        return null;
    }
    
    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    @Override
    public boolean getCanSpawnHere()
    {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);
		
        return this.posY > 45.0D && this.posY < 63.0D && worldObj.canBlockSeeTheSky(var1, var2, var3)
        		&& this.worldObj.getSavedLightValue(EnumSkyBlock.Block, var1, var2, var3) < 1
        		&& super.getCanSpawnHere();
    }
	   
	   
}
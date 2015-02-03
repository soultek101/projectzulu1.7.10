package com.stek101.projectzulu.common.mobs.entity;

import java.util.Random;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.stek101.projectzulu.common.api.CustomEntityList;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.mobs.EntityAFightorFlight;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIFollowParent;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIMate;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIPanic;
import com.stek101.projectzulu.common.mobs.entityai.EntityAITempt;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIWander;


public class EntityBeetleBS extends EntityGenericAnimal implements IAnimals {
	private EntityAFightorFlight EAFF;
	private CustomEntityList entityEntry;
	private float aggroLevel;
	private double aggroRange;
   
	public EntityBeetleBS(World par1World) {
      super(par1World);
      this.setSize(0.35f, 0.35f);
      this.setPosition(this.posX, this.posY, this.posZ);
      
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
  	  
        getNavigator().setAvoidsWater(true);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIPanic(this, 1.25f));
        tasks.addTask(2, new EntityAIWander(this, 1.0f, 120));
        
        /* Attack On Collide Declared in SubClass */
        tasks.addTask(5, new EntityAIMate(this, 1.0f));
        tasks.addTask(6, new EntityAITempt(this, 1.2f, Items.potato, false));
        tasks.addTask(7, new EntityAIFollowParent(this, 1.1f));
        tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0f));
        
        //targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, false));
        //targetTasks.addTask(4, 
       //         new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking),
       //                 EntityPlayer.class, 16.0F, 0, true));
       // targetTasks.addTask(5,
       //         new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking),
       //                 EntityLiving.class, 16.0F, 0, false, true, IMob.mobSelector));
    }
    
	 /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound() {
        return DefaultProps.mobKey + ":" + DefaultProps.entitySounds + "beetlelivingsound";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound() {
        return DefaultProps.mobKey + ":" + DefaultProps.entitySounds + "beetlehurtsound";
    }
    
    @Override
    public int getTalkInterval() {
        return 160;
    }

    @Override
    public void onLivingUpdate() {
    	super.onLivingUpdate();
    	if (Math.round(this.aggroRange) != 0) {
    		EAFF.updateEntityAFF(worldObj, Items.potato);
    	}
    	
    	if (this.ticksExisted > 10000){
    		//this.despawnEntity();
    		this.setDead();
    	}
    }
    
    @Override
    protected int getExperiencePoints(EntityPlayer p_70693_1_)
    {
        return 0;
    }
    
    @Override
    protected void updateAITick() {     
        super.updateAITick();
    }
    
    /**
     * Checks if the Provided ItemStack is considered an item that should be used for Breeding This is overriden by each
     * Entity if deviations from default are desired
     */
    @Override
    public boolean isValidBreedingItem(ItemStack itemStack) {
        if (itemStack != null
                && (itemStack.getItem() == Items.potato)) {
        	this.setAngerLevel(0);
        	this.setFleeTick(0);
            return true;
        } else {
            return super.isValidBreedingItem(itemStack);
        }
    }
}
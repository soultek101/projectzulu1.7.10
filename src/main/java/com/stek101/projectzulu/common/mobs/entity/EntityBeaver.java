package com.stek101.projectzulu.common.mobs.entity;

import java.util.EnumSet;
import java.util.Random;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.stek101.projectzulu.common.api.BlockList;
import com.stek101.projectzulu.common.api.CustomEntityList;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIAvoidEntity;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import com.stek101.projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIPanic;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIWander;

import cpw.mods.fml.common.Loader;

public class EntityBeaver extends EntityGenericAnimal implements IAnimals {
	
	private boolean isHurt = false;
	private float aggroLevel;
	private double aggroRange;
	private EntityAIAvoidEntity aiEntityAvoidEntity = new EntityAIAvoidEntity(this, EntityPlayer.class, 16.0f, 1.3D, 1.2D);

    public EntityBeaver(World par1World) {
        super(par1World);
        setSize(0.63f, 0.8f);
        
        /*Get Aggression rank of entity and range*/
        CustomEntityList entityEntry = CustomEntityList.getByName(EntityList.getEntityString(this));
         if (entityEntry != null && entityEntry.modData.get().entityProperties != null) {
             aggroLevel = entityEntry.modData.get().entityProperties.aggroLevel;
             aggroRange = entityEntry.modData.get().entityProperties.aggroRange;
         }

        getNavigator().setAvoidsWater(false);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIPanic(this, 1.25f));

        tasks.addTask(3, new EntityAIAttackOnCollide(this, 1.0f, false));
        // tasks.addTask(4, new EntityAIFollowOwner(this, moveSpeed, 10.0F, 2.0F));

        // tasks.addTask(5, new EntityAIMate(this, moveSpeed));
        // tasks.addTask(6, new EntityAITempt(this, moveSpeed, Items.spider_eye, false));
        // tasks.addTask(7, new EntityAIFollowParent(this, moveSpeed));
        tasks.addTask(9, new EntityAIWander(this, 1.0f, 120));

        targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, false));
        targetTasks.addTask(4,
                new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking),
                        EntityPlayer.class, 16.0F, 0, true));
        // targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityLiving.class, 16.0F, 0, false, true,
        // IMob.mobSelector));
    }

    @Override
    protected boolean isValidLocation(World world, int xCoord, int yCoord, int zCoord) {
        return super.isValidLocation(world, xCoord, yCoord, zCoord)
                && worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
    }

    @Override
    public void onLivingUpdate() {
    	super.onLivingUpdate();
    	
   	 if (aggroRange != 0 && aggroLevel != 0) {  /** 0 and 0 means deactivate FoF behaviour **/
   	        EntityPlayer entityplayer = worldObj.getClosestPlayerToEntity(this, aggroRange);
   	        
   	        /* Check if target can be detected, then entity will decide whether to fight or flee, ignore if tamed */
   	        //if (!(this instanceof EntityGenericTameable) && !((EntityGenericTameable)this).isTamed()){
   	        if (!((EntityGenericTameable)this).isTamed()){
   	          if(entityplayer != null && canEntityBeSeen(entityplayer)) {
   	          	
   	          	Random rand = new Random();         	
   	          	int dieRoll = rand.nextInt(100);    	
   	          	
   	             if ((aggroLevel) >= dieRoll){
   	          		if (this.getAngerLevel() == 0 && this.getFleeTick() == 0){
   	          		  this.tasks.removeTask(this.aiEntityAvoidEntity);
   	          		  this.angerLevel = 400;  //make it angry!!!
   	          		  
   	          		  }
   	          	} else {
   	          		if (this.getAngerLevel() == 0 && this.getFleeTick() == 0){ 
   	          		 this.tasks.addTask(1, this.aiEntityAvoidEntity );
   	          		 this.setFleeTick(400);
   	          		 
   	          		 }
   	          	}
   	           } else {
   	         	 this.angerLevel = 0; // calm down and move on with life
   	         	 this.setFleeTick(0);
   	           }          
   	          }
   	        
   	        /* A wounded animal is a very dangerous animal */
   	     	if (this.getMaxHealth() > this.getHealth() && isHurt == false){
   	     		aggroLevel = aggroLevel + 25;
   	     		isHurt = true;
   	     	}
   	      }
    }
    
    /**
     * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
     */
    @Override
    public int getTotalArmorValue() {
        return 0;
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound() {
        return DefaultProps.mobKey + ":" + DefaultProps.entitySounds + "beaverliving";
    }

    @Override
    protected void dropRareDrop(int par1) {
        if (Loader.isModLoaded(DefaultProps.BlocksModId) && BlockList.mobHeads.isPresent()) {
            entityDropItem(new ItemStack(BlockList.mobHeads.get(), 1, 6), 1);
        }
        super.dropRareDrop(par1);
    }
}
package com.stek101.projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.Random;

import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIFollowParent;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIMate;
import com.stek101.projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIPanic;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIStayStill;
import com.stek101.projectzulu.common.mobs.entityai.EntityAITempt;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIWander;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIAvoidEntity;
import com.stek101.projectzulu.common.mobs.entityai.RandomPositionGenerator;
import com.stek101.projectzulu.common.api.CustomEntityList;
import com.stek101.projectzulu.common.api.CustomMobData;


public class EntityBear extends EntityGenericAnimal implements IAnimals {
    private boolean isHurt = false;
	private float aggroLevel;
	private double aggroRange;
	private EntityAIAvoidEntity aiEntityAvoidEntity = new EntityAIAvoidEntity(this, EntityPlayer.class, 16.0f, 1.3D, 1.2D);

	public EntityBear(World par1World) {
        super(par1World);

        /*Get Aggression rank of entity and range*/
        CustomEntityList entityEntry = CustomEntityList.getByName(EntityList.getEntityString(this));
         if (entityEntry != null && entityEntry.modData.get().entityProperties != null) {
             aggroLevel = entityEntry.modData.get().entityProperties.aggroLevel;
             aggroRange = entityEntry.modData.get().entityProperties.aggroRange;
         }
                
     
        getNavigator().setAvoidsWater(true);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIPanic(this, 1.25f));
        tasks.addTask(2, new EntityAIWander(this, 1.0f, 120));
        
        /* Attack On Collide Declared in SubClass */
        tasks.addTask(5, new EntityAIMate(this, 1.0f));
        tasks.addTask(6, new EntityAITempt(this, 1.2f, Items.spider_eye, false));
        tasks.addTask(7, new EntityAIFollowParent(this, 1.1f));
        tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0f));
        
        targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, false));
        targetTasks.addTask(4, 
                new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking),
                        EntityPlayer.class, 16.0F, 0, true));
        targetTasks.addTask(5,
                new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking),
                        EntityLiving.class, 16.0F, 0, false, true, IMob.mobSelector));
    }
    
    @Override
    protected String getHurtSound() {
        return DefaultProps.mobKey + ":" + DefaultProps.entitySounds + "bearliving";
    }

    @Override
    public boolean shouldNotifySimilar(EntityPlayer attackingPlayer) {
        return true;
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
    	          		  //System.out.println("FIGHT FIGHT FIGHT, aggrolevel of " + aggroLevel + " dieRoll of " + dieRoll);         		
    	          		  }
    	          	} else {
    	          		if (this.getAngerLevel() == 0 && this.getFleeTick() == 0){ 
    	          		 this.tasks.addTask(1, this.aiEntityAvoidEntity );
    	          		 this.setFleeTick(400);
    	          		 //System.out.println("FLEEEEEEEEEEEE, aggrolevel of " + aggroLevel + " dieRoll of " + dieRoll); 
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
                && (itemStack.getItem() == Items.fish || itemStack.getItem() == Items.cooked_fished)) {
        	this.setAngerLevel(0);
        	this.setFleeTick(0);
            return true;
        } else {
            return super.isValidBreedingItem(itemStack);
        }
    }
}
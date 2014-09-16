package com.stek101.projectzulu.common.mobs.entity;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.EnumSet;
import java.util.Random;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.stek101.projectzulu.common.ProjectZulu_Core;
import com.stek101.projectzulu.common.api.CustomEntityList;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIAvoidEntity;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIFollowOwner;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIFollowParent;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIMate;
import com.stek101.projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIOwnerHurtByTarget;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIOwnerHurtTarget;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIPanic;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIStayStill;
import com.stek101.projectzulu.common.mobs.entityai.EntityAITempt;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIWander;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;

public class EntityFox extends EntityGenericAnimal implements IAnimals {
	private boolean isHurt = false;
	private float aggroLevel;
	private double aggroRange;
	private EntityAIAvoidEntity aiEntityAvoidEntity = new EntityAIAvoidEntity(this, EntityPlayer.class, 16.0f, 1.3D, 1.2D);

    public EntityFox(World par1World) {
        super(par1World);
        setSize(0.6f, 1.0f);
        
        /*Get Aggression rank of entity and range*/
        CustomEntityList entityEntry = CustomEntityList.getByName(EntityList.getEntityString(this));
         if (entityEntry != null && entityEntry.modData.get().entityProperties != null) {
             aggroLevel = entityEntry.modData.get().entityProperties.aggroLevel;
             aggroRange = entityEntry.modData.get().entityProperties.aggroRange;
         }

        getNavigator().setAvoidsWater(true);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIPanic(this, 1.25f));
        tasks.addTask(2, new EntityAIStayStill(this, EntityStates.sitting));

        tasks.addTask(3, new EntityAIAttackOnCollide(this, 1.0f, false));
        tasks.addTask(4, new EntityAIFollowOwner(this, 1.0f, 10.0F, 2.0F));
        tasks.addTask(5, new EntityAIMate(this, 1.0f));
        tasks.addTask(6, new EntityAITempt(this, 1.2f, Items.chicken, false));
        tasks.addTask(7, new EntityAIFollowParent(this, 1.1f));
        tasks.addTask(9, new EntityAIWander(this, 1.0f, 120));
        tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        tasks.addTask(11, new EntityAILookIdle(this));

        targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, false));
        targetTasks.addTask(4,
                new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking),
                        EntityPlayer.class, 16.0F, 0, true));
        targetTasks.addTask(5,
                new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking),
                        EntityLiving.class, 16.0F, 0, false, true, IMob.mobSelector));
    }

    @Override
    public int getTotalArmorValue() {
        return 2;
    }
    
    @Override
    protected void updateAITick() {	   	
        super.updateAITick();
    }
    
    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound() {
        return DefaultProps.mobKey + ":" + DefaultProps.entitySounds + "foxhurtsound";
    }

    @Override
    protected boolean isValidLocation(World world, int xCoord, int yCoord, int zCoord) {
        return worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
    }

    @Override
    public void onLivingUpdate() {
      super.onLivingUpdate();
    	
   	  if (aggroRange != 0 && aggroLevel != 0) {  /** 0 and 0 means deactivate FoF behaviour **/
   	        EntityPlayer entityplayer = worldObj.getClosestPlayerToEntity(this, aggroRange);
   	        
   	        /* Check if target can be detected, then entity will decide whether to fight or flee, ignore if tamed */
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

    @Override
    public boolean isValidTamingItem(ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }

        if (itemStack.getItem() == Items.cooked_chicken) {
            return true;
        }
        return super.isValidTamingItem(itemStack);
    }

    @Override
    public boolean isValidBreedingItem(ItemStack itemStack) {
        if (itemStack != null && itemStack.getItem() == Items.chicken) {
        	this.setAngerLevel(0);
        	this.setFleeTick(0);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean shouldNotifySimilar(EntityPlayer attackingPlayer) {
        return true;
    }

    /**
     * Validates if Itemstack can be used to Heal Entity Caution: ItemStack may be Null
     */
    @Override
    public int getHealingValueIfValid(ItemStack itemStack) {

        if (itemStack == null) {
            return 0;
        }

        if (itemStack.getItem() == Items.cooked_chicken) {
            return ((ItemFood) Items.melon).func_150905_g(itemStack);
        }

        return 0;
    }

    @Override
    public boolean isRideable() {
        return super.isRideable();
    }

    @Override
    protected boolean shouldPanic() {
        if (isTamed()) {
            return false;
        } else {
            return super.shouldPanic();
        }
    }

}
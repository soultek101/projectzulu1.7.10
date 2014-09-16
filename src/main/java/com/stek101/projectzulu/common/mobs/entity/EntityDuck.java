package com.stek101.projectzulu.common.mobs.entity;

import java.util.EnumSet;
import java.util.Random;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.google.common.base.Optional;
import com.stek101.projectzulu.common.api.BlockList;
import com.stek101.projectzulu.common.api.CustomEntityList;
import com.stek101.projectzulu.common.api.ItemList;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIAvoidEntity;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIFollowParent;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIMate;
import com.stek101.projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIPanic;
import com.stek101.projectzulu.common.mobs.entityai.EntityAITempt;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIWander;

import cpw.mods.fml.common.Loader;

public class EntityDuck extends EntityGenericAnimal implements IAnimals {
	
	private boolean isHurt = false;
	private float aggroLevel;
	private double aggroRange;
	private EntityAIAvoidEntity aiEntityAvoidEntity = new EntityAIAvoidEntity(this, EntityPlayer.class, 16.0f, 1.3D, 1.2D);

    private int timeUntilNextEgg;
    private int timeUntilNextFeather;
    int stayStillTimer = 0;
    
	public EntityDuck(World par1World) {
        super(par1World);
        this.setSize(0.9F, 0.9F);
        this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
        this.timeUntilNextFeather = this.rand.nextInt(6000) + 6000;
        
        /*Get Aggression rank of entity and range*/
        CustomEntityList entityEntry = CustomEntityList.getByName(EntityList.getEntityString(this));
         if (entityEntry != null && entityEntry.modData.get().entityProperties != null) {
             aggroLevel = entityEntry.modData.get().entityProperties.aggroLevel;
             aggroRange = entityEntry.modData.get().entityProperties.aggroRange;
         }
           
        getNavigator().setAvoidsWater(false);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 0.6f));
        this.tasks.addTask(2, new EntityAIMate(this, 0.25f));
        this.tasks.addTask(3, new EntityAITempt(this, 0.25f, Items.fish, false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 0.3f));
        this.tasks.addTask(5, new EntityAIWander(this, 0.25f, 120));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.tasks.addTask(8, new EntityAIAttackOnCollide(this, 1.0f, false));
        
        targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, false));
        targetTasks.addTask(4,
                new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking),
                        EntityPlayer.class, 16.0F, 0, true));
        targetTasks.addTask(5,
                new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking),
                        EntityLiving.class, 16.0F, 0, false, true, IMob.mobSelector));
    }
    
    @Override
    protected boolean isValidLocation(World world, int xCoord, int yCoord, int zCoord) {
        return worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
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
     	          		  //System.out.println("Attack Mode!");
     	          		  }
     	          	} else {
     	          		if (this.getAngerLevel() == 0 && this.getFleeTick() == 0){ 
     	          		 this.tasks.addTask(1, this.aiEntityAvoidEntity );
     	          		 this.setFleeTick(400);  
     	          		 //System.out.println("Flee Mode!");
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
     	  
     	if (!this.worldObj.isRemote && !this.isChild() &&  --this.timeUntilNextEgg <= 0)
         {
     		 Optional<?> itemBlock = ItemList.duckEgg;
             this.playSound("mob.chicken.plop", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);

         	 ItemStack itemStackToDrop = new ItemStack((Item)itemBlock.get());
         	 entityDropItem(itemStackToDrop, 1);
             
             this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
         }
     	 
     	if (!this.worldObj.isRemote && !this.isChild() && --this.timeUntilNextFeather <= 0)
        {
            this.dropItem(Items.feather, 1);
            this.timeUntilNextFeather = this.rand.nextInt(6000) + 6000;
        }
    }

    /**
     * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
     */
    @Override
    public int getTotalArmorValue() {
        return 0;
    }
    
    @Override
    protected boolean shouldPanic() {
        return true;
    }

    @Override
    public void updateAIState() {
       
		if (stayStillTimer > 0) {
            entityState = EntityStates.posture;
        } else {
            super.updateAIState();
        }
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound() {
        return DefaultProps.mobKey + ":" + DefaultProps.entitySounds + "duckliving";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound() {
        return DefaultProps.mobKey + ":" + DefaultProps.entitySounds + "duckliving";
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

    @Override
    protected void dropRareDrop(int par1) {
        if (Loader.isModLoaded(DefaultProps.BlocksModId) && BlockList.mobHeads.isPresent()) {
            entityDropItem(new ItemStack(BlockList.mobHeads.get(), 1, 13), 1);
        }
        super.dropRareDrop(par1);
    }

}
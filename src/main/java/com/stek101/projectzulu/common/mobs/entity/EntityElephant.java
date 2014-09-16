package com.stek101.projectzulu.common.mobs.entity;

import java.util.EnumSet;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.stek101.projectzulu.common.api.BlockList;
import com.stek101.projectzulu.common.api.CustomEntityList;
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

public class EntityElephant extends EntityGenericAnimal {
	private boolean isHurt = false;
	private float aggroLevel;
	private double aggroRange;
	private EntityAIAvoidEntity aiEntityAvoidEntity = new EntityAIAvoidEntity(this, EntityPlayer.class, 16.0f, 1.3D, 1.2D);
	
    private float ridingRotation = 0;

    public EntityElephant(World par1World) {
        super(par1World);
        setSize(4.0f, 5.4f);

        /*Get Aggression rank of entity and range*/
        CustomEntityList entityEntry = CustomEntityList.getByName(EntityList.getEntityString(this));
         if (entityEntry != null && entityEntry.modData.get().entityProperties != null) {
             aggroLevel = entityEntry.modData.get().entityProperties.aggroLevel;
             aggroRange = entityEntry.modData.get().entityProperties.aggroRange;
         }
         
        getNavigator().setAvoidsWater(true);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIPanic(this, 1.25f));

        // tasks.addTask(3, new EntityAIAttackOnCollide(this, moveSpeed, false));
        tasks.addTask(3, new EntityAIAttackOnCollide(this, 1.0f, false, 4.0f * 4.0f));

        // tasks.addTask(4, new EntityAIFollowOwner(this, moveSpeed, 10.0F, 2.0F));

        tasks.addTask(5, new EntityAIMate(this, 1.0f));
        tasks.addTask(6, new EntityAITempt(this, 1.2f, Item.getItemFromBlock(Blocks.leaves), false));
        tasks.addTask(7, new EntityAIFollowParent(this, 1.1f));
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
        return worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
    }
    
    @Override
    protected void updateAITick() {       
        super.updateAITick();
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
        return 6;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound() {
        return DefaultProps.mobKey + ":" + DefaultProps.entitySounds + "elephantlivingsound";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound() {
        return DefaultProps.mobKey + ":" + DefaultProps.entitySounds + "elephanthurtsound";
    }

    @Override
    public void knockBack(Entity par1Entity, float par2, double par3, double par5) {
        if (this.rand.nextDouble() >= this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue()) {
            this.isAirBorne = true;
            float var7 = MathHelper.sqrt_double(par3 * par3 + par5 * par5);
            float var8 = 0.4F;
            this.motionX /= 2.0D;
            this.motionY /= 2.0D;
            this.motionZ /= 2.0D;
            this.motionX -= par3 / var7 * var8 * 0.2;
            this.motionY += var8;
            this.motionZ -= par5 / var7 * var8 * 0.2;

            if (this.motionY > 0.2000000059604645D) {
                this.motionY = 0.2000000059604645D;
            }
        }
    }

    @Override
    public boolean isValidBreedingItem(ItemStack itemStack) {
        if (itemStack != null && itemStack.getItem() == Item.getItemFromBlock(Blocks.leaves)) {
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
            entityDropItem(new ItemStack(BlockList.mobHeads.get(), 1, 17), 1);
        }
        super.dropRareDrop(par1);
    }

}
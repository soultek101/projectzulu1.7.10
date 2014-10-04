package com.stek101.projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.stek101.projectzulu.common.api.CustomEntityList;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.mobs.EntityAFightorFlight;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
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

public class EntityFox extends EntityGenericAnimal implements IAnimals {
	private EntityAFightorFlight EAFF;
	private CustomEntityList entityEntry;
	private float aggroLevel;
	private double aggroRange;

    public EntityFox(World par1World) {
        super(par1World);
        setSize(0.6f, 1.0f);
        
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
    public int getTalkInterval() {
        return 160;
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
    	
      if (Math.round(this.aggroRange) != 0) {
  		EAFF.updateEntityAFF(worldObj);
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
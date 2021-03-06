package com.stek101.projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import com.stek101.projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIPanic;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIStayStill;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIWander;

public class EntityBloomDoom extends EntityGenericAnimal implements IMob {

    protected int wakeUpTimer = 0;

    public int getWakeUpTimer() {
        return wakeUpTimer;
    }

    boolean shouldHover = false;

    public EntityBloomDoom(World par1World) {
        super(par1World);
        setSize(1.0f, 1.0f);
        getNavigator().setAvoidsWater(true);

        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIPanic(this, 1.25f));
        tasks.addTask(2, new EntityAIStayStill(this, EntityStates.idle));
        tasks.addTask(3, new EntityAIAttackOnCollide(this, 1.0f, false));
        tasks.addTask(5, new EntityAIWander(this, 1.0f, 120));
        targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, false));
        targetTasks.addTask(4,
                new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking),
                        EntityPlayer.class, 16.0F, 0, true));
    }

    public EntityBloomDoom(World par1World, double parx, double pary, double parz, boolean shouldHover) {
        this(par1World);
        setLocationAndAngles(parx, pary, parz, 1, 1);
        setPosition(parx, pary, parz);
        this.shouldHover = shouldHover;
    }

    @Override
    public int getTotalArmorValue() {
        return 2;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound() {
      if (getEntityState() != EntityStates.idle){
        return DefaultProps.mobKey + ":" + DefaultProps.entitySounds + "dbloomlivingsound";
      }
      return null;
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound() {
        return DefaultProps.mobKey + ":" + DefaultProps.entitySounds + "dbloomhurtsound";
    }   
    
    @Override
    public int getTalkInterval() {
        return 160;
    }

    @Override
    public void onLivingUpdate() {
        if (shouldHover == true) {
            this.motionY = 0;
            if (ticksExisted > (20 * 10)) {
                shouldHover = false;
            }
        }

        if (this.worldObj.isDaytime() && !this.worldObj.isRemote && ticksExisted % (10 * 20) == 0) {
            heal(1);
        }
        
        super.onLivingUpdate();

        /* If Player is Still Nearby after activation, do not become passive */
        if (getEntityState() != EntityStates.idle && worldObj.getClosestPlayerToEntity(this, 8D) != null) {
            setAngerLevel(400);
        }
    }

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    @Override
    public boolean canBePushed() {
        if (getEntityState() == EntityStates.idle) {
            return false;
        } else {
            return !this.isDead;
        }
    }

    // Called when player interacts with mob, eg get milk, saddle
    @Override
    public boolean interact(EntityPlayer par1EntityPlayer) {
        entityAttackedReaction(par1EntityPlayer);
        return super.interact(par1EntityPlayer);
    }
 
}
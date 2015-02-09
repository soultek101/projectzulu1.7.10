package com.stek101.projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.stek101.projectzulu.common.api.CustomEntityList;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.mobs.EntityAFightorFlight;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIFollowParent;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIMate;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIMoveTowardsRestriction;
import com.stek101.projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIPanic;
import com.stek101.projectzulu.common.mobs.entityai.EntityAITempt;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIWander;

public class EntityMonkey extends EntityGenericAnimal implements IAnimals{
	//private EntityAIArrowAttack aiRockAttack = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
	private EntityAFightorFlight EAFF;
	private CustomEntityList entityEntry;
	private float aggroLevel;
	private double aggroRange;
	private boolean throwRock = false;
	private int throwCooldown = 6 * 20;
    private int throwTimer = 100;
    private int stage = 1;
	
	public EntityMonkey(World par1World) {
        super(par1World);
        setSize(1.4f, 0.9f);
        this.setPosition(this.posX, this.posY, this.posZ);
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
        tasks.addTask(1, new EntityAIPanic(this, 1.0f));
        tasks.addTask(2, new EntityAIAttackOnCollide(this, 1.0f, false));    
        tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
        tasks.addTask(4, new EntityAILookIdle(this));
        tasks.addTask(5, new EntityAIMate(this, 0.8f));
        tasks.addTask(6, new EntityAITempt(this, 0.9f, Items.bread, false));
        tasks.addTask(7, new EntityAIFollowParent(this, 0.9f));
        tasks.addTask(8, new EntityAIWander(this, 0.8f, 120));
        tasks.addTask(9, new EntityAIMoveTowardsRestriction(this, 1.0f));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, false));
        targetTasks.addTask(2,
                new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking),
                        EntityPlayer.class, 16.0F, 0, true));

    }
	
    @Override
	protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte)0));
    }
   
    /**
     * Updates the WatchableObject (Byte) created in entityInit(), setting it to 0x01 if par1 is true or 0x00 if it is
     * false.
     */
    public void setBesideClimbableBlock(boolean p_70839_1_)
    {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);

        if (p_70839_1_)
        {
            b0 = (byte)(b0 | 1);
        }
        else
        {
            b0 &= -2;
        }

        this.dataWatcher.updateObject(16, Byte.valueOf(b0));
    }
    
    /**
     * returns true if this entity is by a ladder, false otherwise
     */
    @Override
    public boolean isOnLadder()
    {
        return this.isBesideClimbableBlock();
    }

    /**
     * Returns true if the WatchableObject (Byte) is 0x01 otherwise returns false. The WatchableObject is updated using
     * setBesideClimableBlock.
     */
    public boolean isBesideClimbableBlock()
    {
        return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }


    @Override
    protected boolean isValidLocation(World world, int xCoord, int yCoord, int zCoord) {
        return worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
    }
    
    @Override
    public void onLivingUpdate() {
    	super.onLivingUpdate();
    	if (Math.round(this.aggroRange) != 0) {
    		EAFF.updateEntityAFF(worldObj, Items.bread);
    	}
    	
    	/* If Shoot Timer is 0, tell Entity its Allowed to Shoot a Rock*/
        if (throwTimer == 0) {
            this.throwRock = true;
            this.throwTimer = this.throwCooldown;
            
        	//EntityPlayer targetedEntity = this.worldObj.getClosestVulnerablePlayerToEntity(this, 32.0D);
        	if (this.angerLevel > 0){
        		if (this.throwRock == true && !worldObj.isRemote) {
                    this.throwRockAtTarget();
                    this.throwRock = false;
                }
        	}
        }
        
        this.throwTimer = Math.max(this.throwTimer - 1, 0);
    }

    @Override
    public int getTotalArmorValue() {
        return 1;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound() {
        return DefaultProps.mobKey + ":" + DefaultProps.entitySounds + "monkeylivingsound";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound() {
        return DefaultProps.mobKey + ":" + DefaultProps.entitySounds + "monkeyhurtsound";
    }
    
    @Override
    public int getTalkInterval() {
        return 160;
    }

    @Override
    public boolean isValidBreedingItem(ItemStack itemStack) {
        if (itemStack != null && itemStack.getItem() == Items.bread) {
        	this.setAngerLevel(0);
        	this.setFleeTick(0);
            return true;
            
        } else {
            return super.isValidBreedingItem(itemStack);
        }
    }
    
    @Override
    protected void updateAITick() {
        super.updateAITick();
        this.setBesideClimbableBlock(this.isCollidedHorizontally);
    }
    
    private void throwRockAtTarget() {
        EntityPlayer targetedEntity = this.worldObj.getClosestVulnerablePlayerToEntity(this, 32.0D);
       
        if (targetedEntity != null) {
        	EntityThrowingRock entityTR = new EntityThrowingRock(this.worldObj, this);
            double d0 = targetedEntity.posX - this.posX;
            double d1 = targetedEntity.posY + (double)targetedEntity.getEyeHeight() - 1.100000023841858D - entityTR.posY;
            double d2 = targetedEntity.posZ - this.posZ;
            float f1 = MathHelper.sqrt_double(d0 * d0 + d2 * d2) * 0.2F;
            entityTR.setThrowableHeading(d0, d1 + (double)f1, d2, 1.6F, 12.0F);
            this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
            this.worldObj.spawnEntityInWorld(entityTR);
        }
    }

}
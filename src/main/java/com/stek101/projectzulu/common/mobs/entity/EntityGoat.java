package com.stek101.projectzulu.common.mobs.entity;

import java.util.EnumSet;
import java.util.Random;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.ai.EntityAILookIdle;
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
import com.stek101.projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIFollowParent;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIMate;
import com.stek101.projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIPanic;
import com.stek101.projectzulu.common.mobs.entityai.EntityAITempt;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIWander;

public class EntityGoat extends EntityGenericAnimal implements IAnimals {
	
	private EntityAFightorFlight EAFF;
	private CustomEntityList entityEntry;
	private float aggroLevel;
	private double aggroRange;
	private int timeUntilNextMilking;
	private int milkCounter;
	private boolean giveMilk;

   
	public EntityGoat(World par1World) {
        super(par1World);
        setSize(1.2f, 0.9f);
        
        milkCounter = 0;
        giveMilk = false;
        Random rand1 = new Random();
        this.textureID = rand1.nextInt(3);
        this.timeUntilNextMilking = this.rand.nextInt(6000) + 6000;
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
        tasks.addTask(3, new EntityAILookIdle(this));
        tasks.addTask(4, new EntityAIMate(this, 0.8f));
        tasks.addTask(5, new EntityAITempt(this, 0.9f, Items.apple, false));
        tasks.addTask(6, new EntityAIFollowParent(this, 0.9f));
        tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        tasks.addTask(8, new EntityAIWander(this, 0.8f, 120));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, false));
        targetTasks.addTask(2,
                new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking),
                        EntityPlayer.class, 16.0F, 0, true));

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
    	
    	/* Basic timer to determine whether entity can be milked or not */
    	if (!this.worldObj.isRemote && !this.isChild() &&  --this.timeUntilNextMilking <= 0)
    	{
    		giveMilk = true;    		
    	}    	
    }
    
    @Override
    protected void updateAITasks() {
        super.updateAITasks();
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
        return DefaultProps.mobKey + ":" + DefaultProps.entitySounds + "goatlivingsound";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound() {
        return DefaultProps.mobKey + ":" + DefaultProps.entitySounds + "goathurt";
    }
    
    @Override
    public int getTalkInterval() {
        return 160;
    }

    @Override
    public boolean isValidBreedingItem(ItemStack itemStack) {
        if (itemStack != null && itemStack.getItem() == Items.apple) {
        	this.setAngerLevel(0);
        	this.setFleeTick(0);
            return true;
            
        } else {
            return super.isValidBreedingItem(itemStack);
        }
    }

//    @Override
//    protected void dropRareDrop(int par1) {
//        if (Loader.isModLoaded(DefaultProps.BlocksModId) && BlockList.mobHeads.isPresent()) {
//            entityDropItem(new ItemStack(BlockList.mobHeads.get(), 1, 7), 1);
//        }
//        super.dropRareDrop(par1);
//    }
    
    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    
    public boolean interact(EntityPlayer p_70085_1_)
    {
        ItemStack itemstack = p_70085_1_.inventory.getCurrentItem();

        if (itemstack != null && itemstack.getItem() == Items.bucket && !p_70085_1_.capabilities.isCreativeMode && (giveMilk == true))
        {
            if (itemstack.stackSize-- == 1)
            {
                p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, new ItemStack(Items.milk_bucket));
            }
            else if (!p_70085_1_.inventory.addItemStackToInventory(new ItemStack(Items.milk_bucket)))
            {
                p_70085_1_.dropPlayerItemWithRandomChoice(new ItemStack(Items.milk_bucket, 1, 0), false);
            }            
            this.giveMilk = false;      
            this.timeUntilNextMilking = this.rand.nextInt(6000) + 6000;
            return true;
        }
        else
        {
            return super.interact(p_70085_1_);
        }
    }

}
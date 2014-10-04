package com.stek101.projectzulu.common.mobs.entity;

import java.util.Calendar;
import java.util.EnumSet;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.stek101.projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIFleeSun;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import com.stek101.projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIRestrictSun;
import com.stek101.projectzulu.common.mobs.entityai.EntityAIWander;

public class EntitySkeletonn extends EntityGenericAnimal implements IMob{
	private static ItemStack defaultHeldItem;
	private static boolean entityArmed = false;
				
	public EntitySkeletonn(World p_i1741_1_) {
		super(p_i1741_1_);

		Random rand1 = new Random();
        this.textureID = rand1.nextInt(3);
        
		this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIRestrictSun(this));
        this.tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0f, true));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0f, 120));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking),
                EntityPlayer.class, 16.0F, 0, true));    
        
        Random rand = new Random();         	
      	int dieRoll = rand.nextInt(4) + 1;
      	
        switch (dieRoll) {
        case 1: //defaultHeldItem = new ItemStack(Items.stone_axe);   
        	    this.setCurrentItemOrArmor(0, new ItemStack(Items.stone_axe));
        	    break;
        case 2:	 this.setCurrentItemOrArmor(0, new ItemStack(Items.stone_sword));
        	    //defaultHeldItem = new ItemStack(Items.stone_sword);	    		
	    		break;  
        case 3:	this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_axe));
        	    //defaultHeldItem = new ItemStack(Items.iron_axe);	    		
				break;  
        default: //defaultHeldItem = new ItemStack(Items.iron_sword);
        	    this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_sword));
        	     break;
         }
	}
	
    @Override
    protected void updateAITick() {
        this.angerLevel = 100;
        super.updateAITick();
    }
	
	 public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_)
	    {
	        p_110161_1_ = super.onSpawnWithEgg(p_110161_1_);     

	        this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * this.worldObj.func_147462_b(this.posX, this.posY, this.posZ));

	        if (this.getEquipmentInSlot(4) == null)
	        {
	            Calendar calendar = this.worldObj.getCurrentDate();

	            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F)
	            {
	                this.setCurrentItemOrArmor(4, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.lit_pumpkin : Blocks.pumpkin));
	                this.equipmentDropChances[4] = 0.0F;
	            }
	        }

	        return p_110161_1_;
	    }
	
    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(13, new Byte((byte) 0));
    }
    
	/**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "mob.skeleton.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.skeleton.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.skeleton.death";
    }
    
    @Override
    public int getTalkInterval() {
        return 160;
    }
    
    @Override
    protected void func_145780_a(int xCoord, int yCoord, int zCoord, Block stepBlock) {
    	this.playSound("mob.skeleton.step", 0.15F, 1.0F);
    }
    
    /**
     * Get this Entity's EnumCreatureAttribute
     */
    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }
    
    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote)
        {
            float f = this.getBrightness(1.0F);

            if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)))
            {
                boolean flag = true;
                ItemStack itemstack = this.getEquipmentInSlot(4);

                if (itemstack != null)
                {
                    if (itemstack.isItemStackDamageable())
                    {
                        itemstack.setItemDamage(itemstack.getItemDamageForDisplay() + this.rand.nextInt(2));

                        if (itemstack.getItemDamageForDisplay() >= itemstack.getMaxDamage())
                        {
                            this.renderBrokenItemStack(itemstack);
                            this.setCurrentItemOrArmor(4, (ItemStack)null);
                        }
                    }

                    flag = false;
                }

                if (flag)
                {
                    this.setFire(8);
                }
            }
        }

        if (this.worldObj.isRemote && this.getSkeletonType() == 1)
        {
            this.setSize(0.72F, 2.34F);
        }

 
    }
    
    /**
     * Return this skeleton's type.
     */
    public int getSkeletonType()
    {
        return this.dataWatcher.getWatchableObjectByte(13);
    }

    /**
     * Set this skeleton's type.
     */
    public void setSkeletonType(int p_82201_1_)
    {
        this.dataWatcher.updateObject(13, Byte.valueOf((byte)p_82201_1_));
        this.isImmuneToFire = p_82201_1_ == 1;

        if (p_82201_1_ == 1)
        {
            this.setSize(0.72F, 2.34F);
        }
        else
        {
            this.setSize(0.6F, 1.8F);
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound p_70037_1_)
    {
        super.readEntityFromNBT(p_70037_1_);

        if (p_70037_1_.hasKey("SkeletonType", 99))
        {
            byte b0 = p_70037_1_.getByte("SkeletonType");
            this.setSkeletonType(b0);
        }

    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound p_70014_1_)
    {
        super.writeEntityToNBT(p_70014_1_);
        p_70014_1_.setByte("SkeletonType", (byte)this.getSkeletonType());
    }
    
    /**
     * Sets the held item, or an armor slot. Slot 0 is held item. Slot 1-4 is armor. Params: Item, slot
     */
 //   @Override
 //   public ItemStack getHeldItem() {        
     
 //         return defaultHeldItem; 
//        }
  

    /**
     * Returns the Y Offset of this entity.
     */
    public double getYOffset()
    {
        return super.getYOffset() - 0.5D;
    }
    
    /**
     * Sets the held item, or an armor slot. Slot 0 is held item. Slot 1-4 is armor. Params: Item, slot
     */
    public void setCurrentItemOrArmor(int p_70062_1_, ItemStack p_70062_2_)
    {    	
        super.setCurrentItemOrArmor(p_70062_1_, p_70062_2_);       
    }

    
    /**
     * Makes entity wear random armor based on difficulty
     */
    @Override
    protected void addRandomArmor()
    {
        super.addRandomArmor();        
        this.setCurrentItemOrArmor(0, new ItemStack(Items.stone_sword));
    }


}

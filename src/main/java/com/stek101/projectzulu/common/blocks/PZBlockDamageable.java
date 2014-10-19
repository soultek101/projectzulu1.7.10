package com.stek101.projectzulu.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class PZBlockDamageable extends Block{
    protected int blockMaxDmg;
    protected int blockCurDmg;
    
	protected PZBlockDamageable(Material p_i45394_1_) {
		super(p_i45394_1_);
		// TODO Auto-generated constructor stub
	}
	
	public void setDmgBlock(Block block, int dmgValue){
		
	}
	
	/**
     * Returns the maximum damage an item can take.
     */
    public int getMaxDamage()
    {
        return this.blockMaxDmg;
    }
    
    /**
     * set max damage of an Item
     */
    public PZBlockDamageable setMaxDamage(int p_77656_1_)
    {
        this.blockMaxDmg = p_77656_1_;
        return this;
    }
    
    public int getDamage(PZBlockDamageable block)
    {
        //return block.itemDamage;
    	return 0;
    }
    
    /**
     * Write the stack fields to a NBT object. Return the new NBT object.
     
    public NBTTagCompound writeToNBT(NBTTagCompound p_77955_1_)
    {
        p_77955_1_.setShort("id", (short)Item.getIdFromItem(this.field_151002_e));
        p_77955_1_.setByte("Count", (byte)this.stackSize);
        p_77955_1_.setShort("Damage", (short)this.itemDamage);

        if (this.stackTagCompound != null)
        {
            p_77955_1_.setTag("tag", this.stackTagCompound);
        }

        return p_77955_1_;
    }*/

    /**
     * Read the stack fields from a NBT object.
     
    public void readFromNBT(NBTTagCompound p_77963_1_)
    {
        func_150996_a(Item.getItemById(p_77963_1_.getShort("id")));
        this.stackSize = p_77963_1_.getByte("Count");
        this.itemDamage = p_77963_1_.getShort("Damage");

        if (this.itemDamage < 0)
        {
            this.itemDamage = 0;
        }

        if (p_77963_1_.hasKey("tag", 10))
        {
            this.stackTagCompound = p_77963_1_.getCompoundTag("tag");
        }
    }*/

}

package com.stek101.projectzulu.common.mobs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.stek101.projectzulu.common.mobs.entity.EntityCamel;

public class ContainerCamelInventory extends Container {

	 private IInventory field_111243_a;
	 private final IInventory camelInv;
	 private EntityCamel theEntity;

	    //public ContainerCamelInventory(IInventory p_i1817_1_, final IInventory p_i1817_2_, final EntityCamel p_i1817_3_)
	 public ContainerCamelInventory(InventoryPlayer p_i1817_1_, int entityID, World world)
	    {
            final IInventory p_i1817_2_;
            final EntityCamel p_i1817_3_;

	        this.theEntity = (EntityCamel) world.getEntityByID(entityID);
	        p_i1817_3_ = theEntity;
	        camelInv = theEntity.camelChest;
	        p_i1817_2_ = this.camelInv;
	        this.field_111243_a = p_i1817_2_;
		    
	        byte var4 = 3;
	        p_i1817_2_.openInventory();
	        int var5 = (var4 - 4) * 18;
            
	        this.addSlotToContainer(new Slot(p_i1817_2_, 0, 8, 18)
	        {
	            public boolean isItemValid(ItemStack p_75214_1_)
	            {
	                return super.isItemValid(p_75214_1_) && p_75214_1_.getItem() == Items.saddle && !this.getHasStack();
	            }
	        });
	        
	        
	    /*    this.addSlotToContainer(new Slot(p_i1817_2_, 1, 8, 36)
	        {
	            
	            public boolean isItemValid(ItemStack p_75214_1_)
	            {
	                return super.isItemValid(p_75214_1_) && EntityCamel.func_146085_a(p_75214_1_.getItem());
	            }
	            
	            public boolean func_111238_b()
	            {
	                return false;
	            }
	        });*/
	        
	        int var6;
	        int var7;
	        if (p_i1817_3_.isChested())
	        {
	            for (var6 = 0; var6 < var4; ++var6)
	            {
	                for (var7 = 0; var7 < 5; ++var7)
	                {
	                    this.addSlotToContainer(new Slot(p_i1817_2_, 2 + var7 + var6 * 5, 80 + var7 * 18, 18 + var6 * 18));
	                }
	            }
	        }

	        for (var6 = 0; var6 < 3; ++var6)
	        {
	            for (var7 = 0; var7 < 9; ++var7)
	            {
	                this.addSlotToContainer(new Slot(p_i1817_1_, var7 + var6 * 9 + 9, 8 + var7 * 18, 102 + var6 * 18 + var5));
	            }
	        }

	        for (var6 = 0; var6 < 9; ++var6)
	        {
	            this.addSlotToContainer(new Slot(p_i1817_1_, var6, 8 + var6 * 18, 160 + var5));
	        }
	        
	        //bindPlayerInventory(p_i1817_1_);
	    }
	 
		protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 9; j++) {
					addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
				}
			}
			for (int i = 0; i < 9; i++) {
				addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
			}
		}

	    @Override
	    public boolean canInteractWith(EntityPlayer p_75145_1_)
	    {
	        return this.field_111243_a.isUseableByPlayer(p_75145_1_) && this.theEntity.isEntityAlive() && this.theEntity.getDistanceToEntity(p_75145_1_) < 8.0F;
	    }

	    /**
	     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
	     */
	   @Override
	    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
	    {
	        ItemStack var3 = null;
	        Slot var4 = (Slot)this.inventorySlots.get(p_82846_2_);
	        if (var4 != null && var4.getHasStack())
	        {
	            ItemStack var5 = var4.getStack();
	            var3 = var5.copy();

	            if (p_82846_2_ < this.field_111243_a.getSizeInventory())
	            {
	                if (!this.mergeItemStack(var5, this.field_111243_a.getSizeInventory(), this.inventorySlots.size(), true))
	                {
	                    return null;
	                }
	            }
	            else if (this.getSlot(1).isItemValid(var5) && !this.getSlot(1).getHasStack())
	            {
	                if (!this.mergeItemStack(var5, 1, 2, false))
	                {
	                	return null;
	                }
	            }
	            else if (this.getSlot(0).isItemValid(var5))
	            {
	                if (!this.mergeItemStack(var5, 0, 1, false))
	                {
	                    return null;
	                }
	            }
	            else if (this.field_111243_a.getSizeInventory() <= 2 || !this.mergeItemStack(var5, 2, this.field_111243_a.getSizeInventory(), false))
	            {
	                return null;
	            }

	            if (var5.stackSize == 0)
	            {
	                var4.putStack((ItemStack)null);
	            }
	            else
	            {
	                var4.onSlotChanged();
	            }
	        }

	        return var3;
	    }
	

	    /**
	     * Called when the container is closed.
	     */
	    @Override
	    public void onContainerClosed(EntityPlayer p_75134_1_)
	    {
	        super.onContainerClosed(p_75134_1_);
	        this.field_111243_a.closeInventory();
	    }    
	    
}

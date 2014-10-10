package com.stek101.projectzulu.common.blocks.tombstone;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.stek101.projectzulu.common.ProjectZulu_Core;

public class BlockTombstone extends BlockContainer {

    private Class signEntityClass;
    private final Random field_149955_b = new Random();

    public BlockTombstone(Class par2Class) {
        super(Material.rock);
        this.setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        signEntityClass = par2Class;

        float var4 = 0.4F;
        float var5 = 1.0F;
        setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var5, 0.5F + var4);
        setHardness(0.5F);
        setStepSound(Block.soundTypeMetal);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return null;
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType() {
        return -1;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        return true;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube? This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
    public TileEntity createNewTileEntity(World par1World, int metadata) {
        try {        	
            return (TileEntity) this.signEntityClass.newInstance();
        } catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }

    @Override
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase livingBase,
            ItemStack par6ItemStack) {
        int var6 = (Math.round(-livingBase.rotationYaw / 45f) + 4) & 7;

        par1World.setBlockMetadataWithNotify(par2, par3, par4, var6, 3);

        if (livingBase instanceof EntityPlayer) {
            ((EntityPlayer) livingBase).openGui(ProjectZulu_Core.modInstance, 0, par1World, par2, par3, par4);
        }      
        
        super.onBlockPlacedBy(par1World, par2, par3, par4, livingBase, par6ItemStack);
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
            int par6, float par7, float par8, float par9) {

        if (par5EntityPlayer instanceof EntityPlayer) {
            if (par5EntityPlayer.inventory.getCurrentItem() == null) {
                TileEntity tileEntity = par1World.getTileEntity(par2, par3, par4);

                if (tileEntity != null && tileEntity instanceof TileEntityTombstone) {

                    ((TileEntityTombstone) tileEntity).giveItemsToPlayer(par5EntityPlayer);
                }
                return true;
            } else if (isValidItemForEditing(par5EntityPlayer.inventory.getCurrentItem().getItem())) {
                par5EntityPlayer.openGui(ProjectZulu_Core.modInstance, 0, par1World, par2, par3, par4);
                return true;
            }
        }
        return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
    }

    private boolean isValidItemForEditing(Item itemID) {
        if (itemID == Items.wooden_pickaxe || itemID == Items.stone_pickaxe || itemID == Items.golden_pickaxe
                || itemID == Items.iron_pickaxe || itemID == Items.diamond_pickaxe) {
            return true;
        }
        return false;
    }
    
    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
    	TileEntityTombstone var7 = (TileEntityTombstone) par1World.getTileEntity(par2, par3, par4);
    	
    	 if (var7 != null)
         {
             for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8)
             {
                 ItemStack var9 = var7.getStackInSlot(var8);

                 if (var9 != null)
                 {
                     float var10 = this.field_149955_b.nextFloat() * 0.8F + 0.1F;
                     float var11 = this.field_149955_b.nextFloat() * 0.8F + 0.1F;
                     EntityItem var14;

                     for (float var12 = this.field_149955_b.nextFloat() * 0.8F + 0.1F; var9.stackSize > 0; par1World.spawnEntityInWorld(var14))
                     {
                         int var13 = this.field_149955_b.nextInt(21) + 10;

                         if (var13 > var9.stackSize)
                         {
                             var13 = var9.stackSize;
                         }

                         var9.stackSize -= var13;
                         var14 = new EntityItem(par1World, (double)((float)par2 + var10), (double)((float)par3 + var11), (double)((float)par4 + var12), new ItemStack(var9.getItem(), var13, var9.getItemDamage()));
                         float var15 = 0.05F;
                         var14.motionX = (double)((float)this.field_149955_b.nextGaussian() * var15);
                         var14.motionY = (double)((float)this.field_149955_b.nextGaussian() * var15 + 0.2F);
                         var14.motionZ = (double)((float)this.field_149955_b.nextGaussian() * var15);

                        // if (var9.hasTagCompound())
                        // {
                       //      var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                       //  }                        
                     }
                 }
             }

             par1World.func_147453_f(par2, par3, par4, par5);
         }

         super.breakBlock(par1World, par2, par3, par4, par5, par6);
    	
   //     if (!par1World.isRemote) {
    //        if ((par6 & 8) == 0) {
  //              this.dropBlockAsItem(par1World, par2, par3, par4,
  //                      new ItemStack(this, 1, this.getDamageValue(par1World, par2, par3, par4)));
  //          }
  //          super.breakBlock(par1World, par2, par3, par4, par5, par6);
  //      }
    }	
}

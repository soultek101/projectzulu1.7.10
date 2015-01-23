package com.stek101.projectzulu.common.blocks.spike;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.stek101.projectzulu.common.ProjectZulu_Core;
import com.stek101.projectzulu.common.api.ItemList;
import com.stek101.projectzulu.common.core.ItemGenerics;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSpikes extends BlockContainer {

    public static final String[] imageSuffixB = new String[] { "", "_poison", "_sticky" };
    Material blockMaterial;
    int defaultIvory;
    private boolean isSpikeInvulnerable;
    
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;
    public final int renderID;

    
    public BlockSpikes(int renderID, Material mat, int defaultIvory, boolean spikesInvulnerable) {
        super(mat);
        setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        disableStats();        
        setBlockBounds(0f, 0.0F, 0.0f, 1.0f, 0.5f, 1.0f);      
        this.blockMaterial = mat;
        this.defaultIvory = defaultIvory;
        if (mat == Material.wood) {
        	setHardness(1.0F);
        	setResistance(2.0F);
        	setStepSound(Block.soundTypeWood);
        }
        else if (mat == Material.rock){
        	setHardness(1.5F);
        	setStepSound(Block.soundTypeStone);
        	setResistance(4.0F);
        }
        else if (mat == Material.iron){
        	setHardness(2.5F);
        	setResistance(6.0F);
        	setStepSound(Block.soundTypeMetal);
        }
        else {
        	setHardness(1.0F);
        	setResistance(2.0F);
        	setStepSound(Block.soundTypeGlass);
        }
        this.renderID = renderID;
        this.isSpikeInvulnerable = spikesInvulnerable;
    }
    
    public BlockSpikes(int RenderID, boolean spikesInvulnerable){    	
    	this(RenderID, Material.rock, 1, spikesInvulnerable);    	
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2) {
        if (par2 <= 5) {
            return icons[0];
        } else if (par2 > 5 && par2 <= 11) {
            return icons[1];
        } else {
            return icons[2];
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
    	this.icons = new IIcon[imageSuffixB.length];
	        for (int i = 0; i < this.icons.length; ++i) {
	            this.icons[i] = par1IconRegister.registerIcon(getTextureName() + imageSuffixB[i]);
	        }       
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess par1iBlockAccess, int par2, int par3, int par4) {
        
    	if (par1iBlockAccess.getBlock(par2, par3 - 1, par4) == Blocks.fence
                || par1iBlockAccess.getBlock(par2, par3 - 1, par4) == Blocks.nether_brick_fence) {
            this.setBlockBounds(0.375f, 0.0F, 0.375f, 0.625f, 0.4f, 0.625f);

        } else if (par1iBlockAccess.getBlock(par2, par3 - 1, par4) == Blocks.iron_bars) {

            boolean isNegZ = canThisPaneConnectToThisBlockID(par1iBlockAccess.getBlock(par2, par3 - 1, par4 - 1));
            boolean isPosZ = canThisPaneConnectToThisBlockID(par1iBlockAccess.getBlock(par2, par3 - 1, par4 + 1));
            boolean isNegX = canThisPaneConnectToThisBlockID(par1iBlockAccess.getBlock(par2 - 1, par3 - 1, par4));
            boolean isPosX = canThisPaneConnectToThisBlockID(par1iBlockAccess.getBlock(par2 + 1, par3 - 1, par4));

            float minX = !isNegX ? 0.4f : 0.0f;
            float maxX = !isPosX ? 0.6f : 1.0f;
            float minZ = !isNegZ ? 0.4f : 0.0f;
            float maxZ = !isPosZ ? 0.6f : 1.0f;

            if (!isNegZ && !isPosZ && !isNegX && !isPosX) {
                this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.4f, 1.0f);
            } else {
                this.setBlockBounds(minX, 0.0f, minZ, maxX, 0.4f, maxZ);
            }

        } else {
            switch (par1iBlockAccess.getBlockMetadata(par2, par3, par4)) {
            case 0:
                this.setBlockBounds(0f, 0.0F, 0.0f, 1.0f, 0.5f, 1.0f);
                break;
            case 1:
                this.setBlockBounds(0.0f, 0.0F, 0.0f, 0.5f, 1.0f, 1.0f);
                break;
            case 2:
                this.setBlockBounds(0.0f, 0.0F, 0.0f, 1.0f, 1.0f, 0.5f);
                break;
            case 3:
                this.setBlockBounds(0.5f, 0.0F, 0.0f, 1.0f, 1.0f, 1.0f);
                break;
            case 4:
                this.setBlockBounds(0f, 0.0F, 0.5f, 1.0f, 1.0f, 1.0f);
                break;
            case 5:
                this.setBlockBounds(0f, 0.5F, 0.0f, 1.0f, 1.0f, 1.0f);
                break;

            case 6:
                this.setBlockBounds(0f, 0.0F, 0.0f, 1.0f, 0.5f, 1.0f);
                break;
            case 7:
                this.setBlockBounds(0.0f, 0.0F, 0.0f, 0.5f, 1.0f, 1.0f);
                break;
            case 8:
                this.setBlockBounds(0.0f, 0.0F, 0.0f, 1.0f, 1.0f, 1.0f);
                break;
            case 9:
                this.setBlockBounds(0.5f, 0.0F, 0.0f, 1.0f, 1.0f, 1.0f);
                break;
            case 10:
                this.setBlockBounds(0f, 0.0F, 0.5f, 1.0f, 1.0f, 1.0f);
                break;
            case 11:
                this.setBlockBounds(0f, 0.5F, 0.0f, 1.0f, 1.0f, 1.0f);
                break;

            default:
                break;
            }
        }
    }

    @Override
    public int getRenderType() {
        return renderID;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return null;
    }

    @Override
    public int quantityDropped(Random random) {
        return 1;
    }

    /**
     * checks to see if you can place this block can be placed on that side of a block: BlockLever overrides
     */
    @Override
    public boolean canPlaceBlockOnSide(World par1World, int par2, int par3, int par4, int par5) {
        return this.canPlaceBlockAt(par1World, par2, par3, par4);
    }

    @Override
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
    	//EffectRenderer efrenderer;
    	
    	//if (!par1World.isRemote){
    	 
    	//	EffectRenderer efrenderer = Minecraft.getMinecraft().effectRenderer;
    	//}
    	
    	if ((par5Entity instanceof EntityLiving || par5Entity instanceof EntityPlayer)        		
                && ((par5Entity.prevPosY > par3 + this.maxY * 0.9f && par5Entity.posY < par3 + this.maxY * 0.9f)
                        || (par5Entity.prevPosX > par2 + 0.5 && par5Entity.posX < par2 + 0.5) || (par5Entity.prevPosZ > par4 + 0.5 && par5Entity.posZ < par4 + 0.5))) {
            
        	/* Check if Spikes are Poison, If So Also Apply Poison with Damage */
            if (par1World.getBlockMetadata(par2, par3, par4) > 5 && par1World.getBlockMetadata(par2, par3, par4) < 12) {
             if (par5Entity instanceof EntityLiving)
                ((EntityLiving) par5Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 40, 1));
             
             if (par5Entity instanceof EntityPlayer && !par5Entity.isSneaking())
            	 ((EntityPlayer) par5Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 40, 1));
            }
            
            if (par1World.getBlockMetadata(par2, par3, par4) > 11) {
               if (par5Entity instanceof EntityLiving)
                ((EntityLiving) par5Entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 40, 4));
            
               if (par5Entity instanceof EntityPlayer && !par5Entity.isSneaking())
            	   ((EntityPlayer) par5Entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 40, 4));
            }
            
           if (!par5Entity.isSneaking()) {
        	   
        	   if (par5Entity.isSprinting()) {
        		   par5Entity.attackEntityFrom(DamageSource.generic, 6); 
        	   }
        	   else
        	   {
        		   par5Entity.attackEntityFrom(DamageSource.generic, 2);
        	   }	   

           if (par1World.isRemote) {
        	    Random rand = new Random();
            	par1World.spawnParticle("crit", par5Entity.posX, par5Entity.posY, par5Entity.posZ, 0.0D, 0.0D, 0.0D);  
            	for (int i = 0; i < 6; i++) {            		
            		par1World.spawnParticle("crit", par2 + rand.nextDouble(), par3 + 1 + rand.nextDouble()*0.2, par4 + rand.nextDouble(), 0, 0, 0);
            		}
                }
       	 	
            TileEntity tileEntity = par1World.getTileEntity(par2, par3, par4);
                       
            if (!this.isSpikeInvulnerable){ 
	         if (tileEntity != null && tileEntity instanceof TileEntitySpikes) {
	             ((TileEntitySpikes) tileEntity).setDamage(1);
	            		 
	              if (((TileEntitySpikes) tileEntity).getCurrDamage() > ((TileEntitySpikes) tileEntity).getMaxDmg()) {
	            	  
	            	  if (par1World.isRemote) {
	              	    Random rand = new Random();
	                  	par1World.spawnParticle("crit", par5Entity.posX, par5Entity.posY, par5Entity.posZ, 0.0D, 0.0D, 0.0D);  
	                  	for (int i = 0; i < 6; i++) {            		
	                  		par1World.spawnParticle("crit", par2 + rand.nextDouble(), par3 + 1 + rand.nextDouble()*0.2, par4 + rand.nextDouble(), 0, 0, 0);
	                  		}
	                      }
	            	  
	            	par1World.playSoundAtEntity(par5Entity, "random.break", 1.0F, 1.0F);
	            	par1World.removeTileEntity(par2, par3, par4);
	            	par1World.setBlockToAir(par2, par3, par4);
	            }
	         }
           }
    	  }  
        }     
        super.onEntityCollidedWithBlock(par1World, par2, par3, par4, par5Entity);
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
            int par6, float par7, float par8, float par9) {
    	int maxSpikeDmg;
    	int currSpikeDmg;
    	
    	TileEntity tileEntityO = par1World.getTileEntity(par2, par3, par4);
    	maxSpikeDmg = ((TileEntitySpikes) tileEntityO).getMaxDmg();
    	currSpikeDmg  = ((TileEntitySpikes) tileEntityO).getCurrDamage();

        /* Check if Item is Poison Droplet, is so Make Poisonous */
        if (par5EntityPlayer.inventory.getCurrentItem() != null
                && ItemList.genericCraftingItems.isPresent()
                && par5EntityPlayer.inventory.getCurrentItem().getItem() == ItemList.genericCraftingItems.get()
                && par5EntityPlayer.inventory.getCurrentItem().getItemDamage() == ItemGenerics.Properties.PoisonDroplet
                        .meta()
                && (par1World.getBlockMetadata(par2, par3, par4) < 6 || par1World.getBlockMetadata(par2, par3, par4) > 11)) {

            /* Consume if Not Creative */
            if (!par5EntityPlayer.capabilities.isCreativeMode) {
                par5EntityPlayer.inventory.getCurrentItem().stackSize = par5EntityPlayer.inventory.getCurrentItem().stackSize - 1;
            }

            /* If not Poison (meta<6) increase by 6, if sticky(>11) reduce by 6 */
            if (par1World.getBlockMetadata(par2, par3, par4) < 6) {
            	par1World.removeTileEntity(par2, par3, par4);
                par1World.setBlock(par2, par3, par4, this, par1World.getBlockMetadata(par2, par3, par4) + 6, 3);
            } else {
            	par1World.removeTileEntity(par2, par3, par4);
                par1World.setBlock(par2, par3, par4, this, par1World.getBlockMetadata(par2, par3, par4) - 6, 3);
            }
        }

        /* Check if Item is Sticky Droplet, is so Make Sticky */
        if (par5EntityPlayer.inventory.getCurrentItem() != null
                && par5EntityPlayer.inventory.getCurrentItem().getItem() == Items.slime_ball
                && (par1World.getBlockMetadata(par2, par3, par4) < 12)) {

            /* Consume if Not Creative */
            if (!par5EntityPlayer.capabilities.isCreativeMode) {
                par5EntityPlayer.inventory.getCurrentItem().stackSize = par5EntityPlayer.inventory.getCurrentItem().stackSize - 1;
            }

            /* If not Poison or Sticky (meta<6) increase by 12, if Poison(>6 & <11) increase by 6 */
            if (par1World.getBlockMetadata(par2, par3, par4) < 6) {
            	par1World.removeTileEntity(par2, par3, par4);
                par1World.setBlock(par2, par3, par4, this, par1World.getBlockMetadata(par2, par3, par4) + 12, 3);
            } else {
            	par1World.removeTileEntity(par2, par3, par4);
                par1World.setBlock(par2, par3, par4, this, par1World.getBlockMetadata(par2, par3, par4) + 6, 3);
            }
        }
        
        TileEntity tileEntityC = par1World.getTileEntity(par2, par3, par4);
        ((TileEntitySpikes) tileEntityC).setMaxDmg(maxSpikeDmg);
        ((TileEntitySpikes) tileEntityC).setCurrDamage(currSpikeDmg);
        
        return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
    }

    /**
     * Copied from BlockPane (Iron Fence). Gets passed in the blockID of the block adjacent and supposed to return true
     * if its allowed to connect to the type of blockID passed in. Args: blockID
     */
    private final boolean canThisPaneConnectToThisBlockID(Block block) {
        return block.isOpaqueCube() || block == Blocks.iron_bars || block == Blocks.glass;
    }

    /**
     * Used to Set Metadata as the block is placed called before onBlockPlacedBy by ItemBlock and ItemReed was called
     * void updateBlockMetadata was called int func_85104 ~1.4.5 - 1.4.6 was called int onBlockPlaced ~1.4.7
     */
    @Override
    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7,
            float par8, int par9) {
        int var9 = par1World.getBlockMetadata(par2, par3, par4);
        if (par5 == 1 && this.canPlaceSpikeOn(par1World, par2, par3 - 1, par4)) {
            var9 = 0;
        }

        if (par5 == 2 && this.canPlaceSpikeOn(par1World, par2, par3, par4 + 1)) {
            var9 = 4;
        }

        if (par5 == 3 && this.canPlaceSpikeOn(par1World, par2, par3, par4 - 1)) {
            var9 = 2;
        }

        if (par5 == 4 && this.canPlaceSpikeOn(par1World, par2 + 1, par3, par4)) {
            var9 = 3;
        }

        if (par5 == 5 && this.canPlaceSpikeOn(par1World, par2 - 1, par3, par4)) {
            var9 = 1;
        }
        if (par5 == 0 && this.canPlaceSpikeOn(par1World, par2, par3 + 1, par4)) {
            var9 = 5;
        }

        return var9;
    }

    /**
     * Determines if a torch can be placed on the top surface of this block. Useful for creating your own block that
     * torches can be on, such as fences.
     * 
     * @param world The current world
     * @param x X Position
     * @param y Y Position
     * @param z Z Position
     * @return True to allow the torch to be placed
     */
    public boolean canPlaceSpikeOn(World world, int x, int y, int z) {
        if (World.doesBlockHaveSolidTopSurface(world, x, y, z)) {
            return true;
        } else {
            Block id = world.getBlock(x, y, z);
            return id == Blocks.glass || id == Blocks.piston || id == Blocks.piston_extension
                    || id == Blocks.piston_head || id == Blocks.sticky_piston;
        }
    }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
	   int maxSpikeDmgCap = 50;
	   
	   if (this.blockMaterial == Material.wood)
	   {
		   maxSpikeDmgCap = 50;
       } 
	   else if (this.blockMaterial == Material.rock)
	   {
    	  if (this.defaultIvory == 0){
    		  maxSpikeDmgCap = 50;
    	  } else {
    		  maxSpikeDmgCap = 90;
    	  }
        } 
	   else if (this.blockMaterial == Material.iron)
	    {
		   maxSpikeDmgCap = 175;
        }

		return new TileEntitySpikes(maxSpikeDmgCap);
	}
	
	/**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
      
      super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }
  
}

package com.stek101.projectzulu.common.blocks;

import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.base.Optional;
import com.google.common.collect.Sets;
import com.stek101.projectzulu.common.ProjectZulu_Core;
import com.stek101.projectzulu.common.api.BlockList;
import com.stek101.projectzulu.common.api.ItemList;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.core.OptionalItemStack;


import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class ItemHammerTool extends ItemTool {
	public float weaponDamage;
	private final Item.ToolMaterial material;
	private static final Set field_150915_c = Sets.newHashSet(new Block[] {Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, 
			Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, 
			Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, 
			Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail, 
			Blocks.golden_rail, Blocks.activator_rail, Blocks.crafting_table, Blocks.furnace, Blocks.chest, Blocks.planks, Blocks.bookshelf, Blocks.log, 
			Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin});
	//private static final Set field_150915_c = Sets.newHashSet(new Block[] {});

    public ItemHammerTool(String name, ToolMaterial mat) {
    	super(1.0F, mat, field_150915_c);
        this.material = mat;
        this.maxStackSize = 1;
        this.setMaxDamage(mat.getMaxUses());
        this.setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        bFull3D = true;
        setUnlocalizedName(name.toLowerCase());
        setTextureName(DefaultProps.blockKey + ":" + name.toLowerCase());
        this.weaponDamage = 1.0f + mat.getDamageVsEntity();
        
    }
 
    @Override
    public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_)
    {   
        return p_150893_2_.getMaterial() != Material.wood && p_150893_2_.getMaterial() != Material.plants && p_150893_2_.getMaterial() != Material.vine && 
        		p_150893_2_.getMaterial() != Material.iron && p_150893_2_.getMaterial() != Material.anvil && 
        		p_150893_2_.getMaterial() != Material.rock ? super.func_150893_a(p_150893_1_, p_150893_2_) : this.efficiencyOnProperMaterial;
    
        /*if (p_150893_2_ == Blocks.web)
        {
            return 15.0F;
        }
        else
        {
            Material var3 = p_150893_2_.getMaterial();
            return var3 != Material.plants && var3 != Material.vine && var3 != Material.coral && var3 != Material.leaves && var3 != Material.plants ? 1.0F : 1.5F;
        }*/
    }
    
   /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    @Override
    public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_)
    {
        p_77644_1_.damageItem(2, p_77644_3_);
        //p_77644_2_.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 80, 99));
        return true;
    }
    
 @Override
  
   public boolean onBlockDestroyed(ItemStack itemstack, World p_150894_2_, Block block, int x, int y, int z, EntityLivingBase entityLivingBase)
    { 
        if ((double)block.getBlockHardness(p_150894_2_, x, y, z) != 0.0D)
        {
        	EntityPlayer entityplayer = ProjectZulu_Core.proxy.getClientPlayer(); 
        	itemstack.damageItem(1, entityLivingBase); 
        	
        	World world = entityLivingBase.worldObj;
        	
        	boolean clearBlock = false;
        	Side side = FMLCommonHandler.instance().getEffectiveSide();
        	
        	if (side == Side.SERVER && !entityplayer.capabilities.isCreativeMode) {
    	    	//entityplayer.addStat(StatList.mineBlockStatArray[world.getBlock(x, y, z).getIdFromBlock(world.getBlock(x, y, z))], 1);
    	    	
    	    	entityplayer.addExhaustion(0.025F);	    	
    	    	world.playAuxSFX(2001, x, y, z, world.getBlock(x, y, z).getIdFromBlock(world.getBlock(x, y, z)) + (world.getBlockMetadata(x, y, z) << 12));
    	    	
    	    	if (itemstack.getItemDamage() < itemstack.getMaxDamage()) {
    	    		Random randomGen = new Random();
    	    		ItemStack itemstack1 = null;
    	    		ItemStack itemstack2 = null;
    	    		ItemStack itemstack3 = null;
    	    		//System.out.println("material name for hammer is" + material.name());
    	        	//System.out.println("block hit is " + world.getBlock(x, y, z));
    	        	
    	           	if (world.getBlock(x, y, z).equals(Blocks.cobblestone)) {
    	    	    	itemstack1 = new ItemStack(Blocks.gravel, 1);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.crafting_table)) {
    	        		itemstack1 = new ItemStack(Blocks.planks, randomGen.nextInt(3) + 2);
    	        		//clearBlock = true;
    	    	   	}       
    	        	else if (world.getBlock(x, y, z).equals(Blocks.furnace)) {
    	        		itemstack1 = new ItemStack(Blocks.cobblestone, randomGen.nextInt(5) + 4);
    	    	   	}  
    	        	else if (world.getBlock(x, y, z).equals(Blocks.chest)) {
    	        		itemstack1 = new ItemStack(Blocks.planks, randomGen.nextInt(5) + 4);
    	        		//clearBlock = true;
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.ladder)) {
    	        		itemstack1 = new ItemStack(Items.stick, randomGen.nextInt(2) + 1);
    	        		//clearBlock= true;
    	    	   	}  
    	        	else if (world.getBlock(x, y, z).equals(Blocks.stone_stairs)) {
    	        		itemstack1 = new ItemStack(Blocks.cobblestone, randomGen.nextInt(3) + 1);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.oak_stairs)) {
    	        		itemstack1 = new ItemStack(Blocks.planks, randomGen.nextInt(2) + 1, 0);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.acacia_stairs)) {
    	        		itemstack1 = new ItemStack(Blocks.planks, randomGen.nextInt(2) + 1, 4);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.birch_stairs)) {
    	        		itemstack1 = new ItemStack(Blocks.planks, randomGen.nextInt(2) + 1, 2);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.spruce_stairs)) {
    	        		itemstack1 = new ItemStack(Blocks.planks, randomGen.nextInt(2) + 1, 1);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.jungle_stairs)) {
    	        		itemstack1 = new ItemStack(Blocks.planks, randomGen.nextInt(2) + 1, 3);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.dark_oak_stairs)) {
    	        		itemstack1 = new ItemStack(Blocks.planks, randomGen.nextInt(2) + 1, 5);
    	    	   	} 
    	        	else if (world.getBlock(x, y, z).equals(Blocks.wooden_slab)) {
    	        		itemstack1 = new ItemStack(Blocks.planks, 1, OreDictionary.WILDCARD_VALUE);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.stone_slab)) {
    	        		itemstack1 = new ItemStack(Blocks.cobblestone, 1);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.sandstone_stairs)) {
    	        		itemstack1 = new ItemStack(Blocks.sandstone, randomGen.nextInt(2) + 1);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.fence)) {
    	        		itemstack1 = new ItemStack(Items.stick, randomGen.nextInt(3) + 1);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.fence_gate)) {
    	        		itemstack1 = new ItemStack(Items.stick, randomGen.nextInt(3) + 1);
    	        		itemstack2 = new ItemStack(Blocks.planks, randomGen.nextInt(2) + 1);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.anvil)) {
    	        		itemstack1 = new ItemStack(Blocks.iron_block, randomGen.nextInt(3) + 1);
    	        		itemstack2 = new ItemStack(Items.iron_ingot, randomGen.nextInt(4) + 1);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.bookshelf)) {
    	        		itemstack1 = new ItemStack(Items.book, randomGen.nextInt(3) + 1);
    	        		itemstack2 = new ItemStack(Blocks.planks, randomGen.nextInt(4) + 3);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.brick_stairs)) {
    	        		itemstack2 = new ItemStack(Blocks.brick_block, randomGen.nextInt(2) + 1);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.cauldron)) {
    	        		itemstack1 = new ItemStack(Items.iron_ingot, randomGen.nextInt(4) + 4);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.cobblestone_wall)) {
    	        		itemstack1 = new ItemStack(Blocks.cobblestone, 1);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.double_stone_slab)) {
    	        		itemstack1 = new ItemStack(Items.diamond, randomGen.nextInt(4) + 1);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.double_wooden_slab)) {
    	        		itemstack1 = new ItemStack(Blocks.planks, randomGen.nextInt(4) + 1, OreDictionary.WILDCARD_VALUE);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.enchanting_table)) {
    	        		itemstack1 = new ItemStack(Items.diamond, randomGen.nextInt(2) + 1);
    	        		itemstack2 = new ItemStack(Blocks.obsidian, randomGen.nextInt(3) + 2);
    	        		itemstack3 = new ItemStack(Items.book, 1);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.flower_pot)) {
    	        		itemstack1 = new ItemStack(Items.brick, randomGen.nextInt(3) + 1);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.iron_door)) {
    	        		itemstack1 = new ItemStack(Items.iron_ingot, randomGen.nextInt(4) + 3);
    	    	   	}	
    	        	else if (world.getBlock(x, y, z).equals(Blocks.jukebox)) {
    	        		itemstack1 = new ItemStack(Blocks.planks, randomGen.nextInt(5) + 4);
    	        		itemstack2 = new ItemStack(Items.diamond, 1);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.trapdoor)) {
    	        		itemstack1 = new ItemStack(Blocks.planks, randomGen.nextInt(4) + 3);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.wooden_pressure_plate)) {
    	        		itemstack1 = new ItemStack(Blocks.planks, randomGen.nextInt(2)+1);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.stone_pressure_plate)) {
    	        		itemstack1 = new ItemStack(Blocks.cobblestone, randomGen.nextInt(2)+1);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.rail)) {
    	        		itemstack1 = new ItemStack(Items.iron_ingot, randomGen.nextInt(4)+ 3);
    	        		itemstack2 = new ItemStack(Items.stick, 1);
    	    	   	}
    	        	else if ((world.getBlock(x, y, z).equals(Blocks.wall_sign)) || (world.getBlock(x, y, z).equals(Blocks.standing_sign)))  {
    	        		itemstack1 = new ItemStack(Blocks.planks, randomGen.nextInt(4)+ 3);
    	        		itemstack2 = new ItemStack(Items.stick, 1);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(Blocks.bed)) {
    	        		itemstack1 = new ItemStack(Blocks.planks, randomGen.nextInt(2)+ 2);
    	        		itemstack2 = new ItemStack(Blocks.wool, randomGen.nextInt(2)+ 2);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(BlockList.tombstone.get())) {
    	        		itemstack1 = new ItemStack(Blocks.cobblestone, randomGen.nextInt(5)+ 4);
    	        		itemstack2 = new ItemStack(Items.sign, 1);
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(BlockList.palmTreeDoubleSlab.get())) {
    	        		Block itemBlockx = BlockList.palmTreeSlab.get();
    	        		itemstack1 = new ItemStack(itemBlockx, 1);    	        		
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(BlockList.palmTreeSlab.get())) {
    	        		Block itemBlockx = BlockList.palmTreePlank.get();
    	        		itemstack1 = new ItemStack(itemBlockx, randomGen.nextInt(2)+ 1);    	        		
    	    	   	}
    	        	else if (world.getBlock(x, y, z).equals(BlockList.campfire.get())) {
    	        		int blockMetaData = world.getBlockMetadata(x, y, z);
    	        		//System.out.println("meta of campfire is " + blockMetaData);
    	        		if (blockMetaData == 0){
    	        			itemstack1 = new ItemStack(Blocks.planks, randomGen.nextInt(3)+ 1, OreDictionary.WILDCARD_VALUE );
    	        			itemstack2 = new ItemStack(Items.coal, 1, OreDictionary.WILDCARD_VALUE );
    	        		}
    	        		else if (blockMetaData == 1){
    	        			itemstack1 = new ItemStack(Blocks.cobblestone, randomGen.nextInt(2)+ 1);
    	        			itemstack2 = new ItemStack(Items.coal, 1, OreDictionary.WILDCARD_VALUE );
    	        		}
    	    	   	}
   	            
        	    	if (itemstack1 != null){
        	    		double xrand = (double) (world.rand.nextFloat() * 0.7F) + (double) (0.3F) * 0.5D;
            	    	double yrand = (double) (world.rand.nextFloat() * 0.7F) + (double) (0.3F) * 0.5D;
            	    	double zrand = (double) (world.rand.nextFloat() * 0.7F) + (double) (0.3F) * 0.5D;
        	    		EntityItem itemDrop1 = new EntityItem(world, (double) x + xrand, (double) y + yrand, (double) z + zrand, itemstack1);
        	    		itemDrop1.delayBeforeCanPickup = 10;	    	
        	    		world.spawnEntityInWorld(itemDrop1);    	    		
        	    	}
        	    	
        	    	if (itemstack2 != null){
        	    		double xrand = (double) (world.rand.nextFloat() * 0.7F) + (double) (0.3F) * 0.5D;
            	    	double yrand = (double) (world.rand.nextFloat() * 0.7F) + (double) (0.3F) * 0.5D;
            	    	double zrand = (double) (world.rand.nextFloat() * 0.7F) + (double) (0.3F) * 0.5D;
        	    		EntityItem itemDrop2 = new EntityItem(world, (double) x + xrand, (double) y + yrand, (double) z + zrand, itemstack2);
        	    		itemDrop2.delayBeforeCanPickup = 10;	    	
        	    		world.spawnEntityInWorld(itemDrop2);    	    		
        	    	}
        	    	
        	    	if (itemstack3 != null){
        	    		double xrand = (double) (world.rand.nextFloat() * 0.7F) + (double) (0.3F) * 0.5D;
            	    	double yrand = (double) (world.rand.nextFloat() * 0.7F) + (double) (0.3F) * 0.5D;
            	    	double zrand = (double) (world.rand.nextFloat() * 0.7F) + (double) (0.3F) * 0.5D;
        	    		EntityItem itemDrop3 = new EntityItem(world, (double) x + xrand, (double) y + yrand, (double) z + zrand, itemstack3);
        	    		itemDrop3.delayBeforeCanPickup = 10;	    	
        	    		world.spawnEntityInWorld(itemDrop3);    	    		
        	    	}

        	    	world.setBlockToAir(x, y, z);
        	    	//if (clearBlock){
        	    		//this.onBlockDestroyed(itemstack, world, world.getBlock(x, y, z), x, y, z, entityplayer);
        	    	//	world.setBlockToAir(x, y, z);
        	    	//}
        	    	//System.out.println("current item damage " + itemstack.getItemDamageForDisplay());
        	    	return true;
    	    	}

        	} else if (side == Side.CLIENT) {
        		return true;
        	} else {
        		return false;
        	}
        }
        return true;
    }
   
  //  @Override
  //  public boolean onBlockStartBreak(ItemStack itemstack, int x, int y, int z, EntityPlayer entityplayer) {
    	
    	
    
    
}

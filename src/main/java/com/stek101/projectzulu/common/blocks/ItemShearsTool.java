package com.stek101.projectzulu.common.blocks;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import com.google.common.collect.Sets;
import com.stek101.projectzulu.common.ProjectZulu_Core;
import com.stek101.projectzulu.common.core.DefaultProps;

public class ItemShearsTool extends ItemTool {
	
	public float weaponDamage;
	private final Item.ToolMaterial material;
	private static final Set field_150915_c = Sets.newHashSet(new Block[] {Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, 
			Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, 
			Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, 
			Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail, 
			Blocks.golden_rail, Blocks.activator_rail, Blocks.crafting_table, Blocks.furnace, Blocks.chest, Blocks.planks, Blocks.bookshelf, Blocks.log, 
			Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin});
	//private static final Set field_150915_c = Sets.newHashSet(new Block[] {});

    public ItemShearsTool(String name, ToolMaterial mat) {
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
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase living)
	{
		if (this.canHarvestBlock(block, stack) || block == Blocks.tripwire || block instanceof IShearable)
		{
			return true;
		}
		return super.onBlockDestroyed(stack, world, block, x, y, z, living);
	}

}

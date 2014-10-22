package com.stek101.projectzulu.common.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.stek101.projectzulu.common.ProjectZulu_Core;
import com.stek101.projectzulu.common.core.DefaultProps;

public class ItemXPGem extends Item {
	private int storedXP;
	private String playerName;

    public ItemXPGem(boolean full3D, String name, int xpAmount) {
        /*maxStackSize = 64;
        setMaxDamage(10);
        setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        bFull3D = full3D;
        setHasSubtypes(true);
        setUnlocalizedName(name);
        setTextureName(DefaultProps.blockKey + ":" + name);
        this.storedXP = xpAmount;*/
        this(full3D, name, xpAmount, "");
    }
    
    public ItemXPGem(boolean full3D, String name, int xpAmount, String playerName) {
        super();
        maxStackSize = 1;
        setMaxDamage(10);
        setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        bFull3D = full3D;
        setHasSubtypes(true);
        setUnlocalizedName(name);
        setTextureName(DefaultProps.blockKey + ":" + name);
        this.storedXP = xpAmount;
        this.playerName = playerName;
    }
 
   public void setStoredXP(int xpAmount){
	   storedXP = xpAmount;
   }
   
   @Override
   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
	if (this.playerName != "") {   
	 if (player.getDisplayName() == this.playerName)
	 {
       player.addExperience(this.storedXP);
       itemstack.damageItem(10, player);
	 }
	 else
	 {
		 System.out.println("Your are not the owner of this XP Gem. BEGONE!!!!");
	 }
	}
	else{
		itemstack.damageItem(10, player);
	}
       return itemstack;
   }

    
    
    
}
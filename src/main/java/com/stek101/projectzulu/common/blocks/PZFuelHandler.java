package com.stek101.projectzulu.common.blocks;

import net.minecraft.item.ItemStack;

import com.stek101.projectzulu.common.api.ItemList;

import cpw.mods.fml.common.IFuelHandler;

public class PZFuelHandler implements IFuelHandler {
	
	@Override
	public int getBurnTime(ItemStack fuel) {
	
	  if (ItemList.coconutShell.isPresent()){
		if(fuel.getItem() == ItemList.coconutShellFuel.get()) 
			return 400;
	  }
	  
		return 0;
	}

}

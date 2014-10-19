package com.stek101.projectzulu.common.blocks;

import com.stek101.projectzulu.common.api.ItemList;

import net.minecraft.item.ItemStack;
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

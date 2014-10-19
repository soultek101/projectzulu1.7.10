package com.stek101.projectzulu.common.mobs;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

import com.google.common.base.Optional;
import com.stek101.projectzulu.common.api.ItemList;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ChangeVanillaDrops {

@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
public void onEvent(LivingDropsEvent event)
{
    if (event.entity instanceof EntitySheep)
    {
        // DEBUG
    	Optional<?> itemBlock = ItemList.muttonRaw;
    	
    	if (itemBlock.isPresent()) {
    	ItemStack itemStackToDrop = new ItemStack((Item)itemBlock.get());
    	
        event.drops.add(new EntityItem(event.entity.worldObj, event.entity.posX, 

              event.entity.posY, event.entity.posZ, itemStackToDrop));
    	}
    }
} 
}
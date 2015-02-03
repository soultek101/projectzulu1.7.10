package com.stek101.projectzulu.common.potion;

import java.util.Collection;
import java.util.Iterator;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import com.stek101.projectzulu.common.core.ProjectZuluLog;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventHandleNullPotions {

    @SubscribeEvent
    public void removeNullPotionEffects(LivingUpdateEvent event) {
        EntityLivingBase entityLiving = event.entityLiving;
        if (entityLiving.ticksExisted < 20) {
            Collection potionEffects = entityLiving.getActivePotionEffects();
            Iterator iterator = potionEffects.iterator();
         try    {
            while (iterator.hasNext()) {
                PotionEffect potionEffectToRemove = (PotionEffect) iterator.next();
                
                if (Potion.potionTypes[potionEffectToRemove.getPotionID()] == null) {
                    ProjectZuluLog.info("Found Invalid Potion Effect. Removing Effect with ID %s.",
                            potionEffectToRemove.getPotionID());
                    iterator.remove();
                }
                
            }
        }
         catch(ArrayIndexOutOfBoundsException exception) {
        	 ProjectZuluLog.info("Error encountered when removing Effect");
        	}
        }
    }
}

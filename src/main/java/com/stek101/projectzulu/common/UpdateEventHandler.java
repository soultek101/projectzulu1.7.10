package com.stek101.projectzulu.common;

import net.minecraft.util.ChatComponentText;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class UpdateEventHandler {

    @SubscribeEvent
	public void checkForUpdate(PlayerEvent.PlayerLoggedInEvent event)
	{
    	if(ProjectZulu_Core.modOutDated)
		{
			event.player.addChatComponentMessage(new ChatComponentText("Attention : Your copy of the Project Zulu mod for MC 1.7.10 is Outdated. Please visit the Project Zulu for MC 1.7.10 thread in the Minecraft Forums to get the latest copy."));
			//event.player.addChatComponentMessage(new ChatComponentText("Changelog: http://www.yahoo.com"));
			//event.player.addChatComponentMessage(new ChatComponentText(updates));
		}
	}
}

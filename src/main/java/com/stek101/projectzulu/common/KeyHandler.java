package com.stek101.projectzulu.common;

import net.minecraft.client.Minecraft;

import com.stek101.projectzulu.common.mobs.packets.PZPacketKeyBind;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class KeyHandler {
    //public static KeyBinding mount_jump;
    //public static KeyBinding mount_inv;
    private PZPacketKeyBind message;
	private Minecraft mc;
	public static KeyHandler instance;
    
    public KeyHandler(){
		instance = this;
    	//mount_jump = new KeyBinding("key.ping", Keyboard.KEY_0, "key.categories.pz");
        // Define the "pong" binding, with (unlocalized) name "key.pong" and
        // the category with (unlocalized) name "key.categories.mymod" and
        // key code 25 ("P", LWJGL constant: Keyboard.KEY_P)
        //mount_inv = new KeyBinding("key.pong", Keyboard.KEY_P, "key.categories.pz");

        // Register both KeyBindings to the ClientRegistry
        //ClientRegistry.registerKeyBinding(mount_jump);
        //ClientRegistry.registerKeyBinding(mount_inv);
    }
	

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
    	 if(Minecraft.getMinecraft().inGameHasFocus) {
    		   if(Minecraft.getMinecraft().gameSettings.keyBindJump.getIsKeyPressed())
			   {
		            message = new PZPacketKeyBind().setPacketData("jump", 90);
		            ProjectZulu_Core.packetHandler.sendToServer(message);
		        }
    		   if(Minecraft.getMinecraft().gameSettings.keyBindInventory.getIsKeyPressed()){
    	            message = new PZPacketKeyBind().setPacketData("inventory", 0); 
    	            ProjectZulu_Core.packetHandler.sendToServer(message);
    	        }
    		   if(Minecraft.getMinecraft().gameSettings.keyBindSneak.getIsKeyPressed()){
   	            message = new PZPacketKeyBind().setPacketData("dismount", 0); 
   	            ProjectZulu_Core.packetHandler.sendToServer(message);
   	        }
    		   
		   }
    }
}

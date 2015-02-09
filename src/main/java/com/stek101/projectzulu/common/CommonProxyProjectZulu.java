package com.stek101.projectzulu.common;

import java.io.File;

import net.minecraft.entity.player.EntityPlayer;

public class CommonProxyProjectZulu {

    public int addArmor(String armor) {
        return 0;
    }
    
    public int addTool(String tool){
    	return 0;
    }

    public void bossHealthTicker() {
    }

    public void registerModelsAndRender() {
    }
    
    public EntityPlayer getClientPlayer() { return null; }
    
    // ========== Get Minecraft Directory ==========
    public File getMinecraftDir() {
    	return new File(".");
    }
}

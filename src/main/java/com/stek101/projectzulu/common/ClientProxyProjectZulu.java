package com.stek101.projectzulu.common;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.player.EntityPlayer;

import com.stek101.projectzulu.common.blocks.EntityCreeperBlossomPrimed;
import com.stek101.projectzulu.common.blocks.RenderCreeperBlossomPrimed;
import com.stek101.projectzulu.common.core.CustomEntityManager;
import com.stek101.projectzulu.common.mobs.BossHealthDisplayTicker;
import com.stek101.projectzulu.common.mobs.EntityGenericEgg;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;

public class ClientProxyProjectZulu extends CommonProxyProjectZulu{ 	
	
	@Override
	public int addArmor(String armor){
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}
	
	@Override
	public void bossHealthTicker(){
	    FMLCommonHandler.instance().bus().register(new BossHealthDisplayTicker());
	}
	
	@Override
	public void registerModelsAndRender() {
		CustomEntityManager.INSTANCE.registerModelsAndRender();
	}
	
	// ========== Get Client Player Entity ==========
		@Override
	    public EntityPlayer getClientPlayer() {
			return Minecraft.getMinecraft().thePlayer;
		}
}

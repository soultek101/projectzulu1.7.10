package com.stek101.projectzulu.common.mobs;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;

import org.lwjgl.opengl.GL11;

import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.mobs.entity.EntityGenericAnimal;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class BossHealthDisplayTicker {
       
	public static final ResourceLocation icons = new ResourceLocation("textures/gui/icons.png");
	public static EntityGenericAnimal targetBoss;
	protected Minecraft mc = Minecraft.getMinecraft();
    protected float zLevel = 0.0F;
    static EntityPlayer player;
    private Configuration config; 
    private boolean displayBossHealth = true;
    
  //  public BossHealthDisplayTicker (EntityGenericAnimal mobEntity) {
  //  	this.targetBoss = mobEntity;
  //  }

	public static void registerEntity(EntityGenericAnimal newTicker) {
        targetBoss = newTicker;
    }

    public static boolean validTargetPresent(EntityGenericAnimal targetBoss) {
    	if (Minecraft.getMinecraft().thePlayer != null){
         //return targetBoss != null && !targetBoss.isDead && targetBoss.canEntityBeSeen(Minecraft.getMinecraft().thePlayer);
    	 return targetBoss != null && !targetBoss.isDead && Minecraft.getMinecraft().thePlayer.canEntityBeSeen(targetBoss)
    				&& (targetBoss.getDistanceToEntity(Minecraft.getMinecraft().thePlayer) < 16.0f);
    	}
    		else
    	 return false;
    }
    
    @SubscribeEvent
    public void TickEvent(RenderTickEvent event) {
    	config = new Configuration(new File( "." + "/config/", DefaultProps.configDirectory
                + DefaultProps.defaultConfigFile));
        config.load();                
        displayBossHealth = config.get("mob controls", "Display PZBoss HealthBar", this.displayBossHealth).getBoolean(displayBossHealth);
        config.save();
       
        if (event.phase == Phase.END && displayBossHealth) {
            if (validTargetPresent(targetBoss) && Minecraft.getMinecraft().thePlayer != null) {
                renderBossHealthBar(targetBoss, targetBoss.getDefaultEntityName() + " Health");
            }
            
        }
    }

    public void renderBossHealthBar(EntityGenericAnimal boss, String healthBarTitle) {
        
        FontRenderer fontRenderer = mc.fontRenderer;

        /* Draw Title */
        ScaledResolution var3 = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int screenWidth = var3.getScaledWidth();
        byte healthBarHeight = 12;
        fontRenderer.drawStringWithShadow(healthBarTitle, screenWidth / 2 - fontRenderer.getStringWidth(healthBarTitle)
                / 2, healthBarHeight - 10, 16711935);

        /* Draw Health Bar    */
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture(icons).getGlTextureId());
        short fullHealthBarWidth = 182;
        int healthBarOffset = screenWidth / 2 - fullHealthBarWidth / 2;
        int currHealthBarWidth = (int) ((float) boss.getHealth() / (float) boss.getMaxHealth() * (fullHealthBarWidth + 1));

        this.drawTexturedModalRect(healthBarOffset, healthBarHeight, 0, 74, fullHealthBarWidth, 5);
        if (currHealthBarWidth > 0) {
            this.drawTexturedModalRect(healthBarOffset, healthBarHeight, 0, 79, currHealthBarWidth, 5);
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); 
    }
    
   public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6) {
        float var7 = 0.00390625F;
        float var8 = 0.00390625F;

        Tessellator var9 = Tessellator.instance;
        var9.startDrawingQuads();
        var9.addVertexWithUV(par1 + 0, par2 + par6, this.zLevel, (par3 + 0) * var7, (par4 + par6) * var8);
        var9.addVertexWithUV(par1 + par5, par2 + par6, this.zLevel, (par3 + par5) * var7, (par4 + par6) * var8);
        var9.addVertexWithUV(par1 + par5, par2 + 0, this.zLevel, (par3 + par5) * var7, (par4 + 0) * var8);
        var9.addVertexWithUV(par1 + 0, par2 + 0, this.zLevel, (par3 + 0) * var7, (par4 + 0) * var8);
        var9.draw();
    }
}

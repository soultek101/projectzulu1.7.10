package com.stek101.projectzulu.common.mobs;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import com.stek101.projectzulu.common.mobs.entity.EntityCamel;
import com.stek101.projectzulu.common.mobs.entity.EntityGenericAnimal;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiScreenCamelInventory extends GuiContainer
{

	private static final ResourceLocation camelGuiTextures = new ResourceLocation("textures/gui/container/horse.png");
    private IInventory field_147030_v;
    private IInventory field_147029_w;
    private EntityCamel field_147034_x;
    private float field_147033_y;
    private float field_147032_z;
    private int entityID;
    private String entityName;
    
    public GuiScreenCamelInventory(World world, InventoryPlayer p_i1093_1_, int entityID)
    {
        super(new ContainerCamelInventory(p_i1093_1_, entityID, world));
        this.field_147030_v = p_i1093_1_;
        this.entityID = entityID;
        entityName = ((EntityGenericAnimal) world.getEntityByID(entityID)).getEntityTamed();
        
        this.field_147034_x = (EntityCamel) world.getEntityByID(entityID);
        this.field_147029_w = field_147034_x.camelChest;
        //this.field_147029_w = p_i1093_2_;
        //this.field_147034_x = p_i1093_3_;
        
        this.allowUserInput = false;
    }
    
    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        this.fontRendererObj.drawString(this.field_147029_w.hasCustomInventoryName() ? this.field_147029_w.getInventoryName() : I18n.format(this.field_147029_w.getInventoryName(), new Object[0]), 8, 6, 4210752);
        this.fontRendererObj.drawString(this.field_147030_v.hasCustomInventoryName() ? this.field_147030_v.getInventoryName() : I18n.format(this.field_147030_v.getInventoryName(), new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        //this.mc.getTextureManager().bindTexture(camelGuiTextures);
        this.mc.renderEngine.bindTexture(camelGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        if (this.field_147034_x.isChested())
        {
            this.drawTexturedModalRect(k + 79, l + 17, 0, this.ySize, 90, 54);
        }

       // if (this.field_147034_x.func_110259_cr()) for armor
       // {
      //      this.drawTexturedModalRect(k + 7, l + 35, 0, this.ySize + 54, 18, 18);
       // }

        GuiInventory.func_147046_a(k + 51, l + 60, 17, (float)(k + 51) - this.field_147033_y, (float)(l + 75 - 50) - this.field_147032_z, this.field_147034_x);
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        this.field_147033_y = (float)p_73863_1_;
        this.field_147032_z = (float)p_73863_2_;
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    }
}


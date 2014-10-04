package com.stek101.projectzulu.common.blocks;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import com.stek101.projectzulu.common.ProjectZulu_Core;
import com.stek101.projectzulu.common.mobs.entity.EntityGenericTameable;
import com.stek101.projectzulu.common.mobs.packets.PZPacketNameSync;

//@SideOnly(Side.CLIENT)
public class GuiAnimalName extends GuiScreen {
    /**
     * This String is just a local copy of the characters allowed in text rendering of minecraft.
     */
    private static final char[] allowedCharacters = ChatAllowedCharacters.allowedCharacters;

    /** The title string that is displayed in the top-center of the screen. */
    protected String screenTitle = "Entity Name:";

    /** Counts the number of screen updates. */
    private int updateCounter = 0;

    /** The number of the line that is being edited. */
    private int editLine = 0;

    World world;
    EntityPlayer player;
    /* ID of Entity to be Named */
    int entityID;
    public String entityName = "";
    public String defaultEntityName ="";
    public String drawStr="";

    public GuiAnimalName(World world, EntityPlayer player, int entityID) {
        this.world = world;
        this.player = player;
        this.entityID = entityID;
        entityName = ((EntityGenericTameable) world.getEntityByID(entityID)).getEntityTamed();
        defaultEntityName = ((EntityGenericTameable) world.getEntityByID(entityID)).getDefaultEntityName();
        
        if (entityName == null || entityName == "") {
        	
        	entityName = defaultEntityName;
        }
        
        drawStr = entityName;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui() {
        this.buttonList.clear();
        Keyboard.enableRepeatEvents(true);
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120, "Done"));
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed() {    	
        Keyboard.enableRepeatEvents(false);
        //PacketNameSync packet = new PacketNameSync().setPacketData(entityID, drawStr);
        //ProjectZulu_Core.getPipeline().sendToServer(packet);        
        //ProjectZulu_Core.packetHandler.sendToPlayer(message, (EntityPlayerMP) player);
        PZPacketNameSync message = new PZPacketNameSync().setPacketData(entityID, drawStr);
        ProjectZulu_Core.packetHandler.sendToServer(message);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen() {
        ++this.updateCounter;
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton par1GuiButton) {
        if (par1GuiButton.enabled) {
            if (par1GuiButton.id == 0) {
            	this.mc.displayGuiScreen((GuiScreen) null);
            }
        }
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char par1, int par2) {
        if (par2 == Keyboard.KEY_BACK) {
         if( this.drawStr.length() > 0) {
        	 this.drawStr = this.drawStr.substring(0, this.drawStr.length() - 1);
          }
       }

        if (ChatAllowedCharacters.isAllowedCharacter(par1) && this.entityName.length() < 10) {
            this.drawStr = this.drawStr + par1;            
        }       
        super.keyTyped(par1, par2);
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3) {
    	this.drawDefaultBackground();
        this.drawCenteredString(fontRendererObj, this.screenTitle, this.width / 2, 40, 16777215);
        this.drawCenteredString(fontRendererObj, this.drawStr, this.width / 2, 40 + fontRendererObj.FONT_HEIGHT,
                16777215);
        super.drawScreen(par1, par2, par3);
    }
}

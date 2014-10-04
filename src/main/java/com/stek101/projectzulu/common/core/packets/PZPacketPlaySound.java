package com.stek101.projectzulu.common.core.packets;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;

import com.stek101.projectzulu.common.ProjectZulu_Core;
import com.stek101.projectzulu.common.core.ProjectZuluLog;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class PZPacketPlaySound implements IMessage, IMessageHandler<PZPacketPlaySound, IMessage>{
	private int posX;
    private int posY;
    private int posZ;
    private String sound;

    public PZPacketPlaySound setPacketData(int xPos, int yPos, int zPos, String sound) {
        this.posX = xPos;
        this.posY = yPos;
        this.posZ = zPos;        
        this.sound = sound;
        return this;
    }
    
	@Override
	public IMessage onMessage(PZPacketPlaySound message, MessageContext ctx) {
		if(ctx.side != Side.CLIENT) return null;
		
		EntityPlayer player = ProjectZulu_Core.proxy.getClientPlayer();
        World worldObj = player.worldObj;
        worldObj.playSound(message.posX, message.posY, message.posZ, message.sound, 1.0f, 1.0f, false);
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		PacketBuffer buffer = new PacketBuffer(buf);
		try {	
		 posX = buffer.readInt();
		 //System.out.println("fromBytes posx" + posX);
	     posY = buffer.readInt();
	     //System.out.println("fromBytes posy" + posY);
	     posZ = buffer.readInt();
	     //System.out.println("fromBytes posz" + posZ);
	     int soundLength = buffer.readInt();
	     //System.out.println("fromBytes sound length" + soundLength);
	     
	     //   char[] soundChars = new char[soundLength];
	     //   for (int i = 0; i < soundChars.length; i++) {
	     //       soundChars[i] = buffer.readChar();
	     //   }
	     
	     sound = buffer.readStringFromBuffer(soundLength);   
	     //   sound = new String(soundChars);
	        
		} catch (Exception e) {
			ProjectZuluLog.severe("There was a problem decoding the packet in PZPacketPlaySound : " + buffer + ".", this);
            e.printStackTrace();
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer buffer = new PacketBuffer(buf);
		try {	
			buffer.writeInt(posX);
			buffer.writeInt(posY);
			buffer.writeInt(posZ);
			buffer.writeInt(sound.length());
			buffer.writeStringToBuffer(sound);
			
		}  catch (IOException e) {
			ProjectZuluLog.severe("There was a problem encoding the packet in PZPacketPlaySound : " + buffer + ".", this);
            e.printStackTrace();
		} 
	}

}

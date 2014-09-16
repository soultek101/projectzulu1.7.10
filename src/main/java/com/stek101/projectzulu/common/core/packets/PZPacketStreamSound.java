package com.stek101.projectzulu.common.core.packets;

import java.io.IOException;

import com.stek101.projectzulu.common.ProjectZulu_Core;
import com.stek101.projectzulu.common.core.ProjectZuluLog;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class PZPacketStreamSound implements IMessage, IMessageHandler<PZPacketStreamSound, IMessage>{
	private int posX;
    private int posY;
    private int posZ;
    private String sound;

    public PZPacketStreamSound setPacketData(int xPos, int yPos, int zPos, String sound) {
        this.posX = xPos;
        this.posY = yPos;
        this.posZ = zPos;
        this.sound = sound;
        return this;
    }
        
	@Override
	public IMessage onMessage(PZPacketStreamSound message, MessageContext ctx) {
		if(ctx.side != Side.CLIENT) return null;
		
		 EntityPlayer player = ProjectZulu_Core.proxy.getClientPlayer();
		 World worldObj = player.worldObj;
	     worldObj.playRecord(message.sound, message.posX, message.posY, message.posZ);
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
      PacketBuffer buffer = new PacketBuffer(buf);
	  try {	
		posX = buffer.readInt();
        posY = buffer.readInt();
        posZ = buffer.readInt();
        int soundLength = buffer.readInt();
        char[] soundChars = new char[soundLength];
        for (int i = 0; i < soundChars.length; i++) {
            soundChars[i] = buffer.readChar();
        }
        sound = new String(soundChars);
	  } catch (Exception e) {
			ProjectZuluLog.severe("There was a problem decoding the packet in PZPacketStreamSound : " + buffer + ".", this);
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
			ProjectZuluLog.severe("There was a problem encoding the packet in PZPacketStreamSound : " + buffer + ".", this);
          e.printStackTrace();
		} 
	}

}

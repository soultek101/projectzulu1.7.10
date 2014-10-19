package com.stek101.projectzulu.common.core.packets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.stek101.projectzulu.common.ProjectZulu_Core;
import com.stek101.projectzulu.common.core.ProjectZuluLog;

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
	    public void toBytes(ByteBuf buffer) {
	        ByteBufOutputStream data = new ByteBufOutputStream(buffer);
	        try {
	            writeData(data);
	        } catch (Exception e) {
	            ProjectZuluLog.severe("Error writing packet %s to ByteBufOutputStream", this);
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void fromBytes(ByteBuf buffer) {
	        ByteBufInputStream byteStream = new ByteBufInputStream(buffer);
	        try {
	            readData(byteStream);
	        } catch (Exception e) {
	            ProjectZuluLog.severe("Error reading packet %s from ByteBufInputStream", this);
	            e.printStackTrace();
	        }
	    }
	    
    protected void writeData(ByteBufOutputStream buffer) throws IOException {
        buffer.writeInt(posX);
        buffer.writeInt(posY);
        buffer.writeInt(posZ);
        buffer.writeInt(sound.length());
        buffer.writeChars(sound);
    }


    protected void readData(ByteBufInputStream buffer) throws IOException {
        posX = buffer.readInt();
        posY = buffer.readInt();
        posZ = buffer.readInt();
        int soundLength = buffer.readInt();
        char[] soundChars = new char[soundLength];
        for (int i = 0; i < soundChars.length; i++) {
            soundChars[i] = buffer.readChar();
        }
        sound = new String(soundChars);
    }

}

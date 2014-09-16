package com.stek101.projectzulu.common.mobs.packets;

import java.io.IOException;

import io.netty.buffer.ByteBuf;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;

import com.stek101.projectzulu.common.blocks.tombstone.TileEntityTombstone;
import com.stek101.projectzulu.common.core.ProjectZuluLog;

public class PZPacketTileText implements IMessage, IMessageHandler<PZPacketTileText, IMessage> {
    private int tileLocationX;
    private int tileLocationY;
    private int tileLocationZ;
    private String[] text;

    public PZPacketTileText setPacketData(int tileLocationX, int tileLocationY, int tileLocationZ, String[] text) {
        this.tileLocationX = tileLocationX;
        this.tileLocationY = tileLocationY;
        this.tileLocationZ = tileLocationZ;
        this.text = text;
        return this;
    }
    
	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer buffer = new PacketBuffer(buf);
		try {	
		    buffer.writeInt(tileLocationX);
	        buffer.writeInt(tileLocationY);
	        buffer.writeInt(tileLocationZ);
	        buffer.writeInt(text.length);
	       
	         for (String string : text) {
	            buffer.writeInt(string.length());
	            buffer.writeStringToBuffer(string);
	        }
		} catch (IOException e) {
			ProjectZuluLog.severe("There was a problem encoding the packet in PZPacketTileText : " + buffer + ".", this);
            e.printStackTrace();
		} 
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		PacketBuffer buffer = new PacketBuffer(buf);
		try {
        tileLocationX = buffer.readInt();
        tileLocationY = buffer.readInt();
        tileLocationZ = buffer.readInt();
        int numTexts = buffer.readInt();
        text = new String[numTexts];
        for (int i = 0; i < numTexts; i++) {
            int stringLength = buffer.readInt();
            char[] stringChars = new char[stringLength];
            for (int j = 0; j < stringLength; j++) {
                stringChars[j] = buffer.readChar();
            }
            text[i] = new String(stringChars);
         }
		} catch (Exception e) {
			ProjectZuluLog.severe("There was a problem decoding the packet in PZPacketTileText : " + buffer + ".", this);
            e.printStackTrace();
		}
	}

	@Override
	public IMessage onMessage(PZPacketTileText message, MessageContext ctx) {
		if(ctx.side != Side.SERVER) return null;
		 EntityPlayer player = ctx.getServerHandler().playerEntity;
		 
		 if (player.worldObj.getTileEntity(message.tileLocationX, message.tileLocationY, message.tileLocationZ) instanceof TileEntityTombstone) {
	            TileEntity tile = player.worldObj.getTileEntity(message.tileLocationX, message.tileLocationY, message.tileLocationZ);
	            if (tile != null && tile instanceof TileEntityTombstone) {
	                TileEntityTombstone tombstone = (TileEntityTombstone) tile;
	                tombstone.signText = message.text;
	                player.worldObj.markBlockForUpdate(message.tileLocationX, message.tileLocationY, message.tileLocationZ);
	            }
	        }
		return null;
	}

}

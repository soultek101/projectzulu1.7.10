package com.stek101.projectzulu.common.mobs.packets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

import com.stek101.projectzulu.common.blocks.tombstone.TileEntityTombstone;
import com.stek101.projectzulu.common.core.ProjectZuluLog;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

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
        buffer.writeInt(tileLocationX);
        buffer.writeInt(tileLocationY);
        buffer.writeInt(tileLocationZ);
        buffer.writeInt(text.length);
        for (String string : text) {
            buffer.writeInt(string.length());
            buffer.writeChars(string);
        }
    }

    protected void readData(ByteBufInputStream buffer) throws IOException {
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

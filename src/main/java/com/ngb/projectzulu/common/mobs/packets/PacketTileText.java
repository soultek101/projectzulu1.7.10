package com.ngb.projectzulu.common.mobs.packets;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import com.ngb.projectzulu.common.blocks.tombstone.TileEntityTombstone;
import com.ngb.projectzulu.common.core.PZPacket;
import com.ngb.projectzulu.common.dungeon.packets.PacketByteStream;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketTileText extends PacketByteStream implements IMessageHandler<PacketTileText, IMessage> {
    private int tileLocationX;
    private int tileLocationY;
    private int tileLocationZ;
    private String[] text;

    public PacketTileText setPacketData(int tileLocationX, int tileLocationY, int tileLocationZ, String[] text) {
        this.tileLocationX = tileLocationX;
        this.tileLocationY = tileLocationY;
        this.tileLocationZ = tileLocationZ;
        this.text = text;
        return this;
    }

    @Override
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

    @Override
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
    public void handleClientSide(EntityPlayer player) {
    }

    @Override
    public void handleServerSide(EntityPlayer player) {
        if (player.worldObj.getTileEntity(tileLocationX, tileLocationY, tileLocationZ) instanceof TileEntityTombstone) {
            TileEntity tile = player.worldObj.getTileEntity(tileLocationX, tileLocationY, tileLocationZ);
            if (tile != null && tile instanceof TileEntityTombstone) {
                TileEntityTombstone tombstone = (TileEntityTombstone) tile;
                tombstone.signText = text;
                player.worldObj.markBlockForUpdate(tileLocationX, tileLocationY, tileLocationZ);
            }
        }
    }

	@Override
	public IMessage onMessage(PacketTileText message, MessageContext ctx) {
		return handleMessage(message, ctx);
	}
}

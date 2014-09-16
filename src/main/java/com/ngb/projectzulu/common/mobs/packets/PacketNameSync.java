package com.ngb.projectzulu.common.mobs.packets;

import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;

import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import com.ngb.projectzulu.common.dungeon.packets.PacketByteStream;
import com.ngb.projectzulu.common.mobs.entity.EntityGenericTameable;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * Sync entity Name to server from GUI
 */
public class PacketNameSync extends PacketByteStream implements IMessageHandler<PacketNameSync, IMessage> {
    private int entityIdToBeNamed;
    private String entityName;

    public PacketNameSync setPacketData(int entityIdToBeNamed, String entityName) {
        this.entityIdToBeNamed = entityIdToBeNamed;
        this.entityName = entityName;
        return this;
    }

    @Override
    protected void writeData(ByteBufOutputStream buffer) throws IOException {
        buffer.writeInt(entityIdToBeNamed);
        buffer.writeInt(entityName.length());
        buffer.writeChars(entityName);
    }

    @Override
    protected void readData(ByteBufInputStream buffer) throws IOException {
        entityIdToBeNamed = buffer.readInt();
        int nameLength = buffer.readInt();
        char[] nameChars = new char[nameLength];
        for (int i = 0; i < nameLength; i++) {
            nameChars[i] = buffer.readChar();
        }
    }

    @Override
    public void handleClientSide(EntityPlayer player) {
    }

    @Override
    public void handleServerSide(EntityPlayer player) {
        Entity entity = player.worldObj.getEntityByID(entityIdToBeNamed);
        if (entity != null && entity instanceof EntityGenericTameable) {
            ((EntityGenericTameable) entity).setUsername(entityName);
        }
    }

	@Override
	public IMessage onMessage(PacketNameSync message, MessageContext ctx) {
		return handleMessage(message, ctx);
	}
}

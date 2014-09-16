package com.ngb.projectzulu.common.dungeon.packets;

import ibxm.Player;
import io.netty.buffer.ByteBuf;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import com.ngb.projectzulu.common.core.PZPacket;
import com.ngb.projectzulu.common.core.PZPacketBase;
import com.ngb.projectzulu.common.dungeon.TileEntityLimitedMobSpawner;
import com.ngb.projectzulu.common.mobs.packets.PacketAnimTime;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketMobSpawner extends PacketDataStream implements IMessageHandler<PacketMobSpawner, IMessage> {
    int entityIDtoSync;
    private int posX;
    private int posY;
    private int posZ;
    private NBTTagCompound customData;

    public PacketMobSpawner setPacketData(int xPos, int yPos, int zPos, NBTTagCompound customData) {
        this.posX = xPos;
        this.posY = yPos;
        this.posZ = zPos;
        this.customData = customData;
        return this;
    }

    @Override
    protected void writeData(DataOutputStream buffer) throws IOException {
        buffer.writeInt(posX);
        buffer.writeInt(posY);
        buffer.writeInt(posZ);
        writeNBTTagCompound(customData, buffer);
    }

    @Override
    protected void readData(DataInputStream buffer) throws IOException {
        posX = buffer.readInt();
        posY = buffer.readInt();
        posZ = buffer.readInt();
        customData = readNBTTagCompound(buffer);
    }

    @Override
    public void handleClientSide(EntityPlayer player) {
        World world = player.getEntityWorld();
        TileEntity tileEntity = world.getTileEntity(posX, posY, posZ);
        if (tileEntity != null && tileEntity instanceof TileEntityLimitedMobSpawner) {
            tileEntity.readFromNBT(customData);
            ((TileEntityLimitedMobSpawner) tileEntity).forceUpdate();
        }
    }

    @Override
    public void handleServerSide(EntityPlayer player) {
        World world = player.getEntityWorld();
        TileEntity tileEntity = world.getTileEntity(posX, posY, posZ);
        if (tileEntity != null && tileEntity instanceof TileEntityLimitedMobSpawner) {
            tileEntity.readFromNBT(customData);
            ((TileEntityLimitedMobSpawner) tileEntity).forceUpdate();
        }
    }

	@Override
	public void toBytes(ByteBuf buffer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IMessage onMessage(PacketMobSpawner message, MessageContext ctx) {
		return handleMessage(message, ctx);
	}
}

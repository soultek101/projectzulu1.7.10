package com.ngb.projectzulu.common.core.packets;

import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import com.ngb.projectzulu.common.dungeon.packets.PacketByteStream;
import com.ngb.projectzulu.common.mobs.packets.PacketAnimTime;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketPlaySound extends PacketByteStream implements IMessageHandler<PacketPlaySound, IMessage> {
    private int posX;
    private int posY;
    private int posZ;
    private String sound;

    public PacketPlaySound setPacketData(int xPos, int yPos, int zPos, String sound) {
        this.posX = xPos;
        this.posY = yPos;
        this.posZ = zPos;
        this.sound = sound;
        return this;
    }

    @Override
    protected void writeData(ByteBufOutputStream buffer) throws IOException {
        buffer.writeInt(posX);
        buffer.writeInt(posY);
        buffer.writeInt(posZ);
        buffer.writeInt(sound.length());
        buffer.writeChars(sound);
    }

    @Override
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

    @Override
    public void handleClientSide(EntityPlayer player) {
        World worldObj = player.worldObj;
        worldObj.playSound(posX, posY, posZ, sound, 1.0f, 1.0f, false);
    }

    @Override
    public void handleServerSide(EntityPlayer player) {
    }

	@Override
	public IMessage onMessage(PacketPlaySound message, MessageContext ctx) {
		return handleMessage(message, ctx);
	}
}
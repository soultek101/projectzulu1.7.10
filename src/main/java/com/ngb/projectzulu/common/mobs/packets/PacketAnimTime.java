package com.ngb.projectzulu.common.mobs.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.ngb.projectzulu.common.core.PZPacket;
import com.ngb.projectzulu.common.core.PZPacketBase;
import com.ngb.projectzulu.common.mobs.entity.EntityGenericCreature;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketAnimTime extends PZPacketBase implements IMessageHandler<PacketAnimTime, IMessage> {

    private int entityIDtoSync;
    private int animTime;

    public PacketAnimTime setPacketData(int entityIDtoSync, int animTime) {
        this.entityIDtoSync = entityIDtoSync;
        this.animTime = animTime;
        return this;
    }

    @Override
    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(entityIDtoSync);
        buffer.writeInt(animTime);
    }

    @Override
    public void fromBytes(ByteBuf buffer) {
        entityIDtoSync = buffer.readInt();
        animTime = buffer.readInt();
    }

    @Override
    public void handleClientSide(EntityPlayer player) {
        World worldObj = ((EntityPlayer) player).worldObj;
        Entity entity = worldObj.getEntityByID(entityIDtoSync);
        if (entity != null && entity instanceof EntityGenericCreature) {
            ((EntityGenericCreature) entity).setAnimTime(animTime);
        } else {
            // TODO Print to Log Entity No longer Exists
        }
    }

    @Override
    public void handleServerSide(EntityPlayer player) {

    }

	@Override
	public IMessage onMessage(PacketAnimTime message, MessageContext ctx) {
		return handleMessage(message, ctx);
	}
}

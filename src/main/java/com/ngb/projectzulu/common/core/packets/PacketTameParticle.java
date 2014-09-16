package com.ngb.projectzulu.common.core.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import com.ngb.projectzulu.common.core.PZPacket;
import com.ngb.projectzulu.common.core.PZPacketBase;
import com.ngb.projectzulu.common.mobs.entity.EntityGenericTameable;
import com.ngb.projectzulu.common.mobs.packets.PacketFollowerMasterData;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * Send Tame event to Client such that the EntityGenericTameable taming effect can be performed
 */
public class PacketTameParticle extends PZPacketBase implements IMessageHandler<PacketTameParticle, IMessage> {
    private int entityIdToTriggerEffect;
    private boolean tameingSuccess;

    public PacketTameParticle setPacketData(int entityIdToTriggerEffect, boolean tameingSuccess) {
        this.entityIdToTriggerEffect = entityIdToTriggerEffect;
        this.tameingSuccess = tameingSuccess;
        return this;
    }

    @Override
    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(entityIdToTriggerEffect);
        buffer.writeBoolean(tameingSuccess);
    }

    @Override
    public void fromBytes(ByteBuf buffer) {
        entityIdToTriggerEffect = buffer.readInt();
        tameingSuccess = buffer.readBoolean();
    }

    @Override
    public void handleClientSide(EntityPlayer player) {
        Entity entity = player.worldObj.getEntityByID(entityIdToTriggerEffect);
        if (entity != null && entity instanceof EntityGenericTameable) {
            ((EntityGenericTameable) entity).playTameEffect(tameingSuccess);
        }
    }

    @Override
    public void handleServerSide(EntityPlayer player) {
    }

	@Override
	public IMessage onMessage(PacketTameParticle message, MessageContext ctx) {
		return handleMessage(message, ctx);
	}
}

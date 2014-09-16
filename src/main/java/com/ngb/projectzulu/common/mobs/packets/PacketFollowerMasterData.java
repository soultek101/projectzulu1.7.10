package com.ngb.projectzulu.common.mobs.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import com.ngb.projectzulu.common.core.PZPacket;
import com.ngb.projectzulu.common.core.PZPacketBase;
import com.ngb.projectzulu.common.mobs.entity.EntityFollower;
import com.ngb.projectzulu.common.mobs.entity.EntityMaster;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketFollowerMasterData extends PZPacketBase implements IMessageHandler<PacketFollowerMasterData, IMessage> {

    int childEntityID;
    int masterEntityID;
    int followerIndex;

    public PacketFollowerMasterData setPacketData(int childEntityID, int masterEntityID, int followerIndex) {
        this.childEntityID = childEntityID;
        this.masterEntityID = masterEntityID;
        this.followerIndex = followerIndex;
        return this;
    }

    @Override
    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(childEntityID);
        buffer.writeInt(masterEntityID);
        buffer.writeInt(followerIndex);
    }

    @Override
    public void fromBytes(ByteBuf buffer) {
        childEntityID = buffer.readInt();
        masterEntityID = buffer.readInt();
        followerIndex = buffer.readInt();
    }

    @Override
    public void handleClientSide(EntityPlayer player) {
        World worldObj = ((EntityPlayer) player).worldObj;
        Entity childEntity = worldObj.getEntityByID(childEntityID);
        Entity masterEntity = worldObj.getEntityByID(masterEntityID);
        if (followerIndex == -1 || masterEntityID == -1 || childEntity == null
                || !(childEntity instanceof EntityFollower) || masterEntity == null
                || !(masterEntity instanceof EntityMaster)) {
            return;
        }
        ((EntityFollower) childEntity).linkMasterWithFollower(masterEntityID, followerIndex);
    }

    @Override
    public void handleServerSide(EntityPlayer player) {
    }

	@Override
	public IMessage onMessage(PacketFollowerMasterData message,
			MessageContext ctx) {
		return handleMessage(message, ctx);
	}
}

package com.stek101.projectzulu.common.mobs.packets;

import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import io.netty.buffer.ByteBuf;

import com.stek101.projectzulu.common.ProjectZulu_Core;
import com.stek101.projectzulu.common.core.ProjectZuluLog;
import com.stek101.projectzulu.common.core.packets.PZPacketStreamSound;
import com.stek101.projectzulu.common.mobs.entity.EntityFollower;
import com.stek101.projectzulu.common.mobs.entity.EntityMaster;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class PZPacketFollowerMasterData implements IMessage, IMessageHandler<PZPacketFollowerMasterData, IMessage>{

    int childEntityID;
    int masterEntityID;
    int followerIndex;

    public PZPacketFollowerMasterData setPacketData(int childEntityID, int masterEntityID, int followerIndex) {
        this.childEntityID = childEntityID;
        this.masterEntityID = masterEntityID;
        this.followerIndex = followerIndex;
        return this;
    }


@Override
public IMessage onMessage(PZPacketFollowerMasterData message, MessageContext ctx) {
	if(ctx.side != Side.CLIENT) return null;
	
	EntityPlayer player = ProjectZulu_Core.proxy.getClientPlayer();	
    World worldObj = ((EntityPlayer) player).worldObj;
    Entity childEntity = worldObj.getEntityByID(message.childEntityID);
    Entity masterEntity = worldObj.getEntityByID(message.masterEntityID);
    if (message.followerIndex == -1 || message.masterEntityID == -1 || childEntity == null
            || !(childEntity instanceof EntityFollower) || masterEntity == null
            || !(masterEntity instanceof EntityMaster)) {
        return null;
    }
    ((EntityFollower) childEntity).linkMasterWithFollower(message.masterEntityID, message.followerIndex);
	return null;
}

@Override
public void fromBytes(ByteBuf buf) {
  PacketBuffer buffer = new PacketBuffer(buf);
	try {	
		
    childEntityID = buffer.readInt();
    masterEntityID = buffer.readInt();
    followerIndex = buffer.readInt();
    
	} catch (Exception e) {
		ProjectZuluLog.severe("There was a problem decoding the packet in PZPacketFollowerMasterData : " + buffer + ".", this);
      e.printStackTrace();
	}
}

@Override
public void toBytes(ByteBuf buf) {
 PacketBuffer buffer = new PacketBuffer(buf);
  try {	
	  
	 buffer.writeInt(childEntityID);
     buffer.writeInt(masterEntityID);
     buffer.writeInt(followerIndex);
     
     }  catch (Exception e) {
		ProjectZuluLog.severe("There was a problem encoding the packet in PZPacketFollowerMasterData : " + buffer + ".", this);
    e.printStackTrace();
	} 
}
}
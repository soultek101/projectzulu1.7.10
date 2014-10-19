package com.stek101.projectzulu.common.core.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;

import com.stek101.projectzulu.common.ProjectZulu_Core;
import com.stek101.projectzulu.common.core.ProjectZuluLog;
import com.stek101.projectzulu.common.mobs.entity.EntityGenericTameable;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class PZPacketTameParticle implements IMessage, IMessageHandler<PZPacketTameParticle, IMessage>{
	 private int entityIdToTriggerEffect;
	 private boolean tameingSuccess;

	    public PZPacketTameParticle setPacketData(int entityIdToTriggerEffect, boolean tameingSuccess) {
	        this.entityIdToTriggerEffect = entityIdToTriggerEffect;
	        this.tameingSuccess = tameingSuccess;
	        return this;
	    }
	    
	@Override
	public IMessage onMessage(PZPacketTameParticle message, MessageContext ctx) {
		if(ctx.side != Side.CLIENT) return null;
		
		 EntityPlayer player = ProjectZulu_Core.proxy.getClientPlayer();
		 Entity entity = player.worldObj.getEntityByID(message.entityIdToTriggerEffect);
	        if (entity != null && entity instanceof EntityGenericTameable) {
	            ((EntityGenericTameable) entity).playTameEffect(message.tameingSuccess);
	        }
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
	   PacketBuffer buffer = new PacketBuffer(buf);
		try {	
		 entityIdToTriggerEffect = buffer.readInt();
	     tameingSuccess = buffer.readBoolean();
		 } catch (Exception e) {
				ProjectZuluLog.severe("There was a problem decoding the packet in PZPacketStreamSound : " + buffer + ".", this);
	          e.printStackTrace();
			}
	}

	@Override
	public void toBytes(ByteBuf buf) {
	   PacketBuffer buffer = new PacketBuffer(buf);
	   
	   try {
          buffer.writeInt(entityIdToTriggerEffect);
          buffer.writeBoolean(tameingSuccess);
          
	    }  catch (Exception e) {
			ProjectZuluLog.severe("There was a problem encoding the packet in PZTameParticle : " + buffer + ".", this);
         e.printStackTrace();
		}	
	}

}

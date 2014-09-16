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
import com.stek101.projectzulu.common.mobs.entity.EntityGenericCreature;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class PZPacketAnimTime implements IMessage, IMessageHandler<PZPacketAnimTime, IMessage>{

    private int entityIDtoSync;
    private int animTime;

    public PZPacketAnimTime setPacketData(int entityIDtoSync, int animTime) {
        this.entityIDtoSync = entityIDtoSync;
        this.animTime = animTime;
        return this;
    }
    
	@Override
	public IMessage onMessage(PZPacketAnimTime message, MessageContext ctx) {
		if(ctx.side != Side.CLIENT) return null;
		
		 EntityPlayer player = ProjectZulu_Core.proxy.getClientPlayer();		
		 World worldObj = ((EntityPlayer) player).worldObj;
	     Entity entity = worldObj.getEntityByID(message.entityIDtoSync);
	        if (entity != null && entity instanceof EntityGenericCreature) {
	            ((EntityGenericCreature) entity).setAnimTime(message.animTime);
	        } else {
	            // TODO Print to Log Entity No longer Exists
	        }
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
	   PacketBuffer buffer = new PacketBuffer(buf);
	    try {	
	    	
		entityIDtoSync = buffer.readInt();
        animTime = buffer.readInt();
        
	    }  catch (Exception e) {
			ProjectZuluLog.severe("There was a problem encoding the packet in PZPacketAnimTime : " + buffer + ".", this);
          e.printStackTrace();
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer buffer = new PacketBuffer(buf);
		  try {	
			  
		 buffer.writeInt(entityIDtoSync);
	     buffer.writeInt(animTime);
	     
		  }  catch (Exception e) {
				ProjectZuluLog.severe("There was a problem encoding the packet in PZPacketAnimTime : " + buffer + ".", this);
	          e.printStackTrace();
          } 
	}

}

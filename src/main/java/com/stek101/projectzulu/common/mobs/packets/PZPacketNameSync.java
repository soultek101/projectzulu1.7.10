package com.stek101.projectzulu.common.mobs.packets;

import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import cpw.mods.fml.common.SidedProxy;

import com.stek101.projectzulu.common.core.ProjectZuluLog;
import com.stek101.projectzulu.common.mobs.entity.EntityGenericTameable;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class PZPacketNameSync implements IMessage, IMessageHandler<PZPacketNameSync, IMessage> {
    private int entityIdToBeNamed;
    private String entityName;
    private String RentityName;
    
   public PZPacketNameSync setPacketData (int entityIdToBeNamed, String entityName) {
        this.entityIdToBeNamed = entityIdToBeNamed;
        this.entityName = entityName;
        return this;
    }
	
	/**
	 * Called when this message is received.
	 */
	
	@Override
	public IMessage onMessage(PZPacketNameSync message, MessageContext ctx) {
	
		if(ctx.side != Side.SERVER) return null;
		 EntityPlayer player = ctx.getServerHandler().playerEntity;		
		 Entity entity = player.worldObj.getEntityByID(message.entityIdToBeNamed);
	        if (entity != null && entity instanceof EntityGenericTameable) {        	
	        	((EntityGenericTameable) entity).setEntityTamed(message.RentityName);
	        }
	        
		return null;
	}

	/**
	 * Reads the message from bytes.
	 */
	@Override
	public void fromBytes(ByteBuf buf) {
		PacketBuffer buffer = new PacketBuffer(buf);
		
		try {
			
			  entityIdToBeNamed = buffer.readInt();
			  int nameLength = buffer.readInt();
			  RentityName = buffer.readStringFromBuffer(nameLength);		  
			
		} catch (Exception e) {
			ProjectZuluLog.severe("There was a problem decoding the packet in PZPacketNameSync : " + buffer + ".", this);
            e.printStackTrace();
		}
	}	
	
	/**
	 * Writes the message into bytes.
	 */
	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer buffer = new PacketBuffer(buf);
		try {	
			buffer.writeInt(entityIdToBeNamed);
	        buffer.writeInt(entityName.length());
	        buffer.writeStringToBuffer(entityName);	       
	        
		} catch (IOException e) {
			ProjectZuluLog.severe("There was a problem encoding the packet in PZPacketNameSync : " + buffer + ".", this);
            e.printStackTrace();
		}
		
	}

}

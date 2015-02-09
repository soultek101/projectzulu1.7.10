package com.stek101.projectzulu.common.mobs.packets;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;

import com.stek101.projectzulu.common.ProjectZulu_Core;
import com.stek101.projectzulu.common.core.ProjectZuluLog;
import com.stek101.projectzulu.common.mobs.entity.EntityCamel;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class PZPacketKeyBind implements IMessage, IMessageHandler<PZPacketKeyBind, IMessage> {
    private String keyBindCode;
	private int keyBindValue;
    
   public PZPacketKeyBind setPacketData (String keyCode, int keyValue) {
        this.keyBindCode = keyCode;
        this.keyBindValue = keyValue;
        return this;
    }
	
	/**
	 * Called when this message is received.
	 */
	
	@Override
	public IMessage onMessage(PZPacketKeyBind message, MessageContext ctx) {
		if(ctx.side != Side.SERVER) return null;
		 
        if (message.keyBindCode.equalsIgnoreCase("jump")){
        	 EntityPlayerMP player = ctx.getServerHandler().playerEntity;
	            if (player.ridingEntity != null && player.ridingEntity instanceof EntityCamel)
	            {
	                ((EntityCamel)player.ridingEntity).setMountJump();
	            }	
        }
        if (message.keyBindCode.equalsIgnoreCase("inventory")){
        	 EntityPlayerMP player = ctx.getServerHandler().playerEntity;
	            if (player.ridingEntity != null && player.ridingEntity instanceof EntityCamel)
	            {
	            	player.openGui(ProjectZulu_Core.modInstance, 5, player.worldObj, player.ridingEntity.getEntityId(), 0, 0);
	            }	
        }
        
        if (message.keyBindCode.equalsIgnoreCase("dismount")){
       	 EntityPlayerMP player = ctx.getServerHandler().playerEntity;
	            if (player.ridingEntity != null && player.ridingEntity instanceof EntityCamel)
	            {
	            	((EntityCamel)player.ridingEntity).riderDismount();
	            }	
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
			  int kBCLength = buffer.readInt();
			  this.keyBindCode = buffer.readStringFromBuffer(kBCLength);
			  this.keyBindValue = buffer.readInt();
			
		} catch (Exception e) {
			ProjectZuluLog.severe("There was a problem decoding the packet in PZPacketKeyBind : " + buffer + ".", this);
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
			buffer.writeInt(this.keyBindCode.length());
			buffer.writeStringToBuffer(this.keyBindCode);
	        buffer.writeInt(this.keyBindValue);
	        
		} catch (IOException e) {
			ProjectZuluLog.severe("There was a problem encoding the packet in PZPacketKeyBind : " + buffer + ".", this);
            e.printStackTrace();
		}
		
	}

}

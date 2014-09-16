package com.ngb.projectzulu.common.core;

import com.ngb.projectzulu.common.mobs.packets.PacketAnimTime;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public interface PZPacket extends IMessage {
    /**
     * Encode the packet data into the ByteBuf stream. Complex data sets may need specific data handlers (See
     * 
     * @link{cpw.mods.fml.common.network.ByteBuffUtils )
     * @param buffer the buffer to encode into
     */
    public abstract void toBytes(ByteBuf buffer);

    /**
     * Decode the packet data from the ByteBuf stream. Complex data sets may need specific data handlers (See
     * 
     * @link{cpw.mods.fml.common.network.ByteBuffUtils )
     * @param buffer the buffer to decode from
     */
    public abstract void fromBytes(ByteBuf buffer);

    /**
     * Handle a packet on the client side. Note this occurs after decoding has completed.
     * 
     * @param player the player reference
     */
    public abstract void handleClientSide(EntityPlayer player);

    /**
     * Handle a packet on the server side. Note this occurs after decoding has completed.
     * 
     * @param player the player reference
     */
    public abstract void handleServerSide(EntityPlayer player);
    
	public IMessage handleMessage(PZPacket message, MessageContext ctx);
}

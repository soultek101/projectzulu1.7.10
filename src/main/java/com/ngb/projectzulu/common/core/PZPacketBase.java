package com.ngb.projectzulu.common.core;

import com.ngb.projectzulu.common.mobs.packets.PacketAnimTime;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public abstract class PZPacketBase implements PZPacket {
	
	@Override
	public IMessage handleMessage(PZPacket message, MessageContext ctx) {
		if (ctx.side == Side.CLIENT) {
			message.handleClientSide(Minecraft.getMinecraft().thePlayer);
		} else if (ctx.side == Side.SERVER) {
			message.handleServerSide(ctx.getServerHandler().playerEntity);
		}
		return null;
	}
}

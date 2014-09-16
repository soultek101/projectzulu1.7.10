package com.ngb.projectzulu.common.core;

import net.minecraft.entity.player.EntityPlayerMP;

import com.ngb.projectzulu.common.core.packets.PacketPlaySound;
import com.ngb.projectzulu.common.core.packets.PacketStreamSound;
import com.ngb.projectzulu.common.core.packets.PacketTameParticle;
import com.ngb.projectzulu.common.dungeon.packets.PacketMobSpawner;
import com.ngb.projectzulu.common.mobs.packets.PacketAnimTime;
import com.ngb.projectzulu.common.mobs.packets.PacketFollowerMasterData;
import com.ngb.projectzulu.common.mobs.packets.PacketNameSync;
import com.ngb.projectzulu.common.mobs.packets.PacketTileText;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

/**
 * Packet pipeline class. Directs all registered packet data to be handled by the packets themselves.
 * 
 * Based on PacketPipeline example code by @author sirgingalot and @author cpw
 */
public class PacketPipeline {
	private SimpleNetworkWrapper network;
	private int discriminator = 0;
	
	public PacketPipeline(String channelName) {
		network = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
		registerClientMessage(PacketAnimTime.class, PacketAnimTime.class);
		registerUniversalMessage(PacketMobSpawner.class, PacketMobSpawner.class);
		registerServerMessage(PacketNameSync.class, PacketNameSync.class);
		registerClientMessage(PacketPlaySound.class, PacketPlaySound.class);
		registerClientMessage(PacketStreamSound.class, PacketStreamSound.class);
		registerServerMessage(PacketTileText.class, PacketTileText.class);
		registerClientMessage(PacketTameParticle.class, PacketTameParticle.class);
		registerClientMessage(PacketFollowerMasterData.class, PacketFollowerMasterData.class);
	}
	
	private <REQ extends IMessage, REPLY extends IMessage> void registerClientMessage( 
			Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType) {
		network.registerMessage(messageHandler, requestMessageType, discriminator, Side.CLIENT);
		discriminator++;
	}
	
	private <REQ extends IMessage, REPLY extends IMessage> void registerServerMessage( 
			Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType) {
		network.registerMessage(messageHandler, requestMessageType, discriminator, Side.SERVER);
		discriminator++;
	}
	
	private <REQ extends IMessage, REPLY extends IMessage> void registerUniversalMessage( 
			Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType) {
		network.registerMessage(messageHandler, requestMessageType, discriminator, Side.CLIENT);
		discriminator++;
		network.registerMessage(messageHandler, requestMessageType, discriminator, Side.SERVER);
		discriminator++;
	}
	
    /**
     * Send this message to everyone.
     * 
     * @param message The message to send
     */
    public void sendToAll(PZPacket message) {
    	network.sendToAll(message);
    }

    /**
     * Send this message to the specified player.
     * 
     * @param message The message to send
     * @param player The player to send it to
     */
    public void sendTo(PZPacket message, EntityPlayerMP player) {
    	network.sendTo(message, player);
    }

    /**
     * Send this message to everyone within a certain range of a point.
     * 
     * @param message The message to send
     * @param point The {@link cpw.mods.fml.common.network.NetworkRegistry.TargetPoint} around which to send
     */
    public void sendToAllAround(PZPacket message, NetworkRegistry.TargetPoint point) {
    	network.sendToAllAround(message, point);
    }

    /**
     * Send this message to everyone within the supplied dimension.
     * 
     * @param message The message to send
     * @param dimensionId The dimension id to target
     */
    public void sendToDimension(PZPacket message, int dimensionId) {
    	network.sendToDimension(message, dimensionId);
    }

    /**
     * Send this message to the server.
     * 
     * @param message The message to send
     */
    public void sendToServer(PZPacket message) {
    	network.sendToServer(message);
    }

}
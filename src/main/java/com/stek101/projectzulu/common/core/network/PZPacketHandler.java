package com.stek101.projectzulu.common.core.network;

import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.core.packets.PZPacketPlaySound;
import com.stek101.projectzulu.common.core.packets.PZPacketStreamSound;
import com.stek101.projectzulu.common.core.packets.PZPacketTameParticle;
import com.stek101.projectzulu.common.dungeon.packets.PZPacketMobSpawner;
import com.stek101.projectzulu.common.mobs.packets.PZPacketAnimTime;
import com.stek101.projectzulu.common.mobs.packets.PZPacketFollowerMasterData;
import com.stek101.projectzulu.common.mobs.packets.PZPacketNameSync;
import com.stek101.projectzulu.common.mobs.packets.PZPacketTileText;

import net.minecraft.entity.player.EntityPlayerMP;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;


// ==================================================
// Code adapted from Lycanites Mobs mod, PacketHandler.java
// ==================================================

public class PZPacketHandler {
	
	public final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(DefaultProps.CoreModId);
    
	    // ==================================================
		//                    Initialize
		// ==================================================
		/**
		 * Initializes the Packet Handler where Messages are registered.
		 */
		public void init() {
			int messageID = 0;
			
			// Server to Client:
			
			this.network.registerMessage(PZPacketPlaySound.class, PZPacketPlaySound.class, messageID++, Side.CLIENT);
			this.network.registerMessage(PZPacketStreamSound.class, PZPacketStreamSound.class, messageID++, Side.CLIENT);
			this.network.registerMessage(PZPacketTameParticle.class, PZPacketTameParticle.class, messageID++, Side.CLIENT);
			this.network.registerMessage(PZPacketAnimTime.class, PZPacketAnimTime.class, messageID++, Side.CLIENT);
			this.network.registerMessage(PZPacketFollowerMasterData.class, PZPacketFollowerMasterData.class, messageID++, Side.CLIENT);
			this.network.registerMessage(PZPacketMobSpawner.class, PZPacketMobSpawner.class, messageID++, Side.CLIENT);
			
			// Client to Server:
			 
			this.network.registerMessage(PZPacketNameSync.class, PZPacketNameSync.class, messageID++, Side.SERVER);
			this.network.registerMessage(PZPacketTileText.class, PZPacketTileText.class, messageID++, Side.SERVER);
			this.network.registerMessage(PZPacketMobSpawner.class, PZPacketMobSpawner.class, messageID++, Side.SERVER);
		
		}
	
		// ==================================================
		//                    Send To All
		// ==================================================
		/**
		 * Sends a packet from the server to all players.
		 * @param packet
		 */
		public void sendToAll(IMessage message) {
			this.network.sendToAll(message);
		}
		
		
		// ==================================================
		//                   Send To Player
		// ==================================================
		/**
		 * Sends a packet from the server to the specified player.
		 * @param packet
		 * @param player
		 */
		public void sendToPlayer(IMessage message, EntityPlayerMP player) {
			this.network.sendTo(message, player);
		}		

		// ==================================================
		//                 Send To All Around
		// ==================================================
		/**
		 * Sends a packet from the server to all players near the specified target point.
		 * @param packet
		 * @param point
		 */
		public void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint point) {
			this.network.sendToAllAround(message, point);
		}
		
		
		// ==================================================
		//                 Send To Dimension
		// ==================================================
		/**
		 * Sends a packet to all players within the specified dimension.
		 * @param packet
		 * @param dimensionID The ID of the dimension to use.
		 */
		public void sendToDimension(IMessage message, int dimensionID) {
			this.network.sendToDimension(message, dimensionID);
		}
		
		
		// ==================================================
		//                   Send To Server
		// ==================================================
		/**
		 * Sends a packet from the client player to the server.
		 * @param packet
		 */
		public void sendToServer(IMessage message) {
			this.network.sendToServer(message);
		}
	
}
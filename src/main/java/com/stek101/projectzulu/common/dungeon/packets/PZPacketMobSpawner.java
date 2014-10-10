package com.stek101.projectzulu.common.dungeon.packets;

import io.netty.buffer.ByteBuf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.stek101.projectzulu.common.ProjectZulu_Core;
import com.stek101.projectzulu.common.dungeon.TileEntityLimitedMobSpawner;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class PZPacketMobSpawner implements IMessage, IMessageHandler<PZPacketMobSpawner, IMessage>{
	int entityIDtoSync;
    private int posX;
    private int posY;
    private int posZ;
    private NBTTagCompound customData;

    public PZPacketMobSpawner setPacketData(int xPos, int yPos, int zPos, NBTTagCompound customData) {
        this.posX = xPos;
        this.posY = yPos;
        this.posZ = zPos;
        this.customData = customData;
        return this;
    }
    
	@Override
	public IMessage onMessage(PZPacketMobSpawner message, MessageContext ctx) {
		
		if(ctx.side != Side.SERVER) {
			
			EntityPlayer player = ProjectZulu_Core.proxy.getClientPlayer();
			World world = player.getEntityWorld();
			TileEntity tileEntity = world.getTileEntity(message.posX, message.posY, message.posZ);
			if (tileEntity != null && tileEntity instanceof TileEntityLimitedMobSpawner) {
				tileEntity.readFromNBT(message.customData);
				((TileEntityLimitedMobSpawner) tileEntity).forceUpdate();
				
			 }
		}
		else if (ctx.side != Side.CLIENT) {
			
			 EntityPlayer player = ctx.getServerHandler().playerEntity;			 
			 World world = player.getEntityWorld();
		        TileEntity tileEntity = world.getTileEntity(message.posX, message.posY, message.posZ);
		        if (tileEntity != null && tileEntity instanceof TileEntityLimitedMobSpawner) {
		            tileEntity.readFromNBT(message.customData);
		            ((TileEntityLimitedMobSpawner) tileEntity).forceUpdate();
		        }			
		}	
		return null;
	}

//	@Override
/*	public void fromBytes(ByteBuf buf) {
	  PacketBuffer buffer = new PacketBuffer(buf);
	   try {		
		   
        posX = buffer.readInt();
        posY = buffer.readInt();
        posZ = buffer.readInt();
        customData = readNBTTagCompound(decodeInto(buffer));
        
	   } catch (Exception e) {
			ProjectZuluLog.severe("There was a problem decoding the packet in PZPacketMobSpawner : " + buffer + ".", this);
         e.printStackTrace();
		}
	}*/


//	@Override
/*	public void toBytes(ByteBuf buf) {
      PacketBuffer buffer = new PacketBuffer(buf);
		try {		
			
			buffer.writeInt(posX);
			buffer.writeInt(posY);
			buffer.writeInt(posZ);
			writeNBTTagCompound(customData, encodeInto(buffer));
			
		}  catch (IOException e) {
			ProjectZuluLog.severe("There was a problem encoding the packet in PZPacketMobSpawner : " + buffer + ".", this);
          e.printStackTrace();
		}
	}*/
	@Override
    public void toBytes(ByteBuf buffer) {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(byteArray);
        try {
            writeData(data);
        } catch (Exception e) {
            // TODO: log exception
        }
        byte[] bytes = byteArray.toByteArray();
        buffer.writeInt(bytes.length);
        buffer.writeBytes(bytes);
    }
	
	 protected void writeData(DataOutputStream buffer) throws IOException {
	        buffer.writeInt(posX);
	        buffer.writeInt(posY);
	        buffer.writeInt(posZ);
	        writeNBTTagCompound(customData, buffer);
	    }
	 
	    @Override
	    public void fromBytes(ByteBuf buffer) {
	        int byteLength = buffer.readInt();
	        byte[] dataBytes = new byte[byteLength];
	        buffer.readBytes(dataBytes);

	        ByteArrayInputStream byteArray = new ByteArrayInputStream(dataBytes);
	        DataInputStream data = new DataInputStream(byteArray);
	        try {
	            readData(data);
	        } catch (Exception e) {
	            // TODO: log exception
	        }
	    }
	 
	    protected void readData(DataInputStream buffer) throws IOException {
	        posX = buffer.readInt();
	        posY = buffer.readInt();
	        posZ = buffer.readInt();
	        customData = readNBTTagCompound(buffer);
	    }

	    
	
	public DataInputStream decodeInto( ByteBuf buffer) {
        int byteLength = buffer.readInt();
        byte[] dataBytes = new byte[byteLength];
        buffer.readBytes(dataBytes);

        ByteArrayInputStream byteArray = new ByteArrayInputStream(dataBytes);
        DataInputStream data = new DataInputStream(byteArray);
        
		return data;
    }
	
	public DataOutputStream encodeInto(ByteBuf buffer) {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(byteArray);
        
        byte[] bytes = byteArray.toByteArray();
        buffer.writeInt(bytes.length);
        buffer.writeBytes(bytes);
		return data;
    }
	
	/**
     * Reads a compressed NBTTagCompound from the InputStream
     */
    protected static NBTTagCompound readNBTTagCompound(DataInputStream par0DataInputStream) throws IOException {
     	short length = par0DataInputStream.readShort();
    	if (length < 0)
    		return null;
    	else {
    		byte[] compressed = new byte[length];
    		par0DataInputStream.readFully(compressed);
    	return CompressedStreamTools.readCompressed(new ByteArrayInputStream(compressed));
    	}
    }
    
    /**
     * Writes a compressed NBTTagCompound to the OutputStream
     */
    protected static void writeNBTTagCompound(NBTTagCompound par0NBTTagCompound, DataOutputStream par1DataOutputStream)
            throws IOException {
        if (par0NBTTagCompound == null) {
            par1DataOutputStream.writeShort(-1);
        } else {
            byte[] var2 = CompressedStreamTools.compress(par0NBTTagCompound);
            par1DataOutputStream.writeShort((short) var2.length);
            par1DataOutputStream.write(var2);
        }
    }

}

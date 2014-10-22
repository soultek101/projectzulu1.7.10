package com.stek101.projectzulu.common.blocks.spike;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySpikes extends TileEntity{
	private int spikeMaxDmg = 0;
	private int spikeCurDmg = 0;
	private static final String NBT_SPIKE_MAX_DMG = "SpikeMaxDamage";
	private static final String NBT_SPIKE_CUR_DMG = "SpikeCurrDamage";
	private NBTTagCompound nbtag = new NBTTagCompound();
	
	public TileEntitySpikes(int maxDamage){
		this.spikeMaxDmg = maxDamage;
	}
	
	public TileEntitySpikes(){

	}
	
	public void setMaxDmg(int maxDmg){
		this.spikeMaxDmg = maxDmg;
	}
	
	public int getMaxDmg(){
		return this.spikeMaxDmg;
	}
	
	public void setDamage(int damage){
		this.spikeCurDmg = this.spikeCurDmg + damage;		
	}
	
	public int getCurrDamage(){
		return this.spikeCurDmg;
	}
	
	public void setCurrDamage(int currDmg){
		this.spikeCurDmg = currDmg;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		this.spikeMaxDmg = nbt.getInteger(NBT_SPIKE_MAX_DMG);
		this.spikeCurDmg = nbt.getInteger(NBT_SPIKE_CUR_DMG);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setInteger(NBT_SPIKE_MAX_DMG, spikeMaxDmg);
		nbt.setInteger(NBT_SPIKE_CUR_DMG, spikeCurDmg);
	}

	@Override
	public Packet getDescriptionPacket(){
		NBTTagCompound tileTag = new NBTTagCompound();
		this.writeToNBT(tileTag);
		//return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, tileTag);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 4, tileTag);
	}
	
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound tag = pkt.func_148857_g();
        readFromNBT(tag);
    }
	
}

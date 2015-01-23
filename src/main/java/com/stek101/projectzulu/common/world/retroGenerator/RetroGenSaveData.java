package com.stek101.projectzulu.common.world.retroGenerator;

import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

import com.google.common.collect.Maps;

public class RetroGenSaveData extends WorldSavedData {
	private Map<ChunkCoord, NBTTagCompound> chunks = Maps.newHashMap();

	private final String NBT_SIZE = "size";
	private final String NBT_LOC_X = "CoordLocX";
	private final String NBT_LOC_Z = "CoordLocZ";
	private final String NBT_TAG = "tag";

	public RetroGenSaveData(String name) {
		super(name);
	}

	public boolean isGenerationNeeded(ChunkCoord coord, String genID) {
		NBTTagCompound nbt = chunks.get(coord);

		return nbt == null || !nbt.hasKey(genID) || !nbt.getBoolean(genID);
	}

	public void markChunkRetroGenerated(ChunkCoord coord, String genID) {
		NBTTagCompound nbt = chunks.get(coord);

		if (nbt == null)
			nbt = new NBTTagCompound();

		nbt.setBoolean(genID, true);
		chunks.put(coord, nbt);

		markDirty();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		int size = nbt.getInteger(NBT_SIZE);

		for (int i = 0; i < size; ++i) {
			ChunkCoord coord = new ChunkCoord(nbt.getInteger(i + NBT_LOC_X), nbt.getInteger(i + NBT_LOC_Z));
			chunks.put(coord, nbt.getCompoundTag(i + NBT_TAG));
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger(NBT_SIZE, chunks.size());
		int index = 0;

		for (ChunkCoord coord : chunks.keySet()) {
			nbt.setInteger(index + NBT_LOC_X, coord.chunkX);
			nbt.setInteger(index + NBT_LOC_Z, coord.chunkZ);
			nbt.setTag(index + NBT_TAG, chunks.get(coord));
			index++;
		}
	}
}
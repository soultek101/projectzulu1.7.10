package com.stek101.projectzulu.common.world.retroGenerator;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public interface IRetroGenerator
{
	public String getUniqueGenerationID();

	public boolean canGenerateIn(World world, Chunk chunk, Random random);

	public void generate(Random rand, World world, int chunkX, int chunkZ);
}

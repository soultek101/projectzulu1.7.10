package com.stek101.projectzulu.common.blocks;

import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;


public class BlockZuluWoodFence extends BlockFence {
	
    public BlockZuluWoodFence(String name, Material mat) {
        super(name, mat);
        setLightOpacity(0);
    }
}

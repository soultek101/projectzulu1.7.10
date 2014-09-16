package com.stek101.projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import com.stek101.projectzulu.common.api.BlockList;
import com.stek101.projectzulu.common.blocks.BlockPalmTreeSapling;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class PalmTreeSapling extends BlockDeclaration {

    public PalmTreeSapling() {
        super("PalmTreeSapling");
    }

    @Override
    protected boolean createBlock() {
        BlockList.palmTreeSapling = Optional.of(new BlockPalmTreeSapling().setBlockName(name.toLowerCase())
                .setBlockTextureName(DefaultProps.blockKey + ":" + name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerBlock() {
        Block block = BlockList.palmTreeSapling.get();
        GameRegistry.registerBlock(block, name.toLowerCase());
        OreDictionary.registerOre("sapling", new ItemStack(block));
        OreDictionary.registerOre("saplingPalm", new ItemStack(block));
    }
}

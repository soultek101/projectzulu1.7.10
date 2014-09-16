package com.stek101.projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import com.stek101.projectzulu.common.api.BlockList;
import com.stek101.projectzulu.common.blocks.BlockZuluSlab;
import com.stek101.projectzulu.common.blocks.ItemZuluSlab;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class PalmTreeSlabDeclaration extends BlockDeclaration {

    public PalmTreeSlabDeclaration() {
        super("PalmTreeSlab", 1);
    }

    @Override
    protected boolean createBlock() {
        if (BlockList.palmTreePlank.isPresent()) {
            BlockList.palmTreeSlab = Optional.of((new BlockZuluSlab(BlockList.palmTreePlank.get())).setBlockName(
                    name.toLowerCase()).setBlockTextureName(DefaultProps.blockKey + ":" + name.toLowerCase()));
            return true;
        }
        return false;
    }

    @Override
    protected void registerBlock() {
        if (BlockList.palmTreeDoubleSlab.isPresent() && BlockList.palmTreeSlab.isPresent()) {
            Block block = BlockList.palmTreeSlab.get();
            ItemZuluSlab.initialise((BlockSlab) BlockList.palmTreeSlab.get(),
                    (BlockSlab) BlockList.palmTreeDoubleSlab.get());
            GameRegistry.registerBlock(block, ItemZuluSlab.class, name.toLowerCase());
            OreDictionary.registerOre("slabWood", new ItemStack(block));
            OreDictionary.registerOre("slabPalm", new ItemStack(block));
            Blocks.fire.setFireInfo(block, 5, 20);
        }
    }
}

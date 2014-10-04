package com.stek101.projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.base.Optional;
import com.stek101.projectzulu.common.api.BlockList;
import com.stek101.projectzulu.common.blocks.BlockPalmTreeLog;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import cpw.mods.fml.common.registry.GameRegistry;

public class PalmTreeLogDeclaration extends BlockDeclaration {

    public PalmTreeLogDeclaration() {
        super("PalmTreeLog");
    }

    @Override
    protected boolean createBlock() {
        BlockList.palmTreeLog = Optional.of(new BlockPalmTreeLog().setBlockName(name.toLowerCase())
                .setBlockTextureName(DefaultProps.blockKey + ":" + name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerBlock() {
        Block block = BlockList.palmTreeLog.get();
        GameRegistry.registerBlock(block, name.toLowerCase());
        OreDictionary.registerOre("log", new ItemStack(block));
        OreDictionary.registerOre("logWood", new ItemStack(block));
        OreDictionary.registerOre("logPalm", new ItemStack(block));
        Blocks.fire.setFireInfo(block, 5, 20);
    }

}

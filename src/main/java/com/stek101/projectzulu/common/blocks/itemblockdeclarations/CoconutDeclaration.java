package com.stek101.projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;

import com.google.common.base.Optional;
import com.stek101.projectzulu.common.api.BlockList;
import com.stek101.projectzulu.common.blocks.BlockCoconut;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import cpw.mods.fml.common.registry.GameRegistry;

public class CoconutDeclaration extends BlockDeclaration {

    public CoconutDeclaration() {
        super("coconut");
    }

    @Override
    protected boolean createBlock() {
        BlockList.coconut = Optional.of(new BlockCoconut().setBlockName(name.toLowerCase()).setBlockTextureName(
                DefaultProps.blockKey + ":" + name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerBlock() {
        Block block = BlockList.coconut.get();
        GameRegistry.registerBlock(block, name.toLowerCase());
    }
}

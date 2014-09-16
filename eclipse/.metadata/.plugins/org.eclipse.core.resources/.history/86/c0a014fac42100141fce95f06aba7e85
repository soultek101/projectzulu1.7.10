package com.ngb.projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import com.ngb.projectzulu.common.api.BlockList;
import com.ngb.projectzulu.common.blocks.BlockCoconut;
import com.ngb.projectzulu.common.core.DefaultProps;
import com.ngb.projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

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

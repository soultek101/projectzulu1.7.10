package com.stek101.projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;

import com.google.common.base.Optional;
import com.stek101.projectzulu.common.api.BlockList;
import com.stek101.projectzulu.common.blocks.BlockAloeVera;
import com.stek101.projectzulu.common.blocks.ItemAloeVera;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import cpw.mods.fml.common.registry.GameRegistry;

public class AloeVeraDeclaration extends BlockDeclaration {

    public AloeVeraDeclaration() {
        super("AloeVera");
    }

    @Override
    protected boolean createBlock() {
        BlockList.aloeVera = Optional.of((new BlockAloeVera()).setBlockName(name.toLowerCase()).setBlockTextureName(
                DefaultProps.blockKey + ":" + name.toLowerCase()));
        return true;
    }

    @Override
    public void registerBlock() {
        Block block = BlockList.aloeVera.get();
        GameRegistry.registerBlock(block, ItemAloeVera.class, name.toLowerCase());
    }
}
package com.stek101.projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;

import com.google.common.base.Optional;
import com.stek101.projectzulu.common.api.BlockList;
import com.stek101.projectzulu.common.blocks.BlockWateredDirt;
import com.stek101.projectzulu.common.blocks.ItemWateredDirt;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import cpw.mods.fml.common.registry.GameRegistry;

public class WateredDirtDeclaration extends BlockDeclaration {

    public WateredDirtDeclaration() {
        super("WateredDirt");
    }

    @Override
    protected boolean createBlock() {
        BlockList.wateredDirt = Optional.of((new BlockWateredDirt()).setBlockName(name.toLowerCase())
                .setBlockTextureName(DefaultProps.blockKey + ":" + name.toLowerCase()));
        return true;
    }

    @Override
    public void registerBlock() {
        Block block = BlockList.wateredDirt.get();
        GameRegistry.registerBlock(block, ItemWateredDirt.class, name.toLowerCase());
    }
}

package com.ngb.projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import com.ngb.projectzulu.common.api.BlockList;
import com.ngb.projectzulu.common.blocks.BlockWateredDirt;
import com.ngb.projectzulu.common.blocks.ItemWateredDirt;
import com.ngb.projectzulu.common.core.DefaultProps;
import com.ngb.projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

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

package com.ngb.projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import com.ngb.projectzulu.common.api.BlockList;
import com.ngb.projectzulu.common.blocks.BlockJasper;
import com.ngb.projectzulu.common.core.DefaultProps;
import com.ngb.projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class JasperDeclaration extends BlockDeclaration {

    public JasperDeclaration() {
        super("Jasper");
    }

    @Override
    protected boolean createBlock() {
        BlockList.jasper = Optional.of((new BlockJasper()).setBlockName(name.toLowerCase()).setBlockTextureName(
                DefaultProps.blockKey + ":" + name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerBlock() {
        Block block = BlockList.jasper.get();
        GameRegistry.registerBlock(block, name.toLowerCase());
    }
}

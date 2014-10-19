package com.stek101.projectzulu.common.blocks.itemblockdeclarations;

import com.google.common.base.Optional;
import com.stek101.projectzulu.common.api.ItemList;
import com.stek101.projectzulu.common.blocks.ItemStructurePlacer;
import com.stek101.projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import cpw.mods.fml.common.registry.GameRegistry;

public class StructurePlacerDeclaration extends ItemDeclaration {

    public StructurePlacerDeclaration() {
        super("StructurePlacer");
    }

    @Override
    protected boolean createItem() {
        ItemList.structurePlacer = Optional.of(new ItemStructurePlacer(name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
        GameRegistry.registerItem(ItemList.structurePlacer.get(), name);
    }
}

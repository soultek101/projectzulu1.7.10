package com.stek101.projectzulu.common.blocks.itemblockdeclarations;

import com.stek101.projectzulu.common.api.ItemList;
import com.stek101.projectzulu.common.blocks.ItemCoconutShell;
import com.stek101.projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class CoconutShellDeclaration extends ItemDeclaration {

    public CoconutShellDeclaration() {
        super("CoconutShell");
    }

    @Override
    protected boolean createItem() {
        ItemList.coconutShell = Optional.of(new ItemCoconutShell(false, name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
        GameRegistry.registerItem(ItemList.coconutShell.get(), name);
    }
}

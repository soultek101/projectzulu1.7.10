package com.stek101.projectzulu.common.blocks.itemblockdeclarations;

import com.google.common.base.Optional;
import com.stek101.projectzulu.common.api.ItemList;
import com.stek101.projectzulu.common.blocks.ItemCoconutShellFuel;
import com.stek101.projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import cpw.mods.fml.common.registry.GameRegistry;

public class CoconutShellFuelDeclaration extends ItemDeclaration {

    public CoconutShellFuelDeclaration() {
        super("CoconutShellFuel");
    }

    @Override
    protected boolean createItem() {
        ItemList.coconutShellFuel = Optional.of(new ItemCoconutShellFuel(false, name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
        GameRegistry.registerItem(ItemList.coconutShellFuel.get(), name);
    }
}

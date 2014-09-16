package com.stek101.projectzulu.common.blocks.itemblockdeclarations;

import com.stek101.projectzulu.common.api.ItemList;
import com.stek101.projectzulu.common.blocks.ItemFoodProjectZulu;
import com.stek101.projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class CoconutMilkFragmentDeclaration extends ItemDeclaration {

    public CoconutMilkFragmentDeclaration() {
        super("CoconutMilkFragment");
    }

    @Override
    protected boolean createItem() {
        ItemList.coconutMilkFragment = Optional.of(new ItemFoodProjectZulu(2, 2.4f, false, name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
        GameRegistry.registerItem(ItemList.coconutMilkFragment.get(), name);
    }
}

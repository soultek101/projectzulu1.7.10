package com.stek101.projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import com.stek101.projectzulu.common.api.ItemList;
import com.stek101.projectzulu.common.blocks.ItemFoodProjectZulu;
import com.stek101.projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class CookedMuttonDeclaration extends ItemDeclaration {

    public CookedMuttonDeclaration() {
        super("MuttonCooked");
    }

    @Override
    protected boolean createItem() {
        ItemList.muttonCooked = Optional.of(new ItemFoodProjectZulu(6, 9.6f, true, name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.muttonCooked.get();
        GameRegistry.registerItem(item, name);
    }
}

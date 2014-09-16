package com.stek101.projectzulu.common.blocks;

import net.minecraft.item.Item;
import com.stek101.projectzulu.common.api.ItemList;
import com.stek101.projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class FurPeltDeclaration extends ItemDeclaration {

    public FurPeltDeclaration() {
        super("FurPelt");
    }

    @Override
    protected boolean createItem() {
        ItemList.furPelt = Optional.of(new ItemScale(false, name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.furPelt.get();
        GameRegistry.registerItem(item, name);
    }
}

package com.ngb.projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import com.ngb.projectzulu.common.api.ItemList;
import com.ngb.projectzulu.common.blocks.ItemScale;
import com.ngb.projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class ScaleItemDeclaration extends ItemDeclaration {

    public ScaleItemDeclaration() {
        super("ScaleItem");
    }

    @Override
    protected boolean createItem() {
        ItemList.scaleItem = Optional.of(new ItemScale(false, name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.scaleItem.get();
        GameRegistry.registerItem(item, name);
    }
}
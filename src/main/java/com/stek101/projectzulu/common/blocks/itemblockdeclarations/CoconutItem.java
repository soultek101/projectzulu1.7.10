package com.stek101.projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;

import com.google.common.base.Optional;
import com.stek101.projectzulu.common.api.ItemList;
import com.stek101.projectzulu.common.blocks.ItemCoconutItem;
import com.stek101.projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import cpw.mods.fml.common.registry.GameRegistry;

public class CoconutItem extends ItemDeclaration {

    public CoconutItem() {
        super("CoconutItem");
    }

    @Override
    protected boolean createItem() {
        ItemList.coconutItem = Optional.of(new ItemCoconutItem(false, name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.coconutItem.get();
        GameRegistry.registerItem(item, name);
    }
}

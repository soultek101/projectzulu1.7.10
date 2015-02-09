package com.stek101.projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;

import com.google.common.base.Optional;
import com.stek101.projectzulu.common.api.ItemList;
import com.stek101.projectzulu.common.blocks.ItemThrowingRock;
import com.stek101.projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import cpw.mods.fml.common.registry.GameRegistry;

public class ThrowingRockDeclaration extends ItemDeclaration {

    public ThrowingRockDeclaration() {
        super("ThrowingRock");
    }

    @Override
    protected boolean createItem() {
        ItemList.throwingRock= Optional.of(new ItemThrowingRock(false, name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.throwingRock.get();
        GameRegistry.registerItem(item, name);
    }
}

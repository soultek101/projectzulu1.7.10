package com.stek101.projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;

import com.google.common.base.Optional;
import com.stek101.projectzulu.common.api.ItemList;
import com.stek101.projectzulu.common.blocks.ItemCoconutSeed;
import com.stek101.projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import cpw.mods.fml.common.registry.GameRegistry;

public class CoconutSeedDeclaration extends ItemDeclaration {

    public CoconutSeedDeclaration() {
        super("CoconutSeed");
    }

    @Override
    protected boolean createItem() {
        ItemList.coconutSeed = Optional.of(new ItemCoconutSeed(6, false, name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.coconutSeed.get();
        GameRegistry.registerItem(item, name);
    }
}

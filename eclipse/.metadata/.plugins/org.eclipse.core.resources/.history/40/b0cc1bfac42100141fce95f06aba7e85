package com.ngb.projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import com.ngb.projectzulu.common.api.BlockList;
import com.ngb.projectzulu.common.api.ItemList;
import com.ngb.projectzulu.common.blocks.ItemAloeVeraSeeds;
import com.ngb.projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class AloeVeraSeedsDeclaration extends ItemDeclaration {

    public AloeVeraSeedsDeclaration() {
        super("AloeVeraSeeds", 1);
    }

    @Override
    protected boolean createItem() {
        if (BlockList.aloeVera.isPresent()) {
            ItemList.aloeVeraSeeds = Optional.of(new ItemAloeVeraSeeds(BlockList.aloeVera.get(), name.toLowerCase()));
            return true;
        }
        return false;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.aloeVeraSeeds.get();
        GameRegistry.registerItem(item, name);
    }
}

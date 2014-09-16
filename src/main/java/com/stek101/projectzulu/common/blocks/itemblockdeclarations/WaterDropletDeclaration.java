package com.stek101.projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import com.stek101.projectzulu.common.api.BlockList;
import com.stek101.projectzulu.common.api.ItemList;
import com.stek101.projectzulu.common.blocks.ItemFoodProjectZulu;
import com.stek101.projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class WaterDropletDeclaration extends ItemDeclaration {

    public WaterDropletDeclaration() {
        super("WaterDroplet");
    }

    @Override
    protected boolean createItem() {
        ItemList.waterDroplets = Optional.of(new ItemFoodProjectZulu(1, 0.6f, false, name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
        GameRegistry.registerItem(ItemList.waterDroplets.get(), name);
    }
}

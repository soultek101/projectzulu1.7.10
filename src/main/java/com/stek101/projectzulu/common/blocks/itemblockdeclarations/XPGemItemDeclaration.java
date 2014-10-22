package com.stek101.projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;

import com.google.common.base.Optional;
import com.stek101.projectzulu.common.api.ItemList;
import com.stek101.projectzulu.common.blocks.ItemXPGem;
import com.stek101.projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import cpw.mods.fml.common.registry.GameRegistry;

public class XPGemItemDeclaration extends ItemDeclaration {

    public XPGemItemDeclaration() {
        super("XPGem");
    }

    @Override
    protected boolean createItem() {    	  	
        ItemList.xpGem = Optional.of(new ItemXPGem(false, name.toLowerCase(), 0));
        return true;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.xpGem.get();
        GameRegistry.registerItem(item, name);
    }
}
package com.stek101.projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.config.Configuration;

import com.google.common.base.Optional;
import com.stek101.projectzulu.common.api.ItemList;
import com.stek101.projectzulu.common.blocks.ItemHammerTool;
import com.stek101.projectzulu.common.core.itemblockdeclaration.ItemSetDeclaration;

import cpw.mods.fml.common.registry.GameRegistry;

public class HammerToolDeclaration extends ItemSetDeclaration {

    public final int renderIndex;
    private boolean canHarvestBlocks;

    public HammerToolDeclaration(int renderIndex) {
        super(new String[] { "HammerToolWood", "HammerToolStone", "HammerToolIron",
        		"HammerToolGold", "HammerToolDiamond"});
        this.renderIndex = renderIndex;
    }
    
    @Override
    protected void preCreateLoadConfig(Configuration config) {
        canHarvestBlocks = config.get("Misc Game Settings", "PZ Hammer option canHarvest", this.canHarvestBlocks).getBoolean(canHarvestBlocks);
    }
    
    @Override
    protected boolean createItem(int partIndex) {
        //Item item = new ItemZuluArmor(ProjectZulu_Core.desertClothMaterial, renderIndex, partIndex,
        //        name[partIndex].toLowerCase());

        switch (partIndex) {
        case 0:
            ItemList.hammerToolWood = Optional.of(new ItemHammerTool(name[partIndex].toLowerCase(), ToolMaterial.WOOD, this.canHarvestBlocks));
            return true;
        case 1:
            ItemList.hammerToolStone = Optional.of(new ItemHammerTool(name[partIndex].toLowerCase(), ToolMaterial.STONE, this.canHarvestBlocks));
            return true;
        case 2:
            ItemList.hammerToolIron = Optional.of(new ItemHammerTool(name[partIndex].toLowerCase(), ToolMaterial.IRON, this.canHarvestBlocks));
            return true;
        case 3:
            ItemList.hammerToolGold = Optional.of(new ItemHammerTool(name[partIndex].toLowerCase(), ToolMaterial.GOLD, this.canHarvestBlocks));
            return true;
        case 4:
            ItemList.hammerToolDiamond = Optional.of(new ItemHammerTool(name[partIndex].toLowerCase(), ToolMaterial.EMERALD, this.canHarvestBlocks));
            return true;
        }
        return false;
    }
    

    @Override
    protected void registerItem(int partIndex) {
        Item item = null;
        switch (partIndex) {
        case 0:
            item = ItemList.hammerToolWood.get();
            break;
        case 1:
            item = ItemList.hammerToolStone.get();
            break;
        case 2:
            item = ItemList.hammerToolIron.get();
            break;
        case 3:
            item = ItemList.hammerToolGold.get();
            break;
        case 4:
            item = ItemList.hammerToolDiamond.get();
            break;
        }
        GameRegistry.registerItem(item, name[partIndex]);
    }

    //@Override
    //protected boolean createItem() {
    //    ItemList.hammerTool = Optional.of(new ItemHammerTool(name, ToolMaterial.WOOD));
    //    return true;
   // }

    //@Override
    //protected void registerItem() {
    //    GameRegistry.registerItem(ItemList.hammerTool.get(), name);
    //}

}

package com.stek101.projectzulu.common.blocks;

import net.minecraft.item.Item;

import com.stek101.projectzulu.common.ProjectZulu_Core;

public class ItemCoconutMilk extends Item {

    public ItemCoconutMilk(int par2, boolean par3bool) {
        super();
        maxStackSize = 12;
        setMaxDamage(5);
        this.setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        bFull3D = par3bool;
    }

    public int getMetadata(int par1) {
        return par1;
    }

}

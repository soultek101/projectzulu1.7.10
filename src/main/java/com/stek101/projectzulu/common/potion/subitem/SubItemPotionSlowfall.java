package com.stek101.projectzulu.common.potion.subitem;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

import com.google.common.base.Optional;
import com.stek101.projectzulu.common.api.PotionList;

public class SubItemPotionSlowfall extends SubItemPotionGeneric {

    public SubItemPotionSlowfall(Item itemID, int subID) {
        super(itemID, subID, "potion.slowfall");
        setSubItemBounds(4, 4, 4, 0);
        setEffectScale(20 * 20, 20 * 5, 14, 10, 2);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return PotionList.slowfall;
    }
}
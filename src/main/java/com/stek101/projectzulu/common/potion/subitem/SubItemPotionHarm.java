package com.stek101.projectzulu.common.potion.subitem;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

import com.google.common.base.Optional;
import com.stek101.projectzulu.common.api.PotionList;

public class SubItemPotionHarm extends SubItemPotionGeneric {

    public SubItemPotionHarm(Item itemID, int subID) {
        super(itemID, subID, "potion.harm2");
        setSubItemBounds(4, 1, 4, 0);
        setEffectScale(20 * 10, 20 * 5, 16, 10, 2);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return PotionList.harm2;
    }
}
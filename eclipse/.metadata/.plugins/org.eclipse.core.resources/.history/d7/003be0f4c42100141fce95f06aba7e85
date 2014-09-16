package com.ngb.projectzulu.common.potion.subitem;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import com.ngb.projectzulu.common.api.ItemList;
import com.ngb.projectzulu.common.api.PotionList;
import com.ngb.projectzulu.common.api.SubItemPotionList;
import com.ngb.projectzulu.common.core.ItemGenerics.Properties;
import com.ngb.projectzulu.common.potion.PotionParser;

import com.google.common.base.Optional;

public class SubItemPotionCleansing extends SubItemPotionGeneric {

    public SubItemPotionCleansing(Item itemID, int subID) {
        super(itemID, subID, "potion.cleansing");
        setSubItemBounds(4, 4, 4, 0);
        setEffectScale(20 * 20, 20 * 5, 6, 10, 2);
    }

    @Override
    Optional<? extends Potion> getPotion() {
        return PotionList.cleansing;
    }

    @Override
    protected TYPE getIngredientType(ItemStack ingredient, ItemStack brewingStack) {
        if (ItemList.genericCraftingItems.isPresent() && ingredient.getItem() == ItemList.genericCraftingItems.get()
                && ingredient.getItemDamage() == Properties.GlowingGoo.meta) {
            return TYPE.CHEMICAL;
        } else {
            return super.getIngredientType(ingredient, brewingStack);
        }
    }

    @Override
    protected ItemStack getChemicalPotionResult(ItemStack ingredient, ItemStack brewingStack) {
        if (SubItemPotionList.CURSE.isPresent()) {
            SubItemPotion subItemPotion = SubItemPotionList.CURSE.get();
            return new ItemStack(subItemPotion.item, 1, PotionParser.setID(subItemPotion.subID,
                    brewingStack.getItemDamage()));
        }
        return null;
    }
}
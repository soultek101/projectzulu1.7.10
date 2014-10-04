package com.stek101.projectzulu.common.potion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.item.Item;

import com.google.common.base.Optional;
import com.stek101.projectzulu.common.api.ItemList;
import com.stek101.projectzulu.common.api.SubItemPotionList;
import com.stek101.projectzulu.common.core.itemblockdeclaration.ItemDeclaration;
import com.stek101.projectzulu.common.potion.subitem.SubItemPotion;
import com.stek101.projectzulu.common.potion.subitem.SubItemPotionDamageBoost;
import com.stek101.projectzulu.common.potion.subitem.SubItemPotionFireResistance;
import com.stek101.projectzulu.common.potion.subitem.SubItemPotionHarm;
import com.stek101.projectzulu.common.potion.subitem.SubItemPotionHeal;
import com.stek101.projectzulu.common.potion.subitem.SubItemPotionInvisibility;
import com.stek101.projectzulu.common.potion.subitem.SubItemPotionMoveSlowdown;
import com.stek101.projectzulu.common.potion.subitem.SubItemPotionMoveSpeed;
import com.stek101.projectzulu.common.potion.subitem.SubItemPotionNightVision;
import com.stek101.projectzulu.common.potion.subitem.SubItemPotionPoison;
import com.stek101.projectzulu.common.potion.subitem.SubItemPotionRegeneration;
import com.stek101.projectzulu.common.potion.subitem.SubItemPotionRegistry;
import com.stek101.projectzulu.common.potion.subitem.SubItemPotionWeakness;

import cpw.mods.fml.common.registry.GameRegistry;

public class PZVanillaPotionDeclaration extends ItemDeclaration {

    public PZVanillaPotionDeclaration() {
        super("PZVanillaPotion");
    }

    @Override
    protected boolean createItem() {
        Item item = new ItemPZPotion(name);
        ItemList.vanillaPotions = Optional.of(item);
        int i = 0;
        List<SubItemPotion> list = new ArrayList<SubItemPotion>();

        addToLists(item, i++, SubItemPotionList.STRENGTH, list, SubItemPotionDamageBoost.class);
        addToLists(item, i++, SubItemPotionList.REGENERATION, list, SubItemPotionRegeneration.class);
        addToLists(item, i++, SubItemPotionList.POISON, list, SubItemPotionPoison.class);
        addToLists(item, i++, SubItemPotionList.WEAKNESS, list, SubItemPotionWeakness.class);
        addToLists(item, i++, SubItemPotionList.MOVE_SPEED, list, SubItemPotionMoveSpeed.class);
        addToLists(item, i++, SubItemPotionList.MOVE_SLOW, list, SubItemPotionMoveSlowdown.class);
        addToLists(item, i++, SubItemPotionList.FIRE_RESISTANCE, list, SubItemPotionFireResistance.class);
        addToLists(item, i++, SubItemPotionList.NIGHT_VISION, list, SubItemPotionNightVision.class);
        addToLists(item, i++, SubItemPotionList.INVISIBILITY, list, SubItemPotionInvisibility.class);
        addToLists(item, i++, SubItemPotionList.HEAL, list, SubItemPotionHeal.class);
        addToLists(item, i++, SubItemPotionList.HARM, list, SubItemPotionHarm.class);

        for (SubItemPotion subItemPotion : list) {
            SubItemPotionRegistry.INSTANCE.addSubPotions(subItemPotion);
        }
        return true;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.vanillaPotions.get();
        registerSubPotions(item);
        GameRegistry.registerItem(item, name);
    }

    private void registerSubPotions(Item itemID) {
        Collection<SubItemPotion> potions = SubItemPotionRegistry.INSTANCE.getPotions(itemID);
        for (SubItemPotion subItemPotion : potions) {
            subItemPotion.register();
        }
    }

    private void addToLists(Item itemID, int subID, SubItemPotionList entry, List<SubItemPotion> registryList,
            Class<? extends SubItemPotion> potionClass) {
        try {
            SubItemPotion subItemPotion = potionClass.getConstructor(new Class[] { Item.class, int.class }).newInstance(
                    new Object[] { itemID, subID });
            entry.set(subItemPotion);
            registryList.add(subItemPotion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
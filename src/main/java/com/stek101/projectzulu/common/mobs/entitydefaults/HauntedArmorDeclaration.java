package com.stek101.projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;

import com.stek101.projectzulu.common.api.CustomMobData;
import com.stek101.projectzulu.common.api.ItemList;
import com.stek101.projectzulu.common.core.ConfigHelper;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.core.ItemGenerics;
import com.stek101.projectzulu.common.core.entitydeclaration.EggableDeclaration;
import com.stek101.projectzulu.common.core.entitydeclaration.EntityProperties;
import com.stek101.projectzulu.common.mobs.entity.EntityHauntedArmor;
import com.stek101.projectzulu.common.mobs.models.ModelHauntedArmor;
import com.stek101.projectzulu.common.mobs.renders.RenderHauntedArmor;
import com.stek101.projectzulu.common.mobs.renders.RenderWrapper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HauntedArmorDeclaration extends EggableDeclaration {

    public HauntedArmorDeclaration() {
        super("Haunted Armor", 39, EntityHauntedArmor.class, EnumCreatureType.monster);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 2);

        eggColor1 = (194 << 16) + (194 << 8) + 194;
        eggColor2 = (251 << 16) + (246 << 8) + 36;
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.Ectoplasm.meta(), 4);
        customMobData.entityProperties = new EntityProperties(25f, 4.0f, 0.2f).createFromConfig(config, mobName);
        super.outputDataToList(config, customMobData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderHauntedArmor(new ModelHauntedArmor(), 0.5f, new ResourceLocation(DefaultProps.mobKey, 
        		"textures/mammoth.png"));
    }
}

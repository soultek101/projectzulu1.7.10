package com.ngb.projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import com.ngb.projectzulu.common.api.CustomMobData;
import com.ngb.projectzulu.common.api.ItemList;
import com.ngb.projectzulu.common.core.ConfigHelper;
import com.ngb.projectzulu.common.core.DefaultProps;
import com.ngb.projectzulu.common.core.ItemGenerics;
import com.ngb.projectzulu.common.core.entitydeclaration.EggableDeclaration;
import com.ngb.projectzulu.common.core.entitydeclaration.EntityProperties;
import com.ngb.projectzulu.common.mobs.entity.EntityMinotaur;
import com.ngb.projectzulu.common.mobs.models.ModelMinotaur;
import com.ngb.projectzulu.common.mobs.renders.RenderGenericLiving;
import com.ngb.projectzulu.common.mobs.renders.RenderWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MinotaurDeclaration extends EggableDeclaration {

    public MinotaurDeclaration() {
        super("Minotaur", 38, EntityMinotaur.class, EnumCreatureType.monster);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 2);

        eggColor1 = (51 << 16) + (34 << 8) + 8;
        eggColor2 = (255 << 16) + (255 << 8) + 255;
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.furPelt, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.scrapMeat, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.LargeHeart.meta(), 4);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.Ectoplasm.meta(), 4);
        customMobData.entityProperties = new EntityProperties(30f, 3.0f, 0.25f).createFromConfig(config, mobName);
        super.outputDataToList(config, customMobData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderGenericLiving(new ModelMinotaur(), 0.5f, new ResourceLocation(DefaultProps.mobKey, "minotaur.png"));
    }
}

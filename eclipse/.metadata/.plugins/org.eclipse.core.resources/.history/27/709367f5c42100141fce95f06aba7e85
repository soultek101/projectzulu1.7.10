package com.ngb.projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import com.ngb.projectzulu.common.api.BlockList;
import com.ngb.projectzulu.common.api.CustomMobData;
import com.ngb.projectzulu.common.api.ItemList;
import com.ngb.projectzulu.common.core.ConfigHelper;
import com.ngb.projectzulu.common.core.DefaultProps;
import com.ngb.projectzulu.common.core.entitydeclaration.CreatureDeclaration;
import com.ngb.projectzulu.common.core.entitydeclaration.EntityProperties;
import com.ngb.projectzulu.common.mobs.entity.EntityMummyPharaoh;
import com.ngb.projectzulu.common.mobs.models.ModelMummyPharaoh;
import com.ngb.projectzulu.common.mobs.renders.RenderMummyPharaoh;
import com.ngb.projectzulu.common.mobs.renders.RenderWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PharaohDeclaration extends CreatureDeclaration {

    public PharaohDeclaration() {
        super("Mummy Pharaoh", 4, EntityMummyPharaoh.class, EnumCreatureType.monster);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 3);
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {

        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, Items.iron_ingot, 0, 40);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, BlockList.jasper, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.ankh, 0, 10);
        customMobData.entityProperties = new EntityProperties(200f, 3.0f, 0.35f).createFromConfig(config, mobName);
        super.outputDataToList(config, customMobData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderMummyPharaoh(new ModelMummyPharaoh(), 0.5f, new ResourceLocation(DefaultProps.mobKey,
                "mummy_pharaoh.png"));
    }
}

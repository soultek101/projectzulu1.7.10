package com.stek101.projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;

import com.stek101.projectzulu.common.api.BlockList;
import com.stek101.projectzulu.common.api.CustomMobData;
import com.stek101.projectzulu.common.api.ItemList;
import com.stek101.projectzulu.common.core.ConfigHelper;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.core.entitydeclaration.EggableDeclaration;
import com.stek101.projectzulu.common.core.entitydeclaration.EntityProperties;
import com.stek101.projectzulu.common.mobs.entity.EntityMummyPharaoh;
import com.stek101.projectzulu.common.mobs.models.ModelMummyPharaoh;
import com.stek101.projectzulu.common.mobs.renders.RenderMummyPharaoh;
import com.stek101.projectzulu.common.mobs.renders.RenderWrapper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PharaohDeclaration extends EggableDeclaration {

    public PharaohDeclaration() {
        super("Mummy Pharaoh", 4, EntityMummyPharaoh.class, EnumCreatureType.monster);    
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 2);

        eggColor1 = (255 << 16) + (255 << 8) + 255;
        eggColor2 = (255 << 16) + (255 << 8) + 255;
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
                "textures/mummy_pharaoh.png"));
    }

}

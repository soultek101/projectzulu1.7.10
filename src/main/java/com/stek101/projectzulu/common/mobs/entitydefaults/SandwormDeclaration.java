package com.stek101.projectzulu.common.mobs.entitydefaults;

import java.util.HashSet;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.config.Configuration;

import com.google.common.base.Optional;
import com.stek101.projectzulu.common.api.CustomEntityList;
import com.stek101.projectzulu.common.api.CustomMobData;
import com.stek101.projectzulu.common.api.ItemList;
import com.stek101.projectzulu.common.core.ConfigHelper;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.core.ItemGenerics;
import com.stek101.projectzulu.common.core.entitydeclaration.EntityProperties;
import com.stek101.projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import com.stek101.projectzulu.common.mobs.entity.EntitySandWorm;
import com.stek101.projectzulu.common.mobs.models.ModelSandWorm;
import com.stek101.projectzulu.common.mobs.renders.RenderGenericIdle;
import com.stek101.projectzulu.common.mobs.renders.RenderWrapper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SandwormDeclaration extends SpawnableDeclaration {

    public SandwormDeclaration() {
        super("SandWorm", 1, EntitySandWorm.class, EnumCreatureType.monster);
        setSpawnProperties(1, 100, 1, 1);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 2);

        eggColor1 = (24 << 16) + (0 << 8) + 8;
        eggColor2 = (49 << 16) + (16 << 8) + 8;
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.scrapMeat, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.SmallHeart.meta(), 4);
        ConfigHelper.userItemConfigRangeToMobData(config, "MOB CONTROLS." + mobName, customMobData);
        customMobData.entityProperties = new EntityProperties(20f, 3.0f, 0.22f).createFromConfig(config, mobName);
        CustomEntityList.SANDWORM.modData = Optional.of(customMobData);
        super.outputDataToList(config, customMobData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderGenericIdle(new ModelSandWorm(), 0.5f, new ResourceLocation(DefaultProps.mobKey,
                "textures/sandworm.png"), new ResourceLocation(DefaultProps.mobKey, "textures/sandworm_hidden.png"));
    }

    @Override
    public HashSet<String> getDefaultBiomesToSpawn() {
        HashSet<String> defaultBiomesToSpawn = new HashSet<String>();
        defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.desertHills.biomeName);
        defaultBiomesToSpawn.add("Mountainous Desert");
        defaultBiomesToSpawn.addAll(typeToArray(Type.DESERT));
        return defaultBiomesToSpawn;
    }
}

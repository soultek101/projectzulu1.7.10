package com.stek101.projectzulu.common.mobs.entitydefaults;

import java.util.HashSet;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.config.Configuration;

import com.stek101.projectzulu.common.api.CustomMobData;
import com.stek101.projectzulu.common.api.ItemList;
import com.stek101.projectzulu.common.core.ConfigHelper;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.core.ItemGenerics;
import com.stek101.projectzulu.common.core.entitydeclaration.EntityProperties;
import com.stek101.projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import com.stek101.projectzulu.common.mobs.entity.EntityArmadillo;
import com.stek101.projectzulu.common.mobs.models.ModelArmadillo;
import com.stek101.projectzulu.common.mobs.renders.RenderGenericLiving;
import com.stek101.projectzulu.common.mobs.renders.RenderWrapper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ArmadilloDeclaration extends SpawnableDeclaration {

    public ArmadilloDeclaration() {
        super("Armadillo", 0, EntityArmadillo.class, EnumCreatureType.creature);
        setSpawnProperties(10, 100, 2, 4);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 2);

        eggColor1 = (116 << 16) + (64 << 8) + 33;
        eggColor2 = (60 << 16) + (51 << 8) + 10;
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.scaleItem, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.scrapMeat, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.SmallHeart.meta(), 4);
        customMobData.entityProperties = new EntityProperties(12f, 3.0f, 0.3f).createFromConfig(config, mobName);
        super.outputDataToList(config, customMobData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderGenericLiving(new ModelArmadillo(), 0.5f, new ResourceLocation(DefaultProps.mobKey,
                "textures/armadillo.png"));
    }

    @Override
    public HashSet<String> getDefaultBiomesToSpawn() {
        HashSet<String> defaultBiomesToSpawn = new HashSet<String>();
        defaultBiomesToSpawn.add(BiomeGenBase.jungle.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.jungleHills.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.jungleEdge.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.savanna.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName);
        defaultBiomesToSpawn.addAll(typeToArray(Type.JUNGLE));
        defaultBiomesToSpawn.addAll(typeToArray(Type.SAVANNA));
        return defaultBiomesToSpawn;
    }
}

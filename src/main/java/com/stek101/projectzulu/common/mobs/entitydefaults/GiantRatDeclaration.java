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
import com.stek101.projectzulu.common.mobs.entity.EntityGiantRat;
import com.stek101.projectzulu.common.mobs.models.ModelGiantRat;
import com.stek101.projectzulu.common.mobs.renders.RenderGenericLiving;
import com.stek101.projectzulu.common.mobs.renders.RenderWrapper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GiantRatDeclaration extends SpawnableDeclaration {

    public GiantRatDeclaration() {
        super("GiantRat", 55, EntityGiantRat.class, EnumCreatureType.creature);
        setSpawnProperties(8, 100, 1, 3);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 2);

        eggColor1 = (222 << 16) + (222 << 8) + 64;
        eggColor2 = (75 << 16) + (85 << 8) + 24;
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.furPelt, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.scrapMeat, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.SmallHeart.meta(), 5);
        customMobData.entityProperties = new EntityProperties(15f, 3.0f, 0.3f, 0.0f, 0.0f, 32.0f, 50f, 16D).createFromConfig(config, mobName);
        super.outputDataToList(config, customMobData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderGenericLiving(new ModelGiantRat(), 0.5f, new ResourceLocation(DefaultProps.mobKey, 
        		"textures/giantrat0.png"));
    }

    @Override
    public HashSet<String> getDefaultBiomesToSpawn() {
        HashSet<String> defaultBiomesToSpawn = new HashSet<String>();
        defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.extremeHills.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.taiga.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.swampland.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName);

        HashSet<String> nonFrozen = new HashSet<String>();
        nonFrozen.addAll(typeToArray(Type.MOUNTAIN));
        nonFrozen.addAll(typeToArray(Type.FOREST));
        nonFrozen.addAll(typeToArray(Type.PLAINS));
        nonFrozen.removeAll(typeToArray(Type.FROZEN));
        defaultBiomesToSpawn.addAll(nonFrozen);
        return defaultBiomesToSpawn;
    }
}
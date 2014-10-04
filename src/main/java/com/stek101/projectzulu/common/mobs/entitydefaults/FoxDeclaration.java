package com.stek101.projectzulu.common.mobs.entitydefaults;

import java.util.HashSet;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Items;
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
import com.stek101.projectzulu.common.mobs.entity.EntityFox;
import com.stek101.projectzulu.common.mobs.models.ModelFox;
import com.stek101.projectzulu.common.mobs.renders.RenderTameable;
import com.stek101.projectzulu.common.mobs.renders.RenderWrapper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FoxDeclaration extends SpawnableDeclaration {

    public FoxDeclaration() {
        super("Fox", 9, EntityFox.class, EnumCreatureType.creature);
        setSpawnProperties(10, 100, 1, 3);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 2);

        eggColor1 = (204 << 16) + (132 << 8) + 22;
        eggColor2 = (224 << 16) + (224 << 8) + 224;
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                Items.beef, 0, 5);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.furPelt, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.scrapMeat, 0, 15);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.SmallHeart.meta(), 4);
        customMobData.entityProperties = new EntityProperties(12f, 2.0f, 0.3f, 100f, 0.0f, 32.0f, 30f, 16D).createFromConfig(config, mobName);
        super.outputDataToList(config, customMobData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderTameable(new ModelFox(), 0.5f, new ResourceLocation(DefaultProps.mobKey, 
        		"textures/fox.png"));
    }

    @Override
    public HashSet<String> getDefaultBiomesToSpawn() {
        HashSet<String> defaultBiomesToSpawn = new HashSet<String>();
        defaultBiomesToSpawn.add(BiomeGenBase.taiga.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.taigaHills.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName);
        defaultBiomesToSpawn.add("Birch Forest");
        defaultBiomesToSpawn.add("Forested Island");
        defaultBiomesToSpawn.add("Forested Hills");
        defaultBiomesToSpawn.add("Green Hills");
        defaultBiomesToSpawn.add("Mountain Taiga");
        defaultBiomesToSpawn.add("Pine Forest");
        defaultBiomesToSpawn.add("Rainforest");
        defaultBiomesToSpawn.add("Redwood Forest");
        defaultBiomesToSpawn.add("Lush Redwoods");
        defaultBiomesToSpawn.add("Snow Forest");
        defaultBiomesToSpawn.add("Snowy Rainforest");
        defaultBiomesToSpawn.add("Temperate Rainforest");
        defaultBiomesToSpawn.add("Woodlands");
        defaultBiomesToSpawn.addAll(typeToArray(Type.FOREST));
        return defaultBiomesToSpawn;
    }
}

package com.stek101.projectzulu.common.mobs.entitydefaults;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Items;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.config.Configuration;

import com.stek101.projectzulu.common.api.CustomMobData;
import com.stek101.projectzulu.common.core.ConfigHelper;
import com.stek101.projectzulu.common.core.entitydeclaration.EntityProperties;
import com.stek101.projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import com.stek101.projectzulu.common.mobs.entity.EntityFishB;
import com.stek101.projectzulu.common.mobs.models.ModelFishB;
import com.stek101.projectzulu.common.mobs.renders.RenderGenericLivingMT;
import com.stek101.projectzulu.common.mobs.renders.RenderWrapper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FishBDeclaration extends SpawnableDeclaration {
	private final List <String> entityTextures = new ArrayList <String>();
	
	public FishBDeclaration() {
    	
        super("FishB", 56, EntityFishB.class, EnumCreatureType.waterCreature);
        setSpawnProperties(12, 100, 4, 4);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 1);
        
        eggColor1 = (25 << 16) + (106 << 8) + 150;
        eggColor2 = (200 << 16) + (103 << 8) + 150;
        
        entityTextures.add("textures/fishb0.png");
        entityTextures.add("textures/fishb1.png");
        entityTextures.add("textures/fishb2.png");
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, Items.fish, 0, 10);
        customMobData.entityProperties = new EntityProperties(3f, 1.0f, 0.3f, 0.0f, 0.0f, 32.0f, 0.0f, 12D).createFromConfig(config, mobName);
        super.outputDataToList(config, customMobData);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderGenericLivingMT(new ModelFishB(), 0.5f, entityTextures);
    }
    
    @Override
    public HashSet<String> getDefaultBiomesToSpawn() {
        HashSet<String> defaultBiomesToSpawn = new HashSet<String>();
        defaultBiomesToSpawn.add(BiomeGenBase.ocean.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.river.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.beach.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.frozenOcean.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.deepOcean.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.frozenRiver.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.swampland.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.birchForest.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.birchForestHills.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.coldBeach.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.coldTaiga.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.coldTaigaHills.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.desertHills.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.extremeHills.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.extremeHillsEdge.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.extremeHillsPlus.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.coldTaigaHills.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.roofedForest.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.jungleEdge.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.jungle.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.jungleHills.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.megaTaiga.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.megaTaigaHills.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.mesa.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.mesaPlateau.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.mushroomIsland.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.mushroomIslandShore.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.savanna.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.savannaPlateau.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.stoneBeach.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.taiga.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.taigaHills.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName);
        
        HashSet<String> nonFrozen = new HashSet<String>();
        nonFrozen.addAll(typeToArray(Type.OCEAN));
        nonFrozen.addAll(typeToArray(Type.RIVER));
        nonFrozen.addAll(typeToArray(Type.PLAINS));
        nonFrozen.addAll(typeToArray(Type.SANDY));
        nonFrozen.addAll(typeToArray(Type.WATER));
        nonFrozen.addAll(typeToArray(Type.WET));
        nonFrozen.addAll(typeToArray(Type.FOREST));
        nonFrozen.addAll(typeToArray(Type.CONIFEROUS));
        nonFrozen.addAll(typeToArray(Type.JUNGLE));
        nonFrozen.addAll(typeToArray(Type.DENSE));
        nonFrozen.addAll(typeToArray(Type.LUSH));
        nonFrozen.addAll(typeToArray(Type.HILLS));
        nonFrozen.removeAll(typeToArray(Type.FROZEN));
        defaultBiomesToSpawn.addAll(nonFrozen);
        return defaultBiomesToSpawn;
    }
}
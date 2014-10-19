package com.stek101.projectzulu.common.mobs.entitydefaults;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.config.Configuration;

import com.stek101.projectzulu.common.api.CustomMobData;
import com.stek101.projectzulu.common.api.ItemList;
import com.stek101.projectzulu.common.core.ConfigHelper;
import com.stek101.projectzulu.common.core.ItemGenerics;
import com.stek101.projectzulu.common.core.entitydeclaration.EntityProperties;
import com.stek101.projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import com.stek101.projectzulu.common.mobs.entity.EntityGoat;
import com.stek101.projectzulu.common.mobs.models.ModelGoat;
import com.stek101.projectzulu.common.mobs.renders.RenderGenericLivingMT;
import com.stek101.projectzulu.common.mobs.renders.RenderWrapper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GoatDeclaration extends SpawnableDeclaration {	
	private final List <String> goatTextures = new ArrayList <String>();
	
    public GoatDeclaration() {
        super("Goat", 45, EntityGoat.class, EnumCreatureType.creature);
        setSpawnProperties(10, 100, 1, 3);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 2);

        eggColor1 = (152 << 16) + (152 << 8) + 152;
        eggColor2 = (158 << 16) + (99 << 8) + 22;
        
        goatTextures.add("textures/goat0.png");
        goatTextures.add("textures/goat1.png");
        goatTextures.add("textures/goat2.png");     
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.muttonRaw, 0, 2);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.furPelt, 0, 10);        
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.SmallHeart.meta(), 5);
        customMobData.entityProperties = new EntityProperties(9f, 2.0f, 0.3f, 0.0f, 0.5f, 32.0f, 20.0f, 16D).createFromConfig(
                config, mobName);
        super.outputDataToList(config, customMobData);
    }

     
    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
    	return new RenderGenericLivingMT(new ModelGoat(), 0.5f, goatTextures);
    }

    @Override
    public HashSet<String> getDefaultBiomesToSpawn() {
        HashSet<String> defaultBiomesToSpawn = new HashSet<String>();
        defaultBiomesToSpawn.add(BiomeGenBase.taiga.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.taigaHills.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.birchForestHills.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.birchForestHills.biomeName);
        defaultBiomesToSpawn.add("Alpine");
        defaultBiomesToSpawn.add("Mountain Taiga");
        defaultBiomesToSpawn.add("Snowy Rainforest");

        HashSet<String> frozenForest = new HashSet<String>();
        frozenForest.addAll(typeToArray(Type.PLAINS));
        frozenForest.addAll(typeToArray(Type.HILLS));
        frozenForest.retainAll(typeToArray(Type.FROZEN));
        defaultBiomesToSpawn.addAll(frozenForest);
        return defaultBiomesToSpawn;
    }
}
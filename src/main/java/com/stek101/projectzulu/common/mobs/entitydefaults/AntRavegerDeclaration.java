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
import com.stek101.projectzulu.common.mobs.entity.EntityAntRavager;
import com.stek101.projectzulu.common.mobs.models.ModelAntRavager;
import com.stek101.projectzulu.common.mobs.renders.RenderGenericLiving;
import com.stek101.projectzulu.common.mobs.renders.RenderWrapper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AntRavegerDeclaration extends SpawnableDeclaration {
	
    public AntRavegerDeclaration() {
        super("RavagerAnt", 61, EntityAntRavager.class, EnumCreatureType.creature);
        setSpawnProperties(8, 100, 1, 4);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 2);

        eggColor1 = (44 << 16) + (44 << 8) + 132;
        eggColor2 = (188 << 16) + (188 << 8) + 22;
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.scrapMeat, 0, 2);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.SmallHeart.meta(), 5);
        customMobData.entityProperties = new EntityProperties(9f, 2.0f, 0.10f, 0.0f, 0.5f, 32.0f, 20.0f, 16D).createFromConfig(
                config, mobName);
        super.outputDataToList(config, customMobData);
    }

     
    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
    	 //int r1 = (int) (Math.random()* goatTextures.length);	 
    	   
        return new RenderGenericLiving(new ModelAntRavager(), 0.5f, new ResourceLocation(DefaultProps.mobKey, 
        		"textures/antravager.png"));
    }

    @Override
    public HashSet<String> getDefaultBiomesToSpawn() {
        HashSet<String> defaultBiomesToSpawn = new HashSet<String>();        
        defaultBiomesToSpawn.add(BiomeGenBase.birchForestHills.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.birchForest.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.roofedForest.biomeName);
        
        HashSet<String> nonfrozenForest = new HashSet<String>();
        nonfrozenForest.addAll(typeToArray(Type.PLAINS));
        nonfrozenForest.addAll(typeToArray(Type.FOREST));
        nonfrozenForest.addAll(typeToArray(Type.CONIFEROUS));
        nonfrozenForest.removeAll(typeToArray(Type.FROZEN));
        defaultBiomesToSpawn.addAll(nonfrozenForest);
        return defaultBiomesToSpawn;
    }
}
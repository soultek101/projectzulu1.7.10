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
import com.stek101.projectzulu.common.core.entitydeclaration.EntityProperties;
import com.stek101.projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import com.stek101.projectzulu.common.mobs.entity.EntityBeetleBS;
import com.stek101.projectzulu.common.mobs.models.ModelBeetleBS;
import com.stek101.projectzulu.common.mobs.renders.RenderGenericLivingMT;
import com.stek101.projectzulu.common.mobs.renders.RenderWrapper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BeetleBSDeclaration extends SpawnableDeclaration {
	private final List <String> entityTextures = new ArrayList <String>();
	
    public BeetleBSDeclaration() {
        super("BeetleBS", 63, EntityBeetleBS.class, EnumCreatureType.ambient);
        setSpawnProperties(10, 5, 1, 1);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 1);

        eggColor1 = (226 << 16) + (126 << 8) + 0;
        eggColor2 = (101 << 16) + (101 << 8) + 143;
        
        entityTextures.add("textures/beetlebs1.png");
        entityTextures.add("textures/beetlebs2.png");
        entityTextures.add("textures/beetlebs3.png");
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        //ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, Items.feather, 0, 8);
        customMobData.entityProperties = new EntityProperties(1f, 0.0f, 0.15f).createFromConfig(config, mobName);
        super.outputDataToList(config, customMobData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
       // return new RenderGenericLiving(new ModelBeetleAS(), 0.5f, new ResourceLocation(DefaultProps.mobKey,
        //        "textures/beetleas1.png"));
    	return new RenderGenericLivingMT(new ModelBeetleBS(), 0.5f, entityTextures);
    }

    @Override
    public HashSet<String> getDefaultBiomesToSpawn() {
        HashSet<String> defaultBiomesToSpawn = new HashSet<String>();
        defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName);
        defaultBiomesToSpawn.add("Autumn Woods");
        defaultBiomesToSpawn.add("Birch Forest");
        defaultBiomesToSpawn.add("Forested Hills");
        defaultBiomesToSpawn.add("Forested Island");
        defaultBiomesToSpawn.add("Green Hills");
        defaultBiomesToSpawn.add("Redwood Forest");
        defaultBiomesToSpawn.add("Lush Redwoods");
        defaultBiomesToSpawn.add("Temperate Rainforest");
        defaultBiomesToSpawn.add("Woodlands");

        HashSet<String> nonFrozenForest = new HashSet<String>();
        nonFrozenForest.addAll(typeToArray(Type.FOREST));
        nonFrozenForest.addAll(typeToArray(Type.PLAINS));
        nonFrozenForest.removeAll(typeToArray(Type.FROZEN));
        defaultBiomesToSpawn.addAll(nonFrozenForest);

        return defaultBiomesToSpawn;
    }
}

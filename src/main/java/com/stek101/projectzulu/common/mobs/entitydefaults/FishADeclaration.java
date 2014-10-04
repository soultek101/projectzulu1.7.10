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
import com.stek101.projectzulu.common.mobs.entity.EntityFishA;
import com.stek101.projectzulu.common.mobs.models.ModelFishA;
import com.stek101.projectzulu.common.mobs.renders.RenderGenericLivingMT;
import com.stek101.projectzulu.common.mobs.renders.RenderWrapper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FishADeclaration extends SpawnableDeclaration {
	private final List <String> entityTextures = new ArrayList <String>();
	
	public FishADeclaration() {
    	
        super("FishA", 50, EntityFishA.class, EnumCreatureType.waterCreature);
        setSpawnProperties(100, 100, 4, 4);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 1);
        
        eggColor1 = (25 << 16) + (106 << 8) + 150;
        eggColor2 = (105 << 16) + (203 << 8) + 67;
        
        entityTextures.add("textures/fisha0.png");
        entityTextures.add("textures/fisha1.png");
        entityTextures.add("textures/fisha2.png");
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
        return new RenderGenericLivingMT(new ModelFishA(), 0.5f, entityTextures);
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
        defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName);
        
        HashSet<String> nonFrozen = new HashSet<String>();
        nonFrozen.addAll(typeToArray(Type.OCEAN));
        nonFrozen.addAll(typeToArray(Type.RIVER));
        nonFrozen.addAll(typeToArray(Type.PLAINS));
        nonFrozen.addAll(typeToArray(Type.SANDY));
        nonFrozen.addAll(typeToArray(Type.WATER));
        nonFrozen.addAll(typeToArray(Type.WET));
        nonFrozen.removeAll(typeToArray(Type.FROZEN));
        defaultBiomesToSpawn.addAll(nonFrozen);
        return defaultBiomesToSpawn;
    }
}
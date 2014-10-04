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
import com.stek101.projectzulu.common.core.ConfigHelper;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.core.entitydeclaration.EntityProperties;
import com.stek101.projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import com.stek101.projectzulu.common.mobs.entity.EntityCyclops;
import com.stek101.projectzulu.common.mobs.models.ModelCyclops;
import com.stek101.projectzulu.common.mobs.renders.RenderGenericModelBiped;
import com.stek101.projectzulu.common.mobs.renders.RenderWrapper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CyclopsDeclaration extends SpawnableDeclaration {

    public CyclopsDeclaration() {
        super("Cyclops", 52, EntityCyclops.class, EnumCreatureType.monster);
        setSpawnProperties(5, 100, 1, 2);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 2);

        eggColor1 = (245 << 16) + (2455 << 8) + 245;
        eggColor2 = (155 << 16) + (155 << 8) + 155;
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
    	ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, Items.bread, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, Items.gold_nugget, 0, 10);
        customMobData.entityProperties = new EntityProperties(20f, 3.0f, 0.25f).createFromConfig(config, mobName);
        super.outputDataToList(config, customMobData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {      
        return new RenderGenericModelBiped(new ModelCyclops(), 0.5f, new ResourceLocation(DefaultProps.mobKey,
        		"textures/cyclops.png"));
    }

    @Override
    public HashSet<String> getDefaultBiomesToSpawn() {
        HashSet<String> defaultBiomesToSpawn = new HashSet<String>();
        defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.desertHills.biomeName);
        defaultBiomesToSpawn.addAll(typeToArray(Type.DESERT));
        return defaultBiomesToSpawn;
    }
}

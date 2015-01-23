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
import com.stek101.projectzulu.common.mobs.entity.EntityCamel;
import com.stek101.projectzulu.common.mobs.models.ModelCamel;
import com.stek101.projectzulu.common.mobs.renders.RenderGenericHorse;
import com.stek101.projectzulu.common.mobs.renders.RenderWrapper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CamelDeclaration extends SpawnableDeclaration {
	
    public CamelDeclaration() {
        super("Camel", 58, EntityCamel.class, EnumCreatureType.creature);
        setSpawnProperties(10, 100, 1, 4);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 2);

        eggColor1 = (231 << 16) + (231 << 8) + 12;
        eggColor2 = (180 << 16) + (180 << 8) + 12;
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                Items.beef, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.scrapMeat, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.BlackLichen.meta(), 4);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.LargeHeart.meta(), 4);
        ConfigHelper.userItemConfigRangeToMobData(config, "MOB CONTROLS." + mobName, customMobData);
        customMobData.entityProperties = new EntityProperties(20f, 5.0f, 0.20f, 0.0f, 0.5f, 32.0f, 20.0f, 16D).createFromConfig(
                config, mobName);
        super.outputDataToList(config, customMobData);
    }

     
    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        //return new RenderGenericLiving(new ModelCamel(), 0.5f, new ResourceLocation(DefaultProps.mobKey, 
        //		"textures/camel.png"));
        return new RenderGenericHorse(new ModelCamel(), 0.5f, new ResourceLocation(DefaultProps.mobKey,
                "textures/camel.png"), new ResourceLocation(DefaultProps.mobKey, 
                		"textures/camel_saddled.png"));
    }

    @Override
    public HashSet<String> getDefaultBiomesToSpawn() {
        HashSet<String> defaultBiomesToSpawn = new HashSet<String>();        
        defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.desertHills.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.savanna.biomeName);
        
        HashSet<String> nonfrozenForest = new HashSet<String>();
        nonfrozenForest.addAll(typeToArray(Type.DESERT));
        nonfrozenForest.addAll(typeToArray(Type.SAVANNA));
        nonfrozenForest.removeAll(typeToArray(Type.FROZEN));
        defaultBiomesToSpawn.addAll(nonfrozenForest);
        return defaultBiomesToSpawn;
    }
}
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
import com.stek101.projectzulu.common.mobs.entity.EntityRabbit;
import com.stek101.projectzulu.common.mobs.models.ModelRabbit;
import com.stek101.projectzulu.common.mobs.renders.RenderGenericLiving;
import com.stek101.projectzulu.common.mobs.renders.RenderWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RabbitDeclaration extends SpawnableDeclaration {

    public RabbitDeclaration() {
        super("Rabbit", 21, EntityRabbit.class, EnumCreatureType.creature);
        setSpawnProperties(15, 100, 2, 4);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 1);

        eggColor1 = (239 << 16) + (179 << 8) + 83;
        eggColor2 = (237 << 16) + (208 << 8) + 166;
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.scrapMeat, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.SmallHeart.meta(), 4);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.RabbitsFoot.meta(), 8);
        customMobData.entityProperties = new EntityProperties(5f, 1.0f, 0.3f, 100f, 0.0f, 32.0f, 10.0f, 10D).createFromConfig(config, mobName);
        super.outputDataToList(config, customMobData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderGenericLiving(new ModelRabbit(), 0.5f, new ResourceLocation(DefaultProps.mobKey, 
        		"textures/rabbit.png"));
    }

    @Override
    public HashSet<String> getDefaultBiomesToSpawn() {
        HashSet<String> defaultBiomesToSpawn = new HashSet<String>();
        defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName);        
        defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.savanna.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.birchForest.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.birchForestHills.biomeName);
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
        nonFrozenForest.removeAll(typeToArray(Type.SNOWY));        
        defaultBiomesToSpawn.addAll(nonFrozenForest);
        return defaultBiomesToSpawn;
    }
}

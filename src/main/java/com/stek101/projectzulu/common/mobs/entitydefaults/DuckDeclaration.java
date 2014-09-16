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
import com.stek101.projectzulu.common.mobs.entity.EntityDuck;
import com.stek101.projectzulu.common.mobs.models.ModelDuck;
import com.stek101.projectzulu.common.mobs.renders.RenderGenericLiving;
import com.stek101.projectzulu.common.mobs.renders.RenderWrapper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DuckDeclaration extends SpawnableDeclaration {

    public DuckDeclaration() {
        super("Duck", 47, EntityDuck.class, EnumCreatureType.creature);
        setSpawnProperties(10, 100, 1, 3);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 2);

        eggColor1 = (200 << 16) + (200 << 8) + 26;
        eggColor2 = (135 << 16) + (135 << 8) + 235;
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, Items.feather, 0, 8);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.scrapMeat, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.SmallHeart.meta(), 4);
        customMobData.entityProperties = new EntityProperties(5f, 1.0f, 0.35f, 0.0f, 0.0f, 32.0f, 10.0f, 16D).createFromConfig(
                config, mobName);
        super.outputDataToList(config, customMobData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderGenericLiving(new ModelDuck(), 0.5f, new ResourceLocation(DefaultProps.mobKey,
                "textures/duck.png"));
    }

    @Override
    public HashSet<String> getDefaultBiomesToSpawn() {
        HashSet<String> defaultBiomesToSpawn = new HashSet<String>();
        defaultBiomesToSpawn.add(BiomeGenBase.river.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.swampland.biomeName);

        HashSet<String> nonFrozenForest = new HashSet<String>();
        nonFrozenForest.addAll(typeToArray(Type.RIVER));
        nonFrozenForest.addAll(typeToArray(Type.WET));
        nonFrozenForest.removeAll(typeToArray(Type.FROZEN));
        defaultBiomesToSpawn.addAll(nonFrozenForest);

        return defaultBiomesToSpawn;
    }
}
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
import com.stek101.projectzulu.common.mobs.entity.EntityGorilla;
import com.stek101.projectzulu.common.mobs.models.ModelGorilla;
import com.stek101.projectzulu.common.mobs.renders.RenderGenericLiving;
import com.stek101.projectzulu.common.mobs.renders.RenderWrapper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GorillaDeclaration extends SpawnableDeclaration {

    public GorillaDeclaration() {
        super("Gorilla", 25, EntityGorilla.class, EnumCreatureType.creature);
        setSpawnProperties(10, 100, 1, 2);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 3);

        eggColor1 = (25 << 16) + (25 << 8) + 25;
        eggColor2 = (93 << 16) + (93 << 8) + 93;
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                Items.beef, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.scrapMeat, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.furPelt, 0, 8);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.LargeHeart.meta(), 4);
        customMobData.entityProperties = new EntityProperties(20f, 5.0f, 0.3f, 0.0f, 0.5f, 32.0f , 40f, 16D).createFromConfig(config, mobName);

        super.outputDataToList(config, customMobData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderGenericLiving(new ModelGorilla(), 0.5f, new ResourceLocation(DefaultProps.mobKey,
                "textures/gorilla.png"));
    }

    @Override
    public HashSet<String> getDefaultBiomesToSpawn() {
        HashSet<String> defaultBiomesToSpawn = new HashSet<String>();
        defaultBiomesToSpawn.add(BiomeGenBase.jungle.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.jungleHills.biomeName);
        defaultBiomesToSpawn.add("Mini Jungle");
        defaultBiomesToSpawn.add("Extreme Jungle");
        defaultBiomesToSpawn.addAll(typeToArray(Type.JUNGLE));
        return defaultBiomesToSpawn;
    }
}

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

import com.google.common.base.Optional;
import com.stek101.projectzulu.common.api.CustomEntityList;
import com.stek101.projectzulu.common.api.CustomMobData;
import com.stek101.projectzulu.common.api.ItemList;
import com.stek101.projectzulu.common.core.ConfigHelper;
import com.stek101.projectzulu.common.core.ItemGenerics;
import com.stek101.projectzulu.common.core.entitydeclaration.EntityProperties;
import com.stek101.projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import com.stek101.projectzulu.common.mobs.entity.EntityHorseBlack;
import com.stek101.projectzulu.common.mobs.models.ModelHorse;
import com.stek101.projectzulu.common.mobs.renders.RenderGenericHorseMT;
import com.stek101.projectzulu.common.mobs.renders.RenderWrapper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HorseBlackDeclaration extends SpawnableDeclaration {
	private final List <String> wildTextures = new ArrayList <String>();
	private final List <String> saddledTextures = new ArrayList <String>();

    public HorseBlackDeclaration() {
        super("Horse", 29, EntityHorseBlack.class, EnumCreatureType.creature);
        setSpawnProperties(5, 50, 1, 2);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 3);

        eggColor1 = (17 << 16) + (17 << 8) + 17;
        eggColor2 = (186 << 16) + (186 << 8) + 186;
        
        wildTextures.add("textures/horse/horse_black.png");
        wildTextures.add("textures/horse/horse_grey.png");
        wildTextures.add("textures/horse/horse_white.png");
        wildTextures.add("textures/horse/horse_brown.png");
        wildTextures.add("textures/horse/horse_beige.png");
        wildTextures.add("textures/horse/horse_dark_black.png");
        wildTextures.add("textures/horse/horse_dark_brown.png");
        
        saddledTextures.add("textures/horse/horse_black_saddled.png");
        saddledTextures.add("textures/horse/horse_grey_saddled.png");
        saddledTextures.add("textures/horse/horse_white_saddled.png");
        saddledTextures.add("textures/horse/horse_brown_saddled.png");
        saddledTextures.add("textures/horse/horse_beige_saddled.png");
        saddledTextures.add("textures/horse/horse_dark_black_saddled.png");
        saddledTextures.add("textures/horse/horse_dark_brown_saddled.png");
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                Items.beef, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.scrapMeat, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.LargeHeart.meta(), 4);
        ConfigHelper.userItemConfigRangeToMobData(config, "MOB CONTROLS." + mobName, customMobData);
        customMobData.entityProperties = new EntityProperties(20f, 3.0f, 0.3f).createFromConfig(config, mobName);
        CustomEntityList.HORSEBLACK.modData = Optional.of(customMobData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
       // return new RenderGenericHorse(new ModelHorse(), 0.5f, new ResourceLocation(DefaultProps.mobKey,
       //         "textures/Horse/horse_black.png"), new ResourceLocation(DefaultProps.mobKey, 
       //         		"textures/Horse/horse_black_saddled.png"));
    	return new RenderGenericHorseMT(new ModelHorse(), 0.5f, wildTextures, saddledTextures);
    }

    @Override
    public HashSet<String> getDefaultBiomesToSpawn() {
        HashSet<String> defaultBiomesToSpawn = new HashSet<String>();
        defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName);

        HashSet<String> nonFrozenForest = new HashSet<String>();
        nonFrozenForest.addAll(typeToArray(Type.FOREST));
        nonFrozenForest.addAll(typeToArray(Type.PLAINS));
        nonFrozenForest.removeAll(typeToArray(Type.FROZEN));
        defaultBiomesToSpawn.addAll(nonFrozenForest);
        return defaultBiomesToSpawn;
    }
}
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
import com.stek101.projectzulu.common.api.ItemList;
import com.stek101.projectzulu.common.core.ConfigHelper;
import com.stek101.projectzulu.common.core.ItemGenerics;
import com.stek101.projectzulu.common.core.entitydeclaration.EntityProperties;
import com.stek101.projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import com.stek101.projectzulu.common.mobs.entity.EntitySkeletonn;
import com.stek101.projectzulu.common.mobs.models.ModelSkeletonn;
import com.stek101.projectzulu.common.mobs.renders.RenderGenericModelBipedMT;
import com.stek101.projectzulu.common.mobs.renders.RenderWrapper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SkeletonnDeclaration extends SpawnableDeclaration {
	private final List <String> entityTextures = new ArrayList <String>();	
	
    public SkeletonnDeclaration() {
        super("Skeletonn", 51, EntitySkeletonn.class, EnumCreatureType.monster);
        setSpawnProperties(5, 30, 1, 2);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 2);

        eggColor1 = (255 << 16) + (155 << 8) + 155;
        eggColor2 = (155 << 16) + (155 << 8) + 155;
        
        entityTextures.add("textures/skeletonn0.png");
        entityTextures.add("textures/skeletonn1.png");
        entityTextures.add("textures/skeletonn2.png");
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
    	ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, Items.bone, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, Items.coal, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.Ectoplasm.meta(), 4);
        customMobData.entityProperties = new EntityProperties(12f, 1.0f, 0.35f).createFromConfig(config, mobName);
        super.outputDataToList(config, customMobData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        //return new RenderGenericLiving(new ModelSkeletonn(), 0.5f, new ResourceLocation(DefaultProps.mobKey, 
        //		"textures/skeletonn.png"));
        
        return new RenderGenericModelBipedMT(new ModelSkeletonn(), 0.5f, entityTextures);
    }

    @Override
    public HashSet<String> getDefaultBiomesToSpawn() {
        HashSet<String> defaultBiomesToSpawn = new HashSet<String>();
        defaultBiomesToSpawn.add(BiomeGenBase.hell.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.swampland.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.desertHills.biomeName);
        defaultBiomesToSpawn.addAll(typeToArray(Type.DEAD));
        defaultBiomesToSpawn.addAll(typeToArray(Type.MAGICAL));
        defaultBiomesToSpawn.addAll(typeToArray(Type.SPOOKY));
        return defaultBiomesToSpawn;
    }
}

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
import com.stek101.projectzulu.common.mobs.entity.EntityMonkey;
import com.stek101.projectzulu.common.mobs.models.ModelMonkeyTailed;
import com.stek101.projectzulu.common.mobs.renders.RenderGenericLiving;
import com.stek101.projectzulu.common.mobs.renders.RenderWrapper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MonkeyDeclaration extends SpawnableDeclaration {
	
    public MonkeyDeclaration() {
        super("Monkey", 59, EntityMonkey.class, EnumCreatureType.creature);
        setSpawnProperties(8, 100, 1, 4);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 2);

        eggColor1 = (78 << 16) + (78 << 8) + 132;
        eggColor2 = (99 << 16) + (99 << 8) + 22;
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.scrapMeat, 0, 2);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.SmallHeart.meta(), 5);
        customMobData.entityProperties = new EntityProperties(9f, 2.0f, 0.3f, 0.0f, 0.5f, 32.0f, 10.0f, 16D).createFromConfig(
                config, mobName);
        super.outputDataToList(config, customMobData);
    }

     
    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
    	 //int r1 = (int) (Math.random()* goatTextures.length);	 
    	   
        return new RenderGenericLiving(new ModelMonkeyTailed(), 0.5f, new ResourceLocation(DefaultProps.mobKey, 
        		"textures/monkey.png" /*goatTextures[r1]*/ ));
    }

    @Override
    public HashSet<String> getDefaultBiomesToSpawn() {
        HashSet<String> defaultBiomesToSpawn = new HashSet<String>();        
        defaultBiomesToSpawn.add(BiomeGenBase.jungle.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.jungleEdge.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.jungleHills.biomeName);
        
        HashSet<String> nonfrozenForest = new HashSet<String>();
        nonfrozenForest.addAll(typeToArray(Type.JUNGLE));
        nonfrozenForest.removeAll(typeToArray(Type.FROZEN));
        defaultBiomesToSpawn.addAll(nonfrozenForest);
        return defaultBiomesToSpawn;
    }
}
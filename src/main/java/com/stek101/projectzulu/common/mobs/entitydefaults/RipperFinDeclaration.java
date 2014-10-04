package com.stek101.projectzulu.common.mobs.entitydefaults;

import java.util.HashSet;
import java.util.Random;

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
import com.stek101.projectzulu.common.core.entitydeclaration.EntityProperties;
import com.stek101.projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import com.stek101.projectzulu.common.mobs.entity.EntityRipperFin;
import com.stek101.projectzulu.common.mobs.models.ModelRipperFin;
import com.stek101.projectzulu.common.mobs.renders.RenderGenericLiving;
import com.stek101.projectzulu.common.mobs.renders.RenderWrapper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RipperFinDeclaration extends SpawnableDeclaration {
	private int textureID;
	private static final ResourceLocation Fishytexture = new ResourceLocation(DefaultProps.mobKey, "textures/ripperfin1.png");
	private static final ResourceLocation Fishytexture2 = new ResourceLocation(DefaultProps.mobKey, "textures/ripperfin1.png");;
	private static final ResourceLocation Fishytexture3 = new ResourceLocation(DefaultProps.mobKey, "textures/ripperfin1.png");;
	
    public RipperFinDeclaration() {
    	
        super("RipperFin", 53, EntityRipperFin.class, EnumCreatureType.waterCreature);
        setSpawnProperties(100, 100, 4, 4);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 1);
        
        eggColor1 = (96 << 16) + (86 << 8) + 100;
        eggColor2 = (95 << 16) + (203 << 8) + 47;
        
        Random rand = new Random();
        this.textureID = rand.nextInt(3);
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {        
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.scrapMeat, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, ItemList.scaleItem, 0, 10);
        customMobData.entityProperties = new EntityProperties(10f, 6.0f, 0.3f, 0.0f, 0.0f, 32.0f, 30f, 12D).createFromConfig(config, mobName);
        super.outputDataToList(config, customMobData);
    }
    
    protected ResourceLocation func_110872_a()
    {
    	int i = textureID;
    	
    	switch(i)
    	{
    	case 0:
    		default:
    		return Fishytexture;
    	case 1:
    		return Fishytexture2;
    	case 2:
    		return Fishytexture3;
    	}
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        //return new RenderGenericLiving(new ModelFishA(), 0.5f, new ResourceLocation(DefaultProps.mobKey, 
        //		"textures/fisha.png"));
    	return new RenderGenericLiving(new ModelRipperFin(), 0.5f, this.func_110872_a());
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
        defaultBiomesToSpawn.addAll(typeToArray(Type.WATER));
        return defaultBiomesToSpawn;
    }
}
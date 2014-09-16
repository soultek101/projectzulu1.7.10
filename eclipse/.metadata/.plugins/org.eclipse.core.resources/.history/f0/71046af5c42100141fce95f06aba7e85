package com.ngb.projectzulu.common.mobs.entitydefaults;

import java.util.HashSet;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.config.Configuration;
import com.ngb.projectzulu.common.api.CustomMobData;
import com.ngb.projectzulu.common.api.ItemList;
import com.ngb.projectzulu.common.core.ConfigHelper;
import com.ngb.projectzulu.common.core.DefaultProps;
import com.ngb.projectzulu.common.core.ItemGenerics;
import com.ngb.projectzulu.common.core.entitydeclaration.EntityProperties;
import com.ngb.projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import com.ngb.projectzulu.common.mobs.entity.EntityPelican;
import com.ngb.projectzulu.common.mobs.models.ModelPelican;
import com.ngb.projectzulu.common.mobs.renders.RenderGenericLiving;
import com.ngb.projectzulu.common.mobs.renders.RenderWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PelicanDeclaration extends SpawnableDeclaration {

    public PelicanDeclaration() {
        super("Pelican", 37, EntityPelican.class, EnumCreatureType.ambient);
        setSpawnProperties(7, 5, 1, 1);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 2);

        eggColor1 = (214 << 16) + (214 << 8) + 214;
        eggColor2 = (168 << 16) + (62 << 8) + 10;
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, Items.chicken, 0, 10);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData, Items.feather, 0, 8);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.LargeHeart.meta(), 4);
        ConfigHelper.configDropToMobData(config, "MOB CONTROLS." + mobName, customMobData,
                ItemList.genericCraftingItems, ItemGenerics.Properties.Talon.meta(), 4);
        customMobData.entityProperties = new EntityProperties(20f, 3.0f, 0.22f).createFromConfig(config, mobName);
        super.outputDataToList(config, customMobData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderWrapper getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderGenericLiving(new ModelPelican(), 0.5f, new ResourceLocation(DefaultProps.mobKey,
                "pelican.png"));
    }

    @Override
    public HashSet<String> getDefaultBiomesToSpawn() {
        HashSet<String> defaultBiomesToSpawn = new HashSet<String>();
        defaultBiomesToSpawn.add(BiomeGenBase.river.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.beach.biomeName);
        defaultBiomesToSpawn.addAll(typeToArray(Type.BEACH));
        return defaultBiomesToSpawn;
    }
}

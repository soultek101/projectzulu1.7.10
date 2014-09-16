package com.stek101.projectzulu.common;

import java.io.File;

import com.stek101.projectzulu.common.blocks.itemblockdeclarations.StructurePlacerDeclaration;
import com.stek101.projectzulu.common.core.CustomEntityManager;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.core.ItemBlockManager;
import com.stek101.projectzulu.common.core.terrain.FeatureGenerator;
import com.stek101.projectzulu.common.world.terrain.CathedralFeature;
import com.stek101.projectzulu.common.world.terrain.CemetaryFeature;
import com.stek101.projectzulu.common.world.terrain.LabyrinthFeature;
import com.stek101.projectzulu.common.world.terrain.OasisFeature;
import com.stek101.projectzulu.common.world.terrain.PyramidFeature;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class ProjectZulu_World extends BaseModule {

    @Override
    public String getIdentifier() {
        return DefaultProps.WorldModId;
    }

    @Override
    public void registration(ItemBlockManager manager) {
        manager.addItemBlock(new StructurePlacerDeclaration());
    }

    @Override
    public void registration(FeatureGenerator manager) {
        manager.registerStructure(new PyramidFeature(), new LabyrinthFeature(), new CemetaryFeature(),
                new OasisFeature(), new CathedralFeature());
    }
}

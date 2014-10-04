package com.stek101.projectzulu.common;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;

import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.core.ItemBlockManager;
import com.stek101.projectzulu.common.dungeon.commands.CommandPlaceBlock;
import com.stek101.projectzulu.common.dungeon.commands.CommandPlaySound;
import com.stek101.projectzulu.common.dungeon.commands.CommandSpawnEntity;
import com.stek101.projectzulu.common.dungeon.commands.CommandStreamSound;
import com.stek101.projectzulu.common.dungeon.itemblockdeclaration.LimitedMobSpawnerDeclaration;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class ProjectZulu_Dungeon extends BaseModule {

    @Override
    public String getIdentifier() {
        return DefaultProps.DungeonModId;
    }

    @Override
    public void registration(ItemBlockManager manager) {
        ItemBlockManager.INSTANCE.addItemBlock(new LimitedMobSpawnerDeclaration());
    }

    @Override
    public void preInit(FMLPreInitializationEvent event, File configDirectory) {
        MinecraftForge.EVENT_BUS.register(new DeathGamerules().loadConfiguration(event.getModConfigurationDirectory()));     
    }

    @Override
    public void serverStarting(FMLServerStartingEvent event, File configDirectory) {
        event.registerServerCommand(new CommandPlaySound());
        event.registerServerCommand(new CommandStreamSound());
        event.registerServerCommand(new CommandSpawnEntity());
        event.registerServerCommand(new CommandPlaceBlock());
    }
}
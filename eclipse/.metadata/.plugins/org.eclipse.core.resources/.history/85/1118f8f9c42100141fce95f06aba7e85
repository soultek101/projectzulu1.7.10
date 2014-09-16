package com.ngb.projectzulu.common.blocks.itemblockdeclarations;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import com.ngb.projectzulu.common.ProjectZulu_Core;
import com.ngb.projectzulu.common.api.BlockList;
import com.ngb.projectzulu.common.blocks.tombstone.BlockTombstone;
import com.ngb.projectzulu.common.blocks.tombstone.TileEntityTombstone;
import com.ngb.projectzulu.common.blocks.tombstone.TileEntityTombstoneRenderer;
import com.ngb.projectzulu.common.core.DefaultProps;
import com.ngb.projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TombstoneDeclaration extends BlockDeclaration {

    public TombstoneDeclaration() {
        super("Tombstone");
    }

    @Override
    protected boolean createBlock() {
        BlockList.tombstone = Optional
                .of(new BlockTombstone(TileEntityTombstone.class).setBlockName(name.toLowerCase()).setBlockTextureName(
                        DefaultProps.blockKey + ":" + name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerBlock() {
        Block block = BlockList.tombstone.get();
        GameRegistry.registerBlock(block, name.toLowerCase());
        Configuration tempConfig = new Configuration(new File(ProjectZulu_Core.modConfigDirectoryFile,
                DefaultProps.configDirectory + DefaultProps.tempConfigFile));
        tempConfig.load();
        Property property = tempConfig.get("TempSettings.Tombstone", "useAlterantiveTileEntityName", false);
        if (!property.getBoolean(false)) {
            try {
                GameRegistry.registerTileEntity(TileEntityTombstone.class, "TileEntityTombstone");
            } catch (IllegalArgumentException e) {
                GameRegistry.registerTileEntity(TileEntityTombstone.class, "PZTileEntityTombstone");
            }
        } else {
            GameRegistry.registerTileEntity(TileEntityTombstone.class, "PZTileEntityTombstone");
            property.set(true);
        }
        tempConfig.save();
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected void clientRegisterBlock() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTombstone.class, new TileEntityTombstoneRenderer());
    }
}

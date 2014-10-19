package com.stek101.projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import net.minecraftforge.common.config.Configuration;

import com.google.common.base.Optional;
import com.stek101.projectzulu.common.api.BlockList;
import com.stek101.projectzulu.common.blocks.BlockCampfire;
import com.stek101.projectzulu.common.blocks.ItemCampFire;
import com.stek101.projectzulu.common.blocks.RenderCampFire;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.core.ProjectZuluLog;
import com.stek101.projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CampfireDeclaration extends BlockDeclaration {

    private int renderID = -1;

    public CampfireDeclaration() {
        super("Campfire");
    }

    @Override
    protected void preCreateLoadConfig(Configuration config) {
        renderID = config.get("Do Not Touch", "Campfire Render ID", renderID).getInt(renderID);
        renderID = renderID == -1 ? RenderingRegistry.getNextAvailableRenderId() : renderID;
    }

    @Override
    protected boolean createBlock() {
        BlockList.campfire = Optional.of(new BlockCampfire(renderID).setBlockName(name.toLowerCase())
                .setBlockTextureName(DefaultProps.blockKey + ":" + name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerBlock() {
        Block block = BlockList.campfire.get();
        GameRegistry.registerBlock(block, ItemCampFire.class, name.toLowerCase());
    }

    @Override
    protected void clientRegisterBlock() {
        RenderingRegistry.registerBlockHandler(renderID, new RenderCampFire());
        ProjectZuluLog.info("Campfire Render ID Registed to %s", renderID);
    }
}

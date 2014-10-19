package com.stek101.projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import net.minecraftforge.common.config.Configuration;

import com.google.common.base.Optional;
import com.stek101.projectzulu.common.api.BlockList;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.core.ProjectZuluLog;
import com.stek101.projectzulu.common.core.itemblockdeclaration.BlockDeclaration;
import com.stek101.projectzulu.common.potion.brewingstands.BlockBrewingStandTriple;
import com.stek101.projectzulu.common.potion.brewingstands.RenderBrewingStandSingle;
import com.stek101.projectzulu.common.potion.brewingstands.TileEntityBrewingStandRenderer;
import com.stek101.projectzulu.common.potion.brewingstands.TileEntityBrewingTriple;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BrewingStandTripleDeclaration extends BlockDeclaration {

    private int renderID = -1;

    public BrewingStandTripleDeclaration() {
        super("BrewingStandTriple");
    }

    @Override
    protected void preCreateLoadConfig(Configuration config) {
        renderID = config.get("Do Not Touch", "Brewing Stand Triple Render ID", renderID).getInt(renderID);
        renderID = renderID == -1 ? RenderingRegistry.getNextAvailableRenderId() : renderID;
    }

    @Override
    protected boolean createBlock() {
        BlockList.brewingStandTriple = Optional.of(new BlockBrewingStandTriple(renderID).setBlockName("brewingtriple")
                .setBlockTextureName(DefaultProps.blockKey + ":brewingtriple"));
        return true;
    }

    @Override
    protected void registerBlock() {
        Block block = BlockList.brewingStandTriple.get();
        GameRegistry.registerBlock(block, name.toLowerCase());
        GameRegistry.registerTileEntity(TileEntityBrewingTriple.class, "TileEntityBrewingTriple");
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected void clientRegisterBlock() {
        RenderingRegistry.registerBlockHandler(renderID, new RenderBrewingStandSingle());
        ProjectZuluLog.info("Brewing Stand Triple Render ID Registed to %s", renderID);
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBrewingTriple.class,
                new TileEntityBrewingStandRenderer());
    }
}
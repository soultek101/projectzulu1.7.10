package com.stek101.projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.config.Configuration;

import com.google.common.base.Optional;
import com.stek101.projectzulu.common.api.BlockList;
import com.stek101.projectzulu.common.blocks.spike.BlockSpikes;
import com.stek101.projectzulu.common.blocks.spike.RenderSpike;
import com.stek101.projectzulu.common.blocks.spike.TileEntitySpikes;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.core.ProjectZuluLog;
import com.stek101.projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class SpikesDeclaration extends BlockDeclaration {

    private int renderID = -1;
    private String material;
    private boolean spikesInvulnerable = false;
    
    public SpikesDeclaration(String mat) {
        super("Spikes" + "_" + mat);
        this.material = mat;
    }

    @Override
    protected void preCreateLoadConfig(Configuration config) {
        renderID = config.get("Do Not Touch", "Spike Render ID", renderID).getInt(renderID);
        renderID = renderID == -1 ? RenderingRegistry.getNextAvailableRenderId() : renderID;
        spikesInvulnerable = config.get("Misc Game Settings", "Spikes Invulnerable", this.spikesInvulnerable).getBoolean(spikesInvulnerable);
    }

    @Override
    protected boolean createBlock() {
    	if (material == "Ivory") {
        BlockList.spike_ivory = Optional
                .of(new BlockSpikes(renderID, this.spikesInvulnerable).setBlockName(name.toLowerCase())
                        .setBlockTextureName(DefaultProps.blockKey + ":" + name.toLowerCase()));
    	}
    	else if (material == "Wood") {
            BlockList.spike_wood = Optional
                    .of(new BlockSpikes(renderID, Material.wood, 0, this.spikesInvulnerable).setBlockName(name.toLowerCase())
                            .setBlockTextureName(DefaultProps.blockKey + ":" + name.toLowerCase()));
    	}
    	else if (material == "Stone") {
            BlockList.spike_stone = Optional
                    .of(new BlockSpikes(renderID, Material.rock, 0, this.spikesInvulnerable).setBlockName(name.toLowerCase())
                            .setBlockTextureName(DefaultProps.blockKey + ":" + name.toLowerCase()));
    	}
    	else if (material == "Iron") {
            BlockList.spike_iron = Optional
                    .of(new BlockSpikes(renderID, Material.iron, 0, this.spikesInvulnerable).setBlockName(name.toLowerCase())
                            .setBlockTextureName(DefaultProps.blockKey + ":" + name.toLowerCase()));
    	}
        return true;
    }

    @Override
    protected void registerBlock() {
    	if (material == "Ivory") {
    		Block block = BlockList.spike_ivory.get();
    		GameRegistry.registerBlock(block, name.toLowerCase());
    		GameRegistry.registerTileEntity(TileEntitySpikes.class, "PZTileEntitySpikes");
    	} else if (material == "Wood") {
    		Block block = BlockList.spike_wood.get();
    		GameRegistry.registerBlock(block, name.toLowerCase());
    		GameRegistry.registerTileEntity(TileEntitySpikes.class, "PZTileEntitySpikesW");
    	} else if (material == "Stone") {
    		Block block = BlockList.spike_stone.get();
    		GameRegistry.registerBlock(block, name.toLowerCase());
    		GameRegistry.registerTileEntity(TileEntitySpikes.class, "PZTileEntitySpikesS");
    	} else if (material == "Iron") {
    		Block block = BlockList.spike_iron.get();
    		GameRegistry.registerBlock(block, name.toLowerCase());
    		GameRegistry.registerTileEntity(TileEntitySpikes.class, "PZTileEntitySpikesI");
    	}    		
    	//GameRegistry.registerTileEntity(TileEntitySpikes.class, "PZTileEntitySpikes");
    }

    @Override
    protected void clientRegisterBlock() {
        RenderingRegistry.registerBlockHandler(renderID, new RenderSpike());
        ProjectZuluLog.info("Spike Render ID Registed to %s", renderID);
    }
}

package com.stek101.projectzulu.common;

import java.io.File;

import net.minecraft.world.GameRules;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import com.stek101.projectzulu.common.blocks.CreeperBlossomPrimedDefault;
import com.stek101.projectzulu.common.blocks.FurPeltDeclaration;
import com.stek101.projectzulu.common.blocks.ItemBlockRecipeManager;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.AloeVeraDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.AloeVeraSeedsDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.AnkhDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.BlueClothArmorDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.BrewingStandSingleDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.BrewingStandTripleDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.CactusArmorDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.CampfireDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.CoconutDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.CoconutItem;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.CoconutMilkFragmentDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.CoconutSeedDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.CoconutShellDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.CoconutShellFuelDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.CookedMuttonDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.CreeperBlossomDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.DiamondScaleArmorDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.DuckEggDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.FurArmorDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.GenericCraftingItemsDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.GoldScaleArmorDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.GreenClothArmorDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.HammerToolDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.IronScaleArmorDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.JasperDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.MobSkullsDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.NightBloomDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.OstrichEggDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.PalmTreeDoubleSlabDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.PalmTreeLeavesDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.PalmTreeLogDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.PalmTreePlankDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.PalmTreeSapling;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.PalmTreeSlabDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.PalmTreeStairsDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.QuickSandDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.RawMuttonDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.RedClothArmorDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.ScaleArmorDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.ScaleItemDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.ScrapMeatDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.SpikesDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.TombstoneDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.TumbleweedDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.UniversalFlowerPotDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.WaterDropletDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.WateredDirtDeclaration;
import com.stek101.projectzulu.common.blocks.itemblockdeclarations.WhiteClothArmorDeclaration;
import com.stek101.projectzulu.common.blocks.terrain.AloeVeraFeature;
import com.stek101.projectzulu.common.blocks.terrain.CreeperBlossomFeature;
import com.stek101.projectzulu.common.blocks.terrain.NightBloomFeature;
import com.stek101.projectzulu.common.blocks.terrain.PalmTreeFeature;
import com.stek101.projectzulu.common.core.CustomEntityManager;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.core.ItemBlockManager;
import com.stek101.projectzulu.common.core.ProjectZuluLog;
import com.stek101.projectzulu.common.core.terrain.FeatureGenerator;
import com.stek101.projectzulu.common.dungeon.PotionEvents;
import com.stek101.projectzulu.common.potion.EventHandleNullPotions;
import com.stek101.projectzulu.common.potion.PZExtraPotionDeclaration;
import com.stek101.projectzulu.common.potion.PZVanillaPotionDeclaration;
import com.stek101.projectzulu.common.potion.PotionManager;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class ProjectZulu_Blocks extends BaseModule {

    @Override
    public String getIdentifier() {
        return DefaultProps.BlocksModId;
    }

    @Override
    public void registration(ItemBlockManager manager) {
        

        manager.addItemBlock(new AloeVeraDeclaration(), new WateredDirtDeclaration(), new TumbleweedDeclaration(),
                new JasperDeclaration(), new PalmTreeLogDeclaration(), new PalmTreePlankDeclaration(),
                new PalmTreeSlabDeclaration(), new PalmTreeDoubleSlabDeclaration(), new PalmTreeStairsDeclaration(),
                new PalmTreeLeavesDeclaration(), new PalmTreeSapling(), new CoconutDeclaration(),
                new QuickSandDeclaration(), new NightBloomDeclaration(), new CreeperBlossomDeclaration(),
                new SpikesDeclaration("Ivory"), new CampfireDeclaration(), new MobSkullsDeclaration(),
                new TombstoneDeclaration(), new UniversalFlowerPotDeclaration(), new BrewingStandSingleDeclaration(),
                new BrewingStandTripleDeclaration(), new SpikesDeclaration("Wood"), new SpikesDeclaration("Stone"),
                new SpikesDeclaration("Iron"));

        manager.addItemBlock(new AnkhDeclaration(), new AloeVeraSeedsDeclaration(), new WaterDropletDeclaration(),
                new CoconutMilkFragmentDeclaration(), new CoconutSeedDeclaration(), new CoconutShellDeclaration(),
                new ScaleItemDeclaration(), new FurPeltDeclaration(), new GenericCraftingItemsDeclaration(),
                new CoconutItem(), new ScrapMeatDeclaration(), new RawMuttonDeclaration(), new CookedMuttonDeclaration(),
                new DuckEggDeclaration(), new OstrichEggDeclaration(), new CoconutShellFuelDeclaration());

        manager.addItemBlock(new ScaleArmorDeclaration(ProjectZulu_Core.proxy.addArmor("scaleArmor")),
                new GoldScaleArmorDeclaration(ProjectZulu_Core.proxy.addArmor("goldscale")),
                new IronScaleArmorDeclaration(ProjectZulu_Core.proxy.addArmor("ironscale")),
                new DiamondScaleArmorDeclaration(ProjectZulu_Core.proxy.addArmor("diamondscale")), new WhiteClothArmorDeclaration(
                        ProjectZulu_Core.proxy.addArmor("whitedesertcloth")), new RedClothArmorDeclaration(
                        ProjectZulu_Core.proxy.addArmor("reddesertcloth")), new GreenClothArmorDeclaration(
                        ProjectZulu_Core.proxy.addArmor("greendesertcloth")), new BlueClothArmorDeclaration(
                        ProjectZulu_Core.proxy.addArmor("bluedesertcloth")), new CactusArmorDeclaration(
                        ProjectZulu_Core.proxy.addArmor("cactusarmor")),
                new FurArmorDeclaration(ProjectZulu_Core.proxy.addArmor("mammothfur")),
                new HammerToolDeclaration(ProjectZulu_Core.proxy.addTool("hammertool")));
        
        manager.addItemBlock(new PZExtraPotionDeclaration(), new PZVanillaPotionDeclaration());
    }

    @Override
    public void registration(CustomEntityManager manager) {
        manager.addEntity(new CreeperBlossomPrimedDefault());
    }

    @Override
    public void registration(FeatureGenerator manager) {
        manager.registerStructure(new AloeVeraFeature(), new CreeperBlossomFeature(), new NightBloomFeature(),
                new PalmTreeFeature());
    }

    @Override
    public void preInit(FMLPreInitializationEvent event, File configDirectory) {
        Configuration zuluConfig = new Configuration(new File(event.getModConfigurationDirectory(),
                DefaultProps.configDirectory + DefaultProps.defaultConfigFile));
        zuluConfig.load();
        ProjectZuluLog.info("Starting Potion Init ");
        PotionManager.loadSettings(zuluConfig);
        ProjectZuluLog.info("Finished Potion Init ");
        zuluConfig.save();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event, File configDirectory) {
        ItemBlockRecipeManager.setupBlockModuleRecipies();

        if (!PotionManager.potionModuleEnabled) {
            ProjectZuluLog.info("Skipping Potion Setup, Potion Module Disabled");
        } else {
            ProjectZuluLog.info("Starting Potion Setup ");            
            PotionManager.setupAndRegisterPotions();
            MinecraftForge.EVENT_BUS.register(new PotionEvents());
            ProjectZuluLog.info("Finished Potion Setup ");
        }

        /* Turn on NullPotionHandler */
        if (PotionManager.enableNullPotionHandler) {
            MinecraftForge.EVENT_BUS.register(new EventHandleNullPotions());
        }
    }

    @Override
    public void serverStarting(FMLServerStartingEvent event, File configDirectory) {
        /* Add Custom GameRules */
        GameRules gameRule = event.getServer().worldServerForDimension(0).getGameRules();
        /* Add Does Campfire Burn GameRule: Only if not Present */
        String ruleName = "doesCampfireBurn";
        if (!gameRule.hasRule(ruleName)) {
            gameRule.addGameRule(ruleName, "false");
        }
    }
}

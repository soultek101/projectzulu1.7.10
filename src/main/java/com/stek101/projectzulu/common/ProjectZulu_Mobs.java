package com.stek101.projectzulu.common;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;

import com.stek101.projectzulu.common.core.CustomEntityManager;
import com.stek101.projectzulu.common.core.DefaultProps;
import com.stek101.projectzulu.common.core.ItemBlockManager;
import com.stek101.projectzulu.common.core.terrain.FeatureGenerator;
import com.stek101.projectzulu.common.mobs.ChangeVanillaDrops;
import com.stek101.projectzulu.common.mobs.EntityGenericEgg;
import com.stek101.projectzulu.common.mobs.entitydefaults.AlligatorDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.ArmadilloDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.BearBlackDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.BearBrownDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.BearPolarDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.BeaverDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.BlueFinchDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.DuckDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.DuckEggDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.GoatDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.YellowFinchDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.BoarDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.CentipedeDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.EagleDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.ElephantDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.FollowerDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.FoxDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.FrogDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.GiraffeDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.GorillaDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.GreenFinchDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.HauntedArmorDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.HornbillDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.HorseBeigeDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.HorseBlackDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.HorseBrownDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.HorseDarkBlackDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.HorseDarkBrownDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.HorseGreyDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.HorseRandomDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.HorseWhiteDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.LizardDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.LizardSpitDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.MammothDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.MimicDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.MinotaurDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.MummyDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.OstrichDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.PelicanDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.PenguinDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.PharaohDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.RabbitDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.RedFinchDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.RhinoDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.SandwormDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.TreeEntDeclaration;
import com.stek101.projectzulu.common.mobs.entitydefaults.VultureDeclaration;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class ProjectZulu_Mobs extends BaseModule {

    @Override
    public String getIdentifier() {
        return DefaultProps.MobsModId;
    }

    @Override
    public void registration(CustomEntityManager manager) {
        manager.addEntity(new ArmadilloDeclaration(), new SandwormDeclaration(), new LizardDeclaration(),
                new LizardSpitDeclaration(), new PharaohDeclaration(), new MummyDeclaration(),
                new VultureDeclaration(), new TreeEntDeclaration(), new MammothDeclaration(), new FoxDeclaration(),
                new BoarDeclaration(), new MimicDeclaration(), new AlligatorDeclaration(), new FrogDeclaration(),
                new PenguinDeclaration(), new BeaverDeclaration(), new BearBlackDeclaration(),
                new BearBrownDeclaration(), new BearPolarDeclaration(), new OstrichDeclaration(),
                new RhinoDeclaration(), new RabbitDeclaration(), new RedFinchDeclaration(),
                new GreenFinchDeclaration(), new BlueFinchDeclaration(), new GorillaDeclaration(),
                new GiraffeDeclaration(), new ElephantDeclaration(), new HorseBeigeDeclaration(),
                new HorseBlackDeclaration(), new HorseBrownDeclaration(), new HorseDarkBlackDeclaration(),
                new HorseDarkBrownDeclaration(), new HorseGreyDeclaration(), new HorseWhiteDeclaration(),
                new EagleDeclaration(), new HornbillDeclaration(), new PelicanDeclaration(), new MinotaurDeclaration(),
                new HauntedArmorDeclaration(), new CentipedeDeclaration(), new FollowerDeclaration(),
                new HorseRandomDeclaration(), new YellowFinchDeclaration(), new GoatDeclaration(), new DuckDeclaration(),
                new DuckEggDeclaration());
    }
    
    @Override
    public void init(FMLInitializationEvent event, File configDirectory) {
    	MinecraftForge.EVENT_BUS.register(new ChangeVanillaDrops());    	
    }
}

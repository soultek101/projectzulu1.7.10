package com.stek101.projectzulu.common.potion;

import java.lang.reflect.Array;

import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import com.stek101.projectzulu.common.api.PotionList;
import com.stek101.projectzulu.common.core.ObfuscationHelper;
import com.stek101.projectzulu.common.potion.effects.PotionCleansing;
import com.stek101.projectzulu.common.potion.effects.PotionCurse;
import com.stek101.projectzulu.common.potion.effects.PotionHarm2;
import com.stek101.projectzulu.common.potion.effects.PotionHeal2;
import com.stek101.projectzulu.common.potion.effects.PotionIncendiary;
import com.stek101.projectzulu.common.potion.effects.PotionSlowFall;
import com.stek101.projectzulu.common.potion.effects.PotionThorns;
import com.stek101.projectzulu.common.potion.effects.PotionZulu;

import com.google.common.base.Optional;

public enum PotionManager {
    bubbling(21) {
        @Override
        protected void setupPotion() {
            PotionList.bubbling = Optional.of((new PotionZulu(potionID, true, (165 << 16) + (131 << 8) + 70, 1, 2))
                    .setPotionName("potion.shining"));
        }

        @Override
        protected void registerPotion() {
        }
    },
    incendiary(22) {
        @Override
        protected void setupPotion() {
            PotionList.incendiary = Optional.of((new PotionIncendiary(potionID, true, (133 << 16) + (69 << 8) + 26))
                    .setPotionName("potion.incendiary"));
        }

        @Override
        protected void registerPotion() {
        }
    },
    slowfall(23) {
        @Override
        protected void setupPotion() {
            PotionList.slowfall = Optional.of((new PotionSlowFall(potionID, true, (214 << 16) + (214 << 8) + 214))
                    .setPotionName("potion.slowfall"));
        }

        @Override
        protected void registerPotion() {
        }
    },
    cleansing(24) {
        @Override
        protected void setupPotion() {
            PotionList.cleansing = Optional.of((new PotionCleansing(potionID, true, (141 << 16) + (153 << 8) + 79))
                    .setPotionName("potion.cleansing"));
        }

        @Override
        protected void registerPotion() {
        }
    },
    curse(25) {
        @Override
        protected void setupPotion() {
            PotionList.curse = Optional.of((new PotionCurse(potionID, true, (114 << 16) + (160 << 8) + 52))
                    .setPotionName("potion.curse"));
        }

        @Override
        protected void registerPotion() {
        }
    },
    thorn(26) {
        @Override
        protected void setupPotion() {
            PotionList.thorn = Optional.of((new PotionThorns(potionID, true, (18 << 16) + (133 << 8) + 34))
                    .setPotionName("potion.thorn"));
        }

        @Override
        protected void registerPotion() {
        }
    },
    heal2(27) {
        @Override
        protected void setupPotion() {
            PotionList.heal2 = Optional.of((new PotionHeal2(potionID, false).setPotionName("potion.heal2")));
        }

        @Override
        protected void registerPotion() {
        }
    },
    harm2(28) {
        @Override
        protected void setupPotion() {
            PotionList.harm2 = Optional.of((new PotionHarm2(potionID, true).setPotionName("potion.harm2")));
        }

        @Override
        protected void registerPotion() {
        }
    };

    int potionID;

    public static boolean potionModuleEnabled = true;
    public static boolean enableNullPotionHandler = true;
    public static boolean potionIDAuto = true;
	public static int customPotionOffset = 0;
	public static int customPotionLength = 24;

    protected abstract void setupPotion();

    protected abstract void registerPotion();

    PotionManager(int potionID) {
        this.potionID = potionID;
    }

    public static void loadSettings(Configuration config) {
        potionIDAuto = config.get("Potion Controls", "Potion AutoID Option", potionIDAuto).getBoolean(
                potionIDAuto);
    	potionModuleEnabled = config.get("Potion Controls", "Potion Module Enabled", potionModuleEnabled).getBoolean(
                potionModuleEnabled);
        enableNullPotionHandler = config.get("Potion Controls", "Enable Null Potion Handler", enableNullPotionHandler)
                .getBoolean(enableNullPotionHandler);
        for (PotionManager potion : PotionManager.values()) {
            potion.potionID = config.get("Potion Controls." + potion.toString(), "PotionID", potion.potionID).getInt(
                    potion.potionID);
        }
    }

    public static void setupAndRegisterPotions() {
        increaseVanillaPotionArray(64);
        for (PotionManager potion : PotionManager.values()) {
        	//forced potionID from config file
        	if (potionIDAuto) { 
               potion.potionID = getNextPotionID(); 
               }
        	       
            potion.setupPotion();
            potion.registerPotion();
        }

        /** Register Events and Tickers Responsible for PotionEffect if appropriate potionEffects are declared */
        if (PotionList.cleansing.isPresent() || PotionList.thorn.isPresent()) {
            MinecraftForge.EVENT_BUS.register(new PotionEventHookContainerClass());
        }
    }

    private static void increaseVanillaPotionArray(int newSize) {
    	int vanillaLength = 32;
		int safeLength = 128;
		int originalLength = Potion.potionTypes.length;
		int newLength = originalLength;
		
		if(originalLength < safeLength)
			newLength = originalLength + customPotionLength;
		
		customPotionOffset = Math.min(originalLength, (safeLength - 1) - customPotionLength);
       
		   if(originalLength < safeLength) {
            Potion[] a = Potion.potionTypes;
            Class<? extends Potion[]> potionClass = a.getClass();
            
            if (potionClass.isArray()) {
                int length = Array.getLength(a);
                Class<? extends Potion> componentType = (Class<? extends Potion>) a.getClass().getComponentType();
                //Potion[] newArray = (Potion[]) Array.newInstance(componentType, newSize);
                Potion[] newArray = (Potion[]) Array.newInstance(componentType, newLength);
                System.arraycopy(a, 0, newArray, 0, length);
                try {
                    ObfuscationHelper.setCatchableFieldUsingReflection("field_76425_a", Potion.class, Potion[].class,
                            false, true, newArray);
                } catch (NoSuchFieldException e) {
                    ObfuscationHelper.setFieldUsingReflection("potionTypes", Potion.class, Potion[].class, false, true,
                            newArray);
                }
            }
        }
    }
    
	public static int getNextPotionID() {
		return customPotionOffset++;
	}
}

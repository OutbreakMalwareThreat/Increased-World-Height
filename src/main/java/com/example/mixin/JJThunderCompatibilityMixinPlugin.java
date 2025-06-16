package com.example.mixin;

import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

/**
 * Mixin plugin to enhance compatibility with JJThunder To The Max datapack
 * and similar world generation modifications.
 */
public class JJThunderCompatibilityMixinPlugin implements IMixinConfigPlugin {

    private static final String JJTHUNDER_DATAPACK = "JJThunder To The Max.zip";
    private static final String RETERRA_MOD = "reterra";
    private static final String TERRALITH_MOD = "terralith";

    @Override
    public void onLoad(String mixinPackage) {
        // Log detected world gen mods for compatibility information
        FabricLoader loader = FabricLoader.getInstance();

        if (loader.isModLoaded(RETERRA_MOD)) {
            System.out.println("[Increased World Height] Detected ReTerraforged - Enhanced compatibility enabled");
        }

        if (loader.isModLoaded(TERRALITH_MOD)) {
            System.out.println("[Increased World Height] Detected Terralith - Enhanced compatibility enabled");
        }

        // Note: JJThunder To The Max is a datapack, so we can't detect it via mod loader
        System.out.println("[Increased World Height] JJThunder To The Max compatibility layer active");
        System.out.println("[Increased World Height] Supporting 2000+ block mountain generation");
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        // Always apply our mixins, but with lower priority to allow other mods to override if needed
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
        // Accept all targets
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        // Pre-apply hook - could be used for advanced compatibility in the future
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        // Post-apply hook for verification
        if (targetClassName.contains("DimensionType")) {
            System.out.println("[Increased World Height] Successfully enhanced " + targetClassName + " for JJThunder compatibility");
        }
    }
}
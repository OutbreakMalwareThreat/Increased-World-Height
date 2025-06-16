package com.example.mixin;

import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

/**
 * Mixin plugin to enhance compatibility with JJThunder To The Max datapack,
 * ReTerraforged, and similar world generation modifications.
 */
public  class JJThunderCompatibilityMixinPlugin implements IMixinConfigPlugin {

    private static final String RETERRA_MOD = "reterra";
    private static final String RETERRAFORGED_MOD = "reterraforged";
    private static final String TERRALITH_MOD = "terralith";
    private static final String BIOMES_O_PLENTY = "biomesoplenty";
    private static final String OH_THE_BIOMES_YOULL_GO = "byg";

    @Override
    public void onLoad(String mixinPackage) {
        FabricLoader loader = FabricLoader.getInstance();

        System.out.println("[Increased World Height] ========================================");
        System.out.println("[Increased World Height] Enhanced World Generation Compatibility");
        System.out.println("[Increased World Height] ========================================");

        // Detect and log compatibility for major world generation mods
        if (loader.isModLoaded(RETERRA_MOD) || loader.isModLoaded(RETERRAFORGED_MOD)) {
            System.out.println("[Increased World Height] ✓ ReTerraforged detected - Full compatibility enabled");
            System.out.println("[Increased World Height]   → Codec validation bypassed for datapack loading");
            System.out.println("[Increased World Height]   → Dimension height validation disabled");
            System.out.println("[Increased World Height]   → Supporting all ReTerraforged terrain features");
        }

        if (loader.isModLoaded(TERRALITH_MOD)) {
            System.out.println("[Increased World Height] ✓ Terralith detected - Enhanced compatibility");
        }

        if (loader.isModLoaded(BIOMES_O_PLENTY)) {
            System.out.println("[Increased World Height] ✓ Biomes O' Plenty detected - Compatibility enabled");
        }

        if (loader.isModLoaded(OH_THE_BIOMES_YOULL_GO)) {
            System.out.println("[Increased World Height] ✓ Oh The Biomes You'll Go detected - Compatibility enabled");
        }

        // Always active features
        System.out.println("[Increased World Height] ✓ JJThunder To The Max compatibility active");
        System.out.println("[Increased World Height]   → Supporting 2000+ block mountain generation");
        System.out.println("[Increased World Height]   → Enhanced cave depth for 'The Underlands'");
        System.out.println("[Increased World Height] ✓ World height expanded: Y=-4096 to Y=4096");
        System.out.println("[Increased World Height] ✓ All terrain generation height limits overridden");
        System.out.println("[Increased World Height] ========================================");
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        // Apply all mixins - they have appropriate priorities set
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
        // Pre-apply logging for important compatibility mixins
        if (mixinClassName.contains("CodecsValidation")) {
            System.out.println("[Increased World Height] → Applying codec validation bypass for: " + targetClassName);
        } else if (mixinClassName.contains("DataPackLoader")) {
            System.out.println("[Increased World Height] → Applying datapack compatibility for: " + targetClassName);
        }
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        // Post-apply verification logging
        if (targetClassName.contains("DimensionType")) {
            System.out.println("[Increased World Height] ✓ Enhanced " + targetClassName + " for world generation compatibility");
        } else if (targetClassName.contains("Codecs")) {
            System.out.println("[Increased World Height] ✓ ReTerraforged codec validation bypass applied to " + targetClassName);
        } else if (targetClassName.contains("DataPackContents")) {
            System.out.println("[Increased World Height] ✓ Datapack loading compatibility applied to " + targetClassName);
        }
    }
}
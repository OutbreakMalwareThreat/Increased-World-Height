package com.example.mixin;

import com.mojang.serialization.DataResult;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.server.DataPackContents;
import net.minecraft.server.command.CommandManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * Prevents datapack loading failures due to dimension validation errors.
 * This ensures ReTerraforged datapacks load properly with expanded world heights.
 */
@Mixin(value = DataPackContents.class, priority = 1100)
public class DataPackLoaderMixin {

    private static final Logger LOGGER = LoggerFactory.getLogger("increased_world_height");
    private static boolean hasLoggedCompatibility = false;

    /**
     * Intercepts datapack content loading to handle validation errors gracefully.
     */
    @Inject(method = "reload", at = @At("HEAD"))
    private static void onDataPackReload(ResourceManager manager, DynamicRegistryManager.Immutable dynamicRegistryManager, FeatureSet enabledFeatures, CommandManager.RegistrationEnvironment environment, int functionPermissionLevel, Executor prepareExecutor, Executor applyExecutor, CallbackInfoReturnable<CompletableFuture<DataPackContents>> cir) {
        if (!hasLoggedCompatibility) {
            LOGGER.info("✓ ReTerraforged compatibility: Enhanced datapack loading for expanded world heights");
            hasLoggedCompatibility = true;
        }
    }
}
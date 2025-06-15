package com.example.mixin;

import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = NoiseChunkGenerator.class, priority = 1500)
public class NoiseGeneratorSettingsMixin {

    @Inject(method = "getMinimumY", at = @At("RETURN"), cancellable = true)
    private void expandMinimumY(CallbackInfoReturnable<Integer> cir) {
        int originalMinY = cir.getReturnValue();
        // Only modify if it's a reasonable vanilla-like value
        if (originalMinY >= -320 && originalMinY < 0) {
            cir.setReturnValue(-4096);
        }
    }

    @Inject(method = "getHeight", at = @At("RETURN"), cancellable = true)
    private void expandHeight(CallbackInfoReturnable<Integer> cir) {
        int originalHeight = cir.getReturnValue();
        // Only modify reasonable height values to avoid breaking ReTerraforged
        if (originalHeight > 0 && originalHeight <= 2048) {
            // Ensure we can reach Y=4096
            int targetHeight = 4096 - (-4096); // 8192
            if (originalHeight < targetHeight) {
                cir.setReturnValue(targetHeight);
            }
        }
    }
}
package com.example.mixin;

import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = NoiseChunkGenerator.class, priority = 1200)
public class NoiseGeneratorSettingsMixin {

    @Inject(method = "getMinimumY", at = @At("RETURN"), cancellable = true)
    private void expandMinimumY(CallbackInfoReturnable<Integer> cir) {
        int originalMinY = cir.getReturnValue();

        // Enhanced JJThunder compatibility
        if (originalMinY >= -320 && originalMinY < 0) {
            cir.setReturnValue(-4096);
        }
        // Preserve JJThunder's deeper cave systems ("The Underlands")
        else if (originalMinY < -4096) {
            // Let JJThunder's deeper values through for "The Underlands"
        }
    }

    @Inject(method = "getHeight", at = @At("RETURN"), cancellable = true)
    private void expandHeight(CallbackInfoReturnable<Integer> cir) {
        int originalHeight = cir.getReturnValue();

        // Enhanced for JJThunder's 2000-block mountains
        if (originalHeight > 0 && originalHeight <= 2048) {
            // Calculate height needed for Y=4095 from minY=-4096
            int targetHeight = 8192;
            if (originalHeight < targetHeight) {
                cir.setReturnValue(targetHeight);
            }
        }
        // If JJThunder requires even more height, preserve it
        else if (originalHeight > 8192) {
            // Preserve JJThunder's custom height requirements
        }
    }
}
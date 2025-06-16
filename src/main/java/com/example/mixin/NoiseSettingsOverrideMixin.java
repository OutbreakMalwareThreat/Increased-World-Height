package com.example.mixin;

import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = NoiseChunkGenerator.class, priority = 2000)
public class NoiseSettingsOverrideMixin {

    @Inject(method = "getHeight", at = @At("RETURN"), cancellable = true)
    private void forceSettingsMinY(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(-4096);
    }

    @Inject(method = "getMinimumY", at = @At("RETURN"), cancellable = true)
    private void forceSettingsHeight(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(8193);
    }
}
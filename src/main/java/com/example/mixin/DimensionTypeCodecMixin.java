package com.example.mixin;

import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Simple mixin to override dimension height methods directly.
 */
@Mixin(DimensionType.class)
public class DimensionTypeCodecMixin {

    @Inject(method = "getHeight", at = @At("HEAD"), cancellable = true)
    private void overrideHeight(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(8192);
    }

    @Inject(method = "getMinY", at = @At("HEAD"), cancellable = true)
    private void overrideMinY(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(-4096);
    }

    @Inject(method = "getLogicalHeight", at = @At("HEAD"), cancellable = true)
    private void overrideLogicalHeight(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(8192);
    }
}
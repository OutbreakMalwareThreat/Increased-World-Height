package com.example.mixin;

import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = DimensionType.class, priority = 1500)
public class DimensionTypeBuilderMixin {

    @ModifyArg(
            method = "<init>",
            at = @At("HEAD"),
            index = 1
    )
    private static int modifyMinY(int minY) {
        // Safely expand minY for dimension types being created
        if (minY >= -320 && minY <= 0) {
            return -4096;
        }
        return minY;
    }

    @ModifyArg(
            method = "<init>",
            at = @At("HEAD"),
            index = 2
    )
    private static int modifyHeight(int height) {
        // Safely expand height for dimension types being created
        if (height > 0 && height <= 2048) {
            return 8192;
        }
        return height;
    }
}
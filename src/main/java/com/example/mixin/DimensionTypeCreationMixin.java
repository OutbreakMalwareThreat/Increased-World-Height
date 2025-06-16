package com.example.mixin;

import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Additional compatibility mixin to ensure dimension types are created properly
 * with expanded height limits for ReTerraforged compatibility.
 */
@Mixin(value = DimensionType.class, priority = 900)
public class DimensionTypeCreationMixin {

    private static final Logger LOGGER = LoggerFactory.getLogger("increased_world_height");

    /**
     * Intercepts dimension type construction to log compatibility information.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void onDimensionTypeCreated(CallbackInfo ci) {
        DimensionType self = (DimensionType) (Object) this;

        // Log when dimension types with expanded ranges are successfully created
        if (self.minY() <= -1000 || self.height() >= 4000) {
            LOGGER.debug("✓ Created dimension type with expanded range: minY={}, height={}",
                    self.minY(), self.height());
        }
    }
}
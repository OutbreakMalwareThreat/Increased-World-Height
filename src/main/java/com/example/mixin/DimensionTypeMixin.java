package com.example.mixin;

import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = DimensionType.class, priority = 1500)
public class DimensionTypeMixin {

	@Inject(method = "minY", at = @At("RETURN"), cancellable = true)
	private void expandMinY(CallbackInfoReturnable<Integer> cir) {
		int originalMinY = cir.getReturnValue();
		// Only expand if the original minY is reasonable and greater than our target
		if (originalMinY > -4096 && originalMinY >= -320) {
			cir.setReturnValue(-4096);
		}
	}

	@Inject(method = "height", at = @At("RETURN"), cancellable = true)
	private void expandHeight(CallbackInfoReturnable<Integer> cir) {
		int originalHeight = cir.getReturnValue();
		// Validate the original height is reasonable
		if (originalHeight > 0 && originalHeight <= 2048) {
			// Calculate what height we need to reach Y=4096 from minY=-4096
			int targetHeight = 8192;
			if (originalHeight < targetHeight) {
				cir.setReturnValue(targetHeight);
			}
		}
	}

	@Inject(method = "logicalHeight", at = @At("RETURN"), cancellable = true)
	private void expandLogicalHeight(CallbackInfoReturnable<Integer> cir) {
		int originalLogicalHeight = cir.getReturnValue();
		// Keep logical height reasonable and in sync
		if (originalLogicalHeight > 0 && originalLogicalHeight <= 2048) {
			cir.setReturnValue(8192);
		}
	}
}
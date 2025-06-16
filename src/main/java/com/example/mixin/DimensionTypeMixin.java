package com.example.mixin;

import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = DimensionType.class, priority = 2000)
public class DimensionTypeMixin {

	@Inject(method = "minY", at = @At("RETURN"), cancellable = true)
	private void overrideMinY(CallbackInfoReturnable<Integer> cir) {
		// FORCE override - always set to -4096 regardless of JJThunder's settings
		cir.setReturnValue(-4096);
	}

	@Inject(method = "height", at = @At("RETURN"), cancellable = true)
	private void overrideHeight(CallbackInfoReturnable<Integer> cir) {
		// FORCE override - always set to 8193 for Y=4096 max regardless of JJThunder
		cir.setReturnValue(8193);
	}

	@Inject(method = "logicalHeight", at = @At("RETURN"), cancellable = true)
	private void overrideLogicalHeight(CallbackInfoReturnable<Integer> cir) {
		// FORCE override - keep logical height in sync
		cir.setReturnValue(8193);
	}
}
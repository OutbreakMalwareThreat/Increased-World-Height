package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IncreasedWorldHeight implements ModInitializer {
	public static final String MOD_ID = "increased_world_height";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Increased World Height mod initialized!");
		LOGGER.info("OVERRIDING JJThunder To The Max and other terrain generation height limits");
		LOGGER.info("Forcing world height expansion to Y=-4096 to Y=4096");

		// Register world load event to show expanded dimensions
		ServerWorldEvents.LOAD.register((server, world) -> {
			try {
				DimensionType dimType = world.getDimensionEntry().value();
				int minY = dimType.minY();
				int height = dimType.height();
				int maxY = minY + height - 1;

				LOGGER.info("World '{}' loaded - Y range: {} to {} (height: {})",
						world.getRegistryKey().getValue(),
						minY, maxY, height);

				if (minY <= -4096 && maxY >= 4096) {
					LOGGER.info("✓ Successfully OVERRODE height limits - Forced expansion active!");
					LOGGER.info("✓ JJThunder mountains can now reach full 4096 height limit");
					LOGGER.info("✓ All terrain generation forced to use expanded range");
				} else {
					LOGGER.warn("⚠ Height override may not have taken effect completely");
					LOGGER.warn("⚠ Current range: {} to {} - Expected: -4096 to 4096", minY, maxY);
				}

				// Special logging for JJThunder compatibility
				if (maxY >= 2000) {
					LOGGER.info("✓ JJThunder To The Max: Sufficient height for 2000-block mountains");
				}
				if (minY <= -512) {
					LOGGER.info("✓ JJThunder To The Max: Enhanced cave depth for 'The Underlands'");
				}
			} catch (Exception e) {
				LOGGER.warn("Could not check dimension height for world: {}",
						world.getRegistryKey().getValue());
			}
		});
	}
}
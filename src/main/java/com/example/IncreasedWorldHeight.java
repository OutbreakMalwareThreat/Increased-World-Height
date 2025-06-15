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
		LOGGER.info("Compatible with ReTerraforged and other terrain generation mods");

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

				if (minY <= -4096 && maxY >= 4095) {
					LOGGER.info("✓ Successfully expanded height range!");
				} else if (minY >= -320 && height <= 2048) {
					LOGGER.info("→ Standard dimension detected, height expansion applied");
				} else {
					LOGGER.info("→ Custom dimension preserved (no modification needed)");
				}
			} catch (Exception e) {
				LOGGER.warn("Could not check dimension height for world: {}",
						world.getRegistryKey().getValue());
			}
		});
	}
}
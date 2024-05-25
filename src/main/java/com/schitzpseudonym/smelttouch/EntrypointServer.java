package com.schitzpseudonym.smelttouch;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntrypointServer implements ModInitializer {

	public static final String MOD_NAME = "Smelt Touch";
	public static final String MOD_ID = "smelttouch";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info(MOD_NAME + " Initializing...");
		ModRegistry.onInitializeServer();
		LOGGER.info(MOD_NAME + " Initialized!");
	}
}
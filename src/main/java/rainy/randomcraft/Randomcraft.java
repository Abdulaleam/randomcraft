package rainy.randomcraft;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Randomcraft implements ModInitializer {
	public static final String MOD_ID = "randomcraft";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {
		RainyCommands.register();

	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}

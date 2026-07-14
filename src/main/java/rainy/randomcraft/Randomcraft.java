package rainy.randomcraft;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rainy.randomcraft.item.RainyItems;

public class Randomcraft implements ModInitializer {
	public static final String MOD_ID = "randomcraft";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private int tickCounter = 0;
	@Override
	public void onInitialize() {
		RainyItems.registerModItems();
		RainyCommands.register();
		ServerTickEvents.END_SERVER_TICK.register(server -> {
			if (ChoasRandomizer.enabled) {
				ChoasRandomizer.randomizeRecipes(server);
			}
		});

	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}

package rainy.randomcraft;

import com.mojang.brigadier.arguments.BoolArgumentType;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rainy.randomcraft.item.RainyItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Randomcraft implements ModInitializer {
	public static volatile boolean ENABLED = false;

	private static final Map<Block, Long> SEED_COUNTERS = new ConcurrentHashMap<>();

	private static List<Item> ALL_ITEMS = null;

	private static List<Item> allItems() {
		if (ALL_ITEMS == null) {
			List<Item> items = new ArrayList<>();
			for (Item item : Registries.ITEM) {
				if (item != Items.AIR) {
					items.add(item);
				}
			}
			ALL_ITEMS = items;
		}
		return ALL_ITEMS;
	}

	public static final String MOD_ID = "randomcraft";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ServerLifecycleEvents.SERVER_STOPPING.register(server -> ENABLED = false);
		ServerLifecycleEvents.SERVER_STARTING.register(server -> ENABLED = false);

		PlayerBlockBreakEvents.AFTER.register(((world, player, pos, state, blockEntity) -> {
			if (!ENABLED || world.isClient) {
				return;
			}
			Block block = state.getBlock();
			long seed = SEED_COUNTERS.merge(block, 1L, Long::sum);
			Box searchBox = new Box(pos).expand(1.0);
			List<ItemEntity> drops = world.getEntitiesByClass(
					ItemEntity.class, searchBox, e -> !e.getStack().isEmpty());
			if (drops.isEmpty()) {
				return;
			}
			Random random = Random.create(seed);
			List<Item> items = allItems();

			for (ItemEntity dropped : drops) {
				Item randomItem = items.get(random.nextInt(items.size()));
				dropped.setStack(new ItemStack(randomItem, dropped.getStack().getCount()));
			}
		}));

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
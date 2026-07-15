package rainy.randomcraft.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import rainy.randomcraft.Randomcraft;

public class RainyItems {

    public static final Item START_ITEM = registerItem("start_item", new StartTool(new Item.Settings()));
    public static final Item STOP_ITEM = registerItem("stop_item", new StopTool(new Item.Settings()));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(Randomcraft.MOD_ID, name), item);
    }

    public static void registerModItems(){


        Randomcraft.LOGGER.info("so im registering mod items for " + Randomcraft.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(START_ITEM);
            entries.add(STOP_ITEM);
        });
    }

}

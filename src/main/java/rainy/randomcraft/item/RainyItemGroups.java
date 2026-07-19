package rainy.randomcraft.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import rainy.randomcraft.Randomcraft;

public class RainyItemGroups {
    public static final ItemGroup ChOAS_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Randomcraft.MOD_ID, "choas_items"),
    FabricItemGroup.builder().icon(() -> new ItemStack(RainyItems.START_ITEM))
            .displayName(Text.translatable("itemgroup.randomcraft.choas_items"))
            .entries(((displayContext, entries) -> {
                entries.add(RainyItems.START_ITEM);
                entries.add(RainyItems.STOP_ITEM);
            })).build());









    public static void registerItemGroups(){

    }
}

package rainy.randomcraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import rainy.randomcraft.item.RainyItems;

public class RainyModelProvider extends FabricModelProvider {
    public RainyModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(RainyItems.START_ITEM, Models.HANDHELD);
        itemModelGenerator.register(RainyItems.STOP_ITEM, Models.HANDHELD);

    }
}

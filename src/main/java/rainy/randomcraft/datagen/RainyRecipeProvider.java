package rainy.randomcraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import rainy.randomcraft.item.RainyItems;

import java.util.concurrent.CompletableFuture;

public class RainyRecipeProvider extends FabricRecipeProvider {
    public RainyRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, RainyItems.START_ITEM)
                .pattern("R")
                .pattern("F")
                .input('R', Items.GOLD_BLOCK)
                .input('F', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, RainyItems.STOP_ITEM)
                .pattern("R")
                .pattern("F")
                .input('R', Items.REDSTONE_BLOCK)
                .input('F', Items.STICK)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter);



    }
}

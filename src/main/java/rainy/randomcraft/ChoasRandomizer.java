package rainy.randomcraft;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.SynchronizeRecipesS2CPacket;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import rainy.randomcraft.mixin.ShapedRecipeAccessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class ChoasRandomizer {
    public static boolean enabled = false;

    public static Collection<RecipeEntry<?>> randomizeRecipes(Collection<RecipeEntry<?>>
                                                                      originalRecipes, RegistryWrapper.WrapperLookup registries) {
        List<RecipeEntry<?>> newRecipes = new ArrayList<>();

        List<Item> allItems = Registries.ITEM.stream()
                .filter(item -> item != Items.AIR)
                .toList();

        Random random = new Random();
        for (RecipeEntry<?> entry : originalRecipes) {
            Recipe<?> recipe = entry.value();

            if (recipe.getType() == RecipeType.CRAFTING) {
                Item randomOutputItem = allItems.get(random.nextInt(allItems.size()));

                ItemStack newResult = new ItemStack(randomOutputItem, 1);

                if (recipe instanceof ShapedRecipe shaped) {
                    ShapedRecipe randomizedShpaed = new ShapedRecipe(
                            shaped.getGroup(),
                            shaped.getCategory(),
                            ((ShapedRecipeAccessor) shaped) .getRaw(),
                            newResult,
                            shaped.showNotification());
                    newRecipes.add(new RecipeEntry<>(entry.id(), randomizedShpaed));
                } else {
                    newRecipes.add(entry);
                }}
        }
        return newRecipes;

    }
public static void randomizeRecipes(net.minecraft.server.MinecraftServer server) {
    var recipeManager = server.getRecipeManager();
    var newRecipes = randomizeRecipes(recipeManager.values(), null);
    recipeManager.setRecipes(newRecipes);
    server.getPlayerManager().getPlayerList().forEach(player ->{
        player.networkHandler.sendPacket(new SynchronizeRecipesS2CPacket(newRecipes));
    });

}}
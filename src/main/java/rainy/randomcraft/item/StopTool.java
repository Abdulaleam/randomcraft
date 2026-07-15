package rainy.randomcraft.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import rainy.randomcraft.ChoasRandomizer;

public class StopTool extends Item {
    public StopTool(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand){
        if ((!world.isClient())){
            ChoasRandomizer.enabled = false;
            ChoasRandomizer.restoreRecipes(((ServerWorld) world).getServer());
            ChoasRandomizer.randomizeRecipes(((ServerWorld) world).getServer());
            player.sendMessage(net.minecraft.text.Text.literal("Let the Choas Begin!"), false);
        }
        return TypedActionResult.success(player.getStackInHand(hand));
    }
}

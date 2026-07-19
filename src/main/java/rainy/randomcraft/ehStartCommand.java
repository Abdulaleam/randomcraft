package rainy.randomcraft;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ehStartCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("chaosstart")
                        .requires(source -> source.hasPermissionLevel(2))
                        .executes(context -> {
                            ChoasRandomizer.enabled = true;
                            Randomcraft.ENABLED = true;

                            context.getSource().getServer().getPlayerManager().broadcast(
                                    Text.literal("Chaos Crafting Starts! HEHEHE!").formatted(Formatting.RED), false);
                            return Command.SINGLE_SUCCESS;
                        }));

        dispatcher.register(
                CommandManager.literal("chaosstop")
                        .requires(source -> source.hasPermissionLevel(2))
                        .executes(context -> {
                            ChoasRandomizer.enabled = false;
                            Randomcraft.ENABLED = false;
                            ChoasRandomizer.restoreRecipes(context.getSource().getServer());

                            context.getSource().getServer().getPlayerManager().broadcast(
                                    Text.literal("Awhhh We Gotta stop :(").formatted(Formatting.AQUA), false);
                            return Command.SINGLE_SUCCESS;
                        }));
    }
}
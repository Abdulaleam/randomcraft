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
                CommandManager.literal("start")
                        .executes(context -> {


                            context.getSource().getServer().getPlayerManager().broadcast( Text.literal("Chaos Crafting Starts! HEHEHE!")
                                            .formatted(Formatting.RED), false);
                            return Command.SINGLE_SUCCESS;
                        }));
    }
}
package rainy.randomcraft;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class RainyCommands {
    public static void register(){
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) ->
                ehStartCommand.register(dispatcher)));
    }
}

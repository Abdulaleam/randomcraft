package rainy.randomcraft;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import rainy.randomcraft.datagen.RainyModelProvider;
import rainy.randomcraft.datagen.RainyRecipeProvider;

public class RandomcraftDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(RainyModelProvider::new);
		pack.addProvider(RainyRecipeProvider::new);

	}
}

package net.sparkshardmc.monstersvsgov.recipe;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.sparkshardmc.monstersvsgov.MonstersVsGov;

public class ModRecipes {
    public static final RecipeSerializer<DaggerRepairRecipe> DAGGER_REPAIR_SERIALIZER = new DaggerRepairRecipe.Serializer();

    public static void register() {
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, 
            new ResourceLocation(MonstersVsGov.MOD_ID, "dagger_repair"), 
            DAGGER_REPAIR_SERIALIZER);
    }
}

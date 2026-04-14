package net.sparkshardmc.monstersvsgov.recipe;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.sparkshardmc.monstersvsgov.MonstersVsGov;
import java.util.function.Supplier;

public class ModRecipes {
    public static Supplier<RecipeSerializer<DaggerRepairRecipe>> DAGGER_REPAIR_SERIALIZER;

    public static void register() {
        DAGGER_REPAIR_SERIALIZER = () -> Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, 
            new ResourceLocation(MonstersVsGov.MOD_ID, "dagger_repair"), 
            new ShapedRecipe.Serializer()); // We use the Shaped Serializer as a base
    }
}

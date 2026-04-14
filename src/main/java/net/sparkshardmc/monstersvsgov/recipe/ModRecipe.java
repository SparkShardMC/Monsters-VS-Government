package net.sparkshardmc.monstersvsgov.recipe;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.sparkshardmc.monstersvsgov.MonstersVsGov;

public class ModRecipes {
    public static void register( ) {
        // This connects your Java logic to the JSON files
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, 
            new ResourceLocation(MonstersVsGov.MOD_ID, "dagger_repair"), 
            new ShapedRecipe.Serializer());
    }
}

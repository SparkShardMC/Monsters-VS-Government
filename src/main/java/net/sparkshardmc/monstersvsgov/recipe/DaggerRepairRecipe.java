package net.sparkshardmc.monstersvsgov.recipe;

import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;

public class DaggerRepairRecipe extends ShapedRecipe {
    public DaggerRepairRecipe(ShapedRecipe compose) {
        super(compose.getId(), compose.getGroup(), compose.category(), 
              compose.getWidth(), compose.getHeight(), 
              compose.getIngredients(), compose.getResultItem(null));
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        // Standard pattern check first
        if (super.matches(container, level)) {
            // Check slot 8 (Bottom-Right) for exactly 3 Gold
            ItemStack goldSlot = container.getItem(8);
            return goldSlot.is(Items.GOLD_INGOT) && goldSlot.getCount() == 3;
        }
        return false;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.DAGGER_REPAIR_SERIALIZER;
    }

    // This handles reading the JSON file into the Java object
    public static class Serializer extends ShapedRecipe.Serializer {
        @Override
        public DaggerRepairRecipe fromJson(net.minecraft.resources.ResourceLocation id, com.google.gson.JsonObject json) {
            return new DaggerRepairRecipe(super.fromJson(id, json));
        }
    }
}

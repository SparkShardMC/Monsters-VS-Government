package net.sparkshardmc.monstersvsgov.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class DaggerRepairRecipe extends ShapedRecipe {
    public DaggerRepairRecipe(ShapedRecipe base) {
        super(base.getGroup(), base.category(), base.getPattern(), base.getResultItem(null));
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        // Standard shape check first
        if (super.matches(input, level)) {
            // In a 3x3 grid, slot 8 is the bottom-right
            ItemStack goldSlot = input.getItem(8);
            
            // Strictly check for Gold Ingot and a count of exactly 3
            return goldSlot.is(Items.GOLD_INGOT) && goldSlot.getCount() == 3;
        }
        return false;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.DAGGER_REPAIR_SERIALIZER;
    }

    // Serializer to handle the JSON registration
    public static class Serializer extends ShapedRecipe.Serializer {
        @Override
        public DaggerRepairRecipe fromJson(net.minecraft.resources.ResourceLocation id, com.google.gson.JsonObject json) {
            return new DaggerRepairRecipe(super.fromJson(id, json));
        }
    }
}

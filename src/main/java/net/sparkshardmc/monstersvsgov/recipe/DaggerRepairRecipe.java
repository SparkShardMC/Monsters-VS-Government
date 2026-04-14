package net.sparkshardmc.monstersvsgov.recipe;

import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import net.sparkshardmc.monstersvsgov.item.ModItems;

public class DaggerRepairRecipe extends ShapedRecipe {
    public DaggerRepairRecipe(ShapedRecipe compose) {
        super(compose.getId(), compose.getGroup(), compose.category(), compose.getWidth(), compose.getHeight(), compose.getIngredients(), compose.getResultItem(null));
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        // First check if the items are in the right spots (The JSON pattern)
        if (super.matches(container, level)) {
            // Check slot 8 (Bottom-Right in 3x3) for exactly 3 Gold
            ItemStack goldSlot = container.getItem(8);
            return goldSlot.is(Items.GOLD_INGOT) && goldSlot.getCount() == 3;
        }
        return false;
    }
}

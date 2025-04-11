package com.uncreativebunch.magicmod.component;

import net.minecraft.component.type.FoodComponent;

/**
 * Contains all the {@link FoodComponent}s that are used within this mod.
 *
 * Food items don't need to have their own specific component - these components can
 * be reused.
 */
public class ModFoodComponents {
    /**
     * Food component currently only used with the TESTING_POWDER item.
     */
    public static FoodComponent TESTING_POWDER_COMPONENT =
        new FoodComponent
            .Builder()
            .alwaysEdible()  // Players can eat this regardless of hunger levels
            .saturationModifier(0.6f)  // Sets how "long" hunger lasts before it starts decreasing again.
            .nutrition(2)  // Sets the number of hunger points given.
            .build();
}

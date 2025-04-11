package com.uncreativebunch.magicmod.component;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;

/**
 * Contains the {@link ConsumableComponent} objects that are a part of this mod.
 * These are what gives the player side effects as a result of eating or drinking.
 */
public class ModConsumableComponents {
    /** If a consumable item has this component, it is guaranteed to give the player night vision for 60 seconds. */
    public static ConsumableComponent TESTING_POWDER_NIGHTVIS =
        ConsumableComponents
            .food()  // This is for a food item, not a drink.
            .consumeEffect(new ApplyEffectsConsumeEffect(
                new StatusEffectInstance(
                    StatusEffects.NIGHT_VISION,  // the effect to apply.
                    60 * 20,  // There are 20 ticks per second - we want 60 seconds of night vis.
                    1  // The intensity - could be strength 2, haste 3, etc.
                ),
                1  // The probability that the consumer gets the effect.
            ))
            .build();
}

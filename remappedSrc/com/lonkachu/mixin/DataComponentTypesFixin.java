package com.lonkachu.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.lonkachu.StackableMod;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.points.BeforeConstant;

import java.lang.annotation.Target;

import static net.minecraft.component.DataComponentTypes.*;


@Mixin(DataComponentTypes.class)
public class DataComponentTypesFixin {

    @ModifyExpressionValue
            (
                    method = "<clinit>",
                    at = @At(value = "CONSTANT", args = "intValue=64")
            )
    private static int getMaxCountPerStack(int original) {
        return StackableMod.getMaxStackCount();
    }
//private static final ComponentMap DEFAULT_ITEM_COMPONENTS = ComponentMap.builder().add(MAX_STACK_SIZE, StackableMod.getMaxStackCount()).add(LORE, LoreComponent.DEFAULT).add(ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT).add(REPAIR_COST, 0).add(ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.DEFAULT).add(RARITY, Rarity.COMMON).build();
}

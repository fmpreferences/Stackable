package com.lonkachu.mixin.client;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(DrawContext.class)
public class DrawContextFixin
{
    /**
     * @author Lonkachu
     * @reason Ulitmately, either I overwrite this method, or I inject it and return immediately afterwords. either way, mods will be incompatible
     */

    @ModifyVariable(method = "Lnet/minecraft/client/gui/DrawContext;drawItemInSlot(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V", at = @At("HEAD"), ordinal = 0)
    private String modifyString(String value, TextRenderer textRenderer, ItemStack stack, int x, int y, @Nullable String countOverride)
    {
        int count = stack.getCount();
        if(count > 999999999) {
            count /= 1000000000;
            return count + "B";
        }
        else if (count > 999999)
        {
            count /= 1000000;
            return count + "M";
        }
        else if (count > 999)
        {
            count /= 1000;
            return count + "K";
        }
        else if (count > 1)
        {
            return String.valueOf(count);
        }


        return "";
    }
}

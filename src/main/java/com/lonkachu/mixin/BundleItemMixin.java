package com.lonkachu.mixin;

import com.lonkachu.StackableMod;
import net.minecraft.item.BundleItem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//@BundleItemMixinFixin
/*
This class handles a major crash issue that results from bundles assuming the stack size is 64 rather than 127 or whatever custom value has been applied, this class makes some siginficant changes to how bundles work and can be a considerable problem if your mod also utilizes the bundleitem class.


 */

@Mixin(BundleItem.class)
public class BundleItemMixin {

    @Mutable
    @Shadow @Final public static int field_51352; //MAX_STORAGE

    @Inject(method = "<clinit>", at = @At("HEAD"))
    private static void inject(CallbackInfo ci) {
        field_51352 = StackableMod.getMaxStackCount();
    }

    //@appendTooltip
    //We need to remove the 0/64 tooltip from the bundle and replace it with a tooltip that includes the actual stacksize we defined already
    @ModifyConstant(
            method = "appendTooltip",
            constant = @Constant(intValue = 64)
    )
    private int appendTooltip(int constant)
    {
        return StackableMod.getMaxStackCount();
    }


}

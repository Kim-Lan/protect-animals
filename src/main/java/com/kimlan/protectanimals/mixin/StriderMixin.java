package com.kimlan.protectanimals.mixin;

import net.minecraft.world.entity.monster.Strider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;

@Mixin(Strider.class)
abstract class StriderMixin {
    @ModifyReturnValue(
        method = "isSensitiveToWater",
        at = @At("RETURN")
    )
    private boolean modifyIsSensitiveToWater(boolean original) {
        return false;
    }
}
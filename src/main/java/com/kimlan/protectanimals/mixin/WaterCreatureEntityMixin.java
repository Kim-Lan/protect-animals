package com.kimlan.protectanimals.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WaterCreatureEntity.class)
abstract class WaterCreatureEntityMixin extends Entity {
    public WaterCreatureEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        if (damageSource.isOf(DamageTypes.DROWN) ||  (this.hasCustomName() && !(this instanceof Monster))) {
            return true;
        }
        return super.isInvulnerableTo(damageSource);
    }
}

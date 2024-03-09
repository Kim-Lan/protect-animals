package com.kimlan.saveanimals.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AllayEntity.class)
abstract class AllayEntityMixin extends Entity {
    public AllayEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        return this.hasCustomName() && !(this instanceof Monster);
    }
}

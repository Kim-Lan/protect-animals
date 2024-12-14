package com.kimlan.protectanimals.mixin;

import net.minecraft.util.Formatting;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Locale;

@Mixin(AnimalEntity.class)
abstract class AnimalEntityMixin extends LivingEntity {
    protected AnimalEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean isInvulnerableTo(ServerWorld world, DamageSource damageSource) {
        if (!this.hasCustomName()) {
            return super.isInvulnerableTo(world, damageSource);
        }

        String name = Formatting.strip(this.getName().getString()).toLowerCase(Locale.ROOT);
        return !(name.equals("killable") || this instanceof Monster);
    }
}

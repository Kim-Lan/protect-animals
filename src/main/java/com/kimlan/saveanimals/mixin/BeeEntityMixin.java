package com.kimlan.saveanimals.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BeeEntity.class)
abstract class BeeEntityMixin extends AnimalEntity {
    protected BeeEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        if (damageSource.isOf(DamageTypes.MOB_ATTACK)
                || damageSource.isOf(DamageTypes.PLAYER_ATTACK)
                || damageSource.isOf(DamageTypes.MOB_PROJECTILE)
                || damageSource.isOf(DamageTypes.WITHER)
                || damageSource.isOf(DamageTypes.WITHER_SKULL)) {
            return super.isInvulnerableTo(damageSource);
        }
        return true;
    }
}

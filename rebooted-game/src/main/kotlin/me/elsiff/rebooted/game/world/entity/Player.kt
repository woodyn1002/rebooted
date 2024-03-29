package me.elsiff.rebooted.game.world.entity

import me.elsiff.rebooted.engine.math.Vector2f
import me.elsiff.rebooted.engine.math.size
import me.elsiff.rebooted.game.graphic.Graphic
import me.elsiff.rebooted.game.graphic.PlayerGraphic
import me.elsiff.rebooted.game.world.AltitudeRanges
import me.elsiff.rebooted.game.world.Altitudes
import me.elsiff.rebooted.game.world.World
import me.elsiff.rebooted.game.world.entity.rule.EntityRule
import me.elsiff.rebooted.game.world.entity.rule.MoveViaControllerRule
import me.elsiff.rebooted.game.world.entity.rule.ShootShotgunRule
import me.elsiff.rebooted.game.world.physic.AABB
import me.elsiff.rebooted.game.world.physic.RigidBody

/**
 * Created by elsiff on 2019-07-29.
 */
class Player(
    override val world: World,
    position: Vector2f
) : Entity {
    private var _isDisposed = false
    override val isDisposed: Boolean get() = _isDisposed

    override val rigidBody: RigidBody = RigidBody(
        position = position,
        mass = 0f,
        restitution = 0f,
        drag = 0.35f
    ).apply {
        boundings += AABB(this, size(10f, 10f))
    }
    override val altitude: Int = Altitudes.GROUND
    override val blockingAltitudes: IntRange = AltitudeRanges.GROUND_ONLY

    override val graphic: Graphic = PlayerGraphic()
    override val rules: MutableList<EntityRule> = mutableListOf(
        MoveViaControllerRule(this@Player, 100f),
        ShootShotgunRule(this@Player)
    )

    override fun handleCollided(other: Entity) {
        // Do Nothing
    }

    override fun dispose() {
        check(!_isDisposed)

        rules.forEach { it.dispose() }

        _isDisposed = true
    }
}
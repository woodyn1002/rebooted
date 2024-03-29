package me.elsiff.rebooted.game.state

import me.elsiff.rebooted.engine.math.size
import me.elsiff.rebooted.engine.math.vec2f
import me.elsiff.rebooted.engine.state.GameState
import me.elsiff.rebooted.game.Game
import me.elsiff.rebooted.game.graphic.add
import me.elsiff.rebooted.game.ui.Layout
import me.elsiff.rebooted.game.world.Camera
import me.elsiff.rebooted.game.world.World
import me.elsiff.rebooted.game.world.entity.Enemy
import me.elsiff.rebooted.game.world.entity.Player

/**
 * Created by elsiff on 2019-07-25.
 */
class WorldState : GameState {
    private var _isDisposed = false
    override val isDisposed: Boolean get() = _isDisposed

    private val world: World = World(size(500f, 500f))
    private val layout: Layout = Layout()
    private val camera: Camera = Camera(world, Game.engine.mainWindow.size)
    private val player: Player = Player(world, vec2f(50f, 50f))

    override fun enter() {
        world += player

        world += Enemy(world, vec2f(250f, 250f))
    }

    override fun update(deltaTime: Float) {
        if (Game.isDebugMode) {
            Game.engine.mainWindow.title = "rebooted [${world.entities.size} entities, FPS: ${1f / deltaTime}]"
        }

        world.update(deltaTime)
    }

    override fun render() {
        Game.engine.mainWindow.graphics.apply {
            clear()

            for (entity in world.entities) {
                add(entity.graphic, entity.positionIn(camera))

                if (Game.isDebugMode) {
                    for (bounding in entity.rigidBody.boundings) {
                        add(bounding.debugGraphic, entity.positionIn(camera))
                    }
                }
            }
            for (widget in layout.widgets) {
                add(widget.graphic, widget.position)
            }

            render()
        }
    }

    override fun dispose() {
        check(!_isDisposed)

        world.dispose()
        layout.dispose()

        _isDisposed = true
    }
}
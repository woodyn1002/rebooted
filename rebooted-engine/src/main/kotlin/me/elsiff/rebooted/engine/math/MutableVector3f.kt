package me.elsiff.rebooted.engine.math

/**
 * Created by elsiff on 2019-07-24.
 */
class MutableVector3f(
    x: Float, y: Float, z: Float
) : Vector3f(x, y, z) {
    override var elements: MutableList<Float> = mutableListOf(x, y, z)

    override var x: Float
        get() = elements[0]
        set(value) {
            elements[0] = value
        }
    override var y: Float
        get() = elements[1]
        set(value) {
            elements[1] = value
        }
    override var z: Float
        get() = elements[2]
        set(value) {
            elements[2] = value
        }

    operator fun set(i: Int, value: Float) {
        require(i in 0..2) { throw IndexOutOfBoundsException() }
        elements[i] = value
    }

    override fun normalized() = super.normalized().toMutable()
    override fun unaryPlus() = super.unaryPlus().toMutable()
    override fun unaryMinus() = super.unaryMinus().toMutable()
    override fun plus(increment: Float) = super.plus(increment).toMutable()
    override fun minus(decrement: Float) = super.minus(decrement).toMutable()
    override fun times(multiplier: Float) = super.times(multiplier).toMutable()
    override fun div(divider: Float) = super.div(divider).toMutable()
}

operator fun Float.plus(vector: MutableVector3f): MutableVector3f = vector.plus(this)
operator fun Float.minus(vector: MutableVector3f): MutableVector3f = vector.minus(this)
operator fun Float.times(vector: MutableVector3f): MutableVector3f = vector.times(this)
operator fun Float.div(vector: MutableVector3f): MutableVector3f = vector.div(this)

fun mutableVec3f(x: Float, y: Float, z: Float) = MutableVector3f(x, y, z)
fun mutableVec3f(x: Number, y: Number, z: Number) = MutableVector3f(x.toFloat(), y.toFloat(), z.toFloat())
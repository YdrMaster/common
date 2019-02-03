package org.mechdancer.common.extension

import kotlin.math.max
import kotlin.math.min

/** 闭区间长度 */
val IntRange.length: Int get() = endInclusive - start

/** 闭区间长度 */
val LongRange.length: Long get() = endInclusive - start

/** 闭区间长度 */
val ClosedFloatingPointRange<Double>.length: Double get() = endInclusive - start

/** 限幅器 */
fun IntRange.limit(x: Int): Int =
    when {
        x < start        -> start
        x > endInclusive -> endInclusive
        else             -> x
    }

/** 限幅器 */
fun LongRange.limit(x: Long): Long =
    when {
        x < start        -> start
        x > endInclusive -> endInclusive
        else             -> x
    }

/** 限幅器 */
fun ClosedFloatingPointRange<Double>.limit(x: Double): Double =
    when {
        x < start        -> start
        x > endInclusive -> endInclusive
        else             -> x
    }

/** 调整周期函数到区间内 */
tailrec fun ClosedFloatingPointRange<Double>.adjust(x: Double): Double =
    when {
        x < start        -> adjust(x + length)
        x > endInclusive -> adjust(x - length)
        else             -> x
    }

/** 根据中心[this]和半径[radius]张开区间 */
infix fun Int.extend(radius: Int): IntRange =
    this - radius..this + radius

/** 根据中心[this]和半径[radius]张开区间 */
infix fun Long.extend(radius: Long): LongRange =
    this - radius..this + radius

/** 根据中心[this]和半径[radius]张开区间 */
infix fun Double.extend(radius: Double): ClosedFloatingPointRange<Double> =
    this - radius..this + radius

/** 并集 */
operator fun ClosedFloatingPointRange<Double>.plus(other: ClosedFloatingPointRange<Double>) =
    min(start, other.start)..max(endInclusive, other.endInclusive)

/** 交集 */
operator fun ClosedFloatingPointRange<Double>.times(other: ClosedFloatingPointRange<Double>) =
    max(start, other.start)..min(endInclusive, other.endInclusive)

/**
 * 等分区间
 * @return 等分点（包括两端点）
 */
operator fun ClosedFloatingPointRange<Double>.div(t: Int) =
    sequence { for (i in 0..t) yield(((t - i) * start + i * endInclusive) / t) }

/** 转换为浮点区间 */
fun IntRange.toDouble() = start.toDouble()..endInclusive.toDouble()

/** 转换为浮点区间 */
fun LongRange.toDouble() = start.toDouble()..endInclusive.toDouble()

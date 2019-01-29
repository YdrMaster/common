package org.mechdancer.common.extension

import org.mechdancer.common.extension.Optional.Otherwise

/** bool = true  则执行代码块 */
fun Boolean.then(block: () -> Unit) = also { if (it) block() }

/** bool = false 则执行代码块 */
fun Boolean.otherwise(block: () -> Unit) = also { if (!it) block() }

/** 检查一个值 */
inline fun <T> T.check(block: (T) -> Boolean) =
    if (block(this)) Optional<T>(this) else Optional(Otherwise)

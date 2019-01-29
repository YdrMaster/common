package org.mechdancer.common.collection.bs


fun <T : Comparable<T>> BSNode<T>.search(value: T): BSNode<T>? =
    if (value < this.value) left.search(value)
    else right.search(value)



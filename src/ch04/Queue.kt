package ch04

import java.lang.IndexOutOfBoundsException

class Queue<E> {
    private val minCapacityIncrement = 12
    private var elements: Array<Any?>
    private var size = 0

    constructor() {
        this.elements = arrayOf()
    }

    constructor(initialCapacity: Int) {
        this.elements = arrayOfNulls(initialCapacity)
    }

    fun enQueue(element: E) {
        if (isFull()) {
            val newArray = arrayOfNulls<Any>(
                size +
                        if (size < minCapacityIncrement / 2) {
                            minCapacityIncrement
                        } else {
                            size shr 1
                        }
            )
            System.arraycopy(elements, 0, newArray, 0, size)
            elements = newArray
        }
        elements[size++] = element
    }

    fun deQueue(): E {
        if (size == 0) throw QueueUnderflowException()
        val oldVal = elements[0]
        elements[0] = null
        System.arraycopy(elements, 1, elements, 0, --size)
        return oldVal as E
    }

    fun front() = try {
        elements[0] as E
    } catch (e: IndexOutOfBoundsException) {
        throw QueueUnderflowException()
    }

    fun isEmpty() = size == 0
    fun isFull() = size == elements.size
}
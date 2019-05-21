package ch02

import java.lang.IllegalArgumentException
import java.util.*

fun main(args: Array<String>) {
    val days = listOf("Sunday", "Monday", "Tuesday", "Wednesday")
    val months = arrayListOf("January", "February", "March", "April")

    val modifiedDays = days + "Thursday"
    months.add("May")
}

class ImmutableList<E> {
    private val minCapacityIncrement = 12
    var elements: Array<Any?>
    private var size = 0

    constructor() {
        this.elements = arrayOf()
    }

    constructor(initialCapacity: Int) {
        if (initialCapacity > 0) {
            this.elements = Array(initialCapacity) { null }
        } else if (initialCapacity == 0) {
            this.elements = emptyArray();
        } else {
            throw IllegalArgumentException("Illegal Capacity: $initialCapacity")
        }
    }

    constructor(vararg item: E) {
        this.elements = item as Array<Any?>
        size = item.size
    }

    fun add(element: E): ImmutableList<E> {
        val s = size
        val newList = ImmutableList<E>(s + 1)
        System.arraycopy(elements, size, newList, 0, s)
        newList.elements[s] = element
        newList.size = s + 1
        return newList
    }

    fun get(index: Int): E {
        if (index >= size) {
            throwIndexOutOfBoundsException(index, size)
        }
        return elements[index] as E
    }

    fun set(index: Int, element: E): ImmutableList<E> {
        if (index >= size) {
            throwIndexOutOfBoundsException(index, size)
        }
        val s = size
        val newList = ImmutableList<E>(s)
        System.arraycopy(elements, 0, element, 0, s)
        newList.elements[index] = element
        newList.size = s
        return newList
    }

    fun isEmpty() = size == 0
    fun size() = size
    operator fun contains(element: E): Boolean {
        return indexOf(element) >= 0
    }

    fun indexOf(element: E): Int {
        if (element == null) {
            for (i in 0 until size) {
                if (elements[i] == null)
                    return i
            }
        } else {
            for (i in 0 until size) {
                if (elements[i] == element)
                    return i
            }
        }
        return -1
    }

    fun lastIndexOf(element: E): Int {
        if (element == null) {
            for (i in size - 1 downTo 0) {
                if (elements[i] == null)
                    return i
            }
        } else {
            for (i in size - 1 downTo 0) {
                if (elements[i] == element)
                    return i
            }
        }
        return -1
    }

    fun toArray(): Array<out Any?> {
        return Arrays.copyOf(elements, size)
    }

    private fun newCapacity(currentCapacity: Int): Int {
        val increment = if (currentCapacity < minCapacityIncrement / 2) {
            minCapacityIncrement
        } else {
            currentCapacity shr 1
        }
        return currentCapacity + increment
    }

    private fun throwIndexOutOfBoundsException(index: Int, size: Int): IndexOutOfBoundsException {
        throw IndexOutOfBoundsException("Invalid index $index, size is $size")
    }

    override fun toString(): String = Arrays.toString(elements)

}
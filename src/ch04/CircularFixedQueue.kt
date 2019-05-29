package ch04

import kotlin.IndexOutOfBoundsException

class CircularFixedQueue<E> {
    private val capacity: Int
    private var front = -1
    private var rear = -1
    private val elements: Array<Any?>

    constructor(capacity: Int) {
        this.capacity = capacity
        this.elements = arrayOfNulls(capacity)
    }

    fun enquque(element: E) {
        if (isFull()) throw QueueOverflowException()
        rear = (rear + 1) % capacity
        elements[rear] = element
        if (front == -1) front = rear
    }

    fun dequeue(): E {
        if (isEmpty()) throw QueueUnderflowException()
        val oldVal = elements[front]
        elements[front] = null
        if (front == rear) {
            front = -1
            rear = -1
        } else front = (front + 1) % capacity
        return oldVal as E
    }

    fun front() = try {
        elements[front] as E
    } catch (e: IndexOutOfBoundsException) {
        throw QueueUnderflowException();
    }

    fun rear() = try {
        elements[rear] as E
    } catch (e: IndexOutOfBoundsException) {
        throw QueueUnderflowException();
    }


    private fun isEmpty(): Boolean {
        return front == -1
    }

    private fun isFull(): Boolean {
        return (rear + 1) % capacity == front
    }
}
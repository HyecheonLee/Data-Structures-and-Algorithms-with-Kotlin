package ch03

class CircularLinkyList<E> {
    private var size = 0
    private var head: Node<E>? = null
    private var tail: Node<E>? = null

    private inner class Node<E> constructor(
        internal var prev: Node<E>?,
        internal var element: E,
        internal var next: Node<E>?
    )

    fun getFirst() = head?.element
    fun getLast() = tail?.element
    fun removeFirst() = if (head == null) null else unlink(head!!)
    fun removeLast() = if (tail == null) null else unlink(tail!!)
    fun addFirst(element: E) {
        linkHead(element)
    }

    fun addLast(element: E) {
        linkTail(element)
    }

    fun add(element: E) {
        linkTail(element)
    }

    fun remove(element: E): Boolean {
        if (head == null) return false
        var curr = head
        do {
            if (curr!!.element == element) {
                unlink(curr)
                return true
            }
            curr = curr.next
        } while (curr != head)
        return false
    }

    fun size() = size

    operator fun contains(element: E) = indexOf(element) != -1

    fun get(index: Int): E {
        validateElementIndex(index)
        return node(index).element
    }

    fun set(index: Int, element: E): E {
        validateElementIndex(index)
        val x = node(index)
        val oldVal = x.element
        x.element = element
        return oldVal
    }

    fun add(index: Int, element: E) {
        validatePositionIndex(index)
        if (index == size) {
            linkTail(element)
        } else {
            linkBefore(element, node(index))
        }
    }

    fun remove(index: Int): E {
        validateElementIndex(index)
        return unlink(node(index))
    }

    private fun indexOf(element: E): Int {
        if (head == null) return -1
        var index = 0
        var x = head
        do {
            if (element == x!!.element)
                return index
            index++
            x = x.next
        } while (x != tail)
        return -1
    }

    private fun linkHead(element: E) {
        val h = head
        val newNode = Node<E>(tail, element, h)
        head = newNode
        if (h == null) {
            tail = newNode
            newNode!!.prev = tail
            newNode.next = head
        } else {
            h.prev = newNode
            tail!!.next = newNode
        }
        size++
    }

    private fun linkTail(element: E) {
        val t = tail
        val newNode = Node<E>(t, element, head)
        tail = newNode
        if (t == null) {
            head = newNode
        } else {
            t.next = newNode
            head!!.prev = newNode
        }
        size++
    }

    private fun linkBefore(element: E, succ: Node<E>) {
        val pred = succ.prev
        val newNode = Node(pred, element, succ)
        succ.prev = newNode
        if (pred == tail) {
            head = newNode
        }
        pred?.next = newNode
        size++
    }

    private fun unlink(curr: Node<E>): E {
        val element = curr.element
        val next = curr.next
        val prev = curr.prev
        if (curr == head) {
            head = next
        }
        if (curr == tail) {
            tail = prev
        }
        prev?.next = next
        next?.prev = prev
        curr.prev = null
        curr.next = null
        size--
        if (size == 0) {
            head = null
            tail = null
        }
        return element
    }

    private fun node(index: Int): Node<E> {
        if (index < size shr 1) {
            var x = head
            for (i in 0 until index)
                x = x!!.next
            return x!!
        } else {
            var x = tail
            for (i in size - 1 downTo index + 1)
                x = x!!.prev
            return x!!
        }
    }

    private fun validateElementIndex(index: Int) {
        if (index < 0 || index >= size)
            throw IndexOutOfBoundsException(outOfBoundsMsg(index))
    }

    private fun validatePositionIndex(index: Int) {
        if (index < 0 && index > size)
            throw IndexOutOfBoundsException(outOfBoundsMsg(index))
    }

    private fun outOfBoundsMsg(index: Int): String {
        return "Index: $index, Size: $size"
    }

}
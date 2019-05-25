package ch03

class DoublyLinkyList<E> {
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
    fun removeFirst() = unlinkHead()
    fun removeLast() = unlinkTail()
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
        var curr = head
        while (curr != null) {
            if (curr.element == element) {
                unlink(curr)
                return true
            }
            curr = curr.next
        }
        return false
    }

    fun clear() {
        var x = head
        while (x != null) {
            val next = x.next
            x.next = null
            x.prev = null
            x = next
        }
        tail = null
        head = tail
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
        validateElementIndex(index)
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


    private fun linkBefore(element: E, succ: Node<E>) {
        val pred = succ.prev
        val newNode = Node(pred, element, succ)
        succ.prev = newNode
        if (pred == null) {
            head = newNode
        } else {
            pred.next = newNode
        }
        size++
    }

    private fun node(index: Int): Node<E> {
        return if (index < size shr 1) {
            var x = head
            for (i in 0 until index) {
                x = x!!.next
            }
            x!!
        } else {
            var x = tail
            for (i in size - 1 downTo index + 1) {
                x = x!!.prev
            }
            x!!
        }
    }

    private fun unlink(curr: Node<E>): E {
        val element = curr.element
        val prev = curr.prev
        val next = curr.next
        if (prev == null) {
            head = next
        } else {
            prev.next = next
            curr.prev = null
        }
        if (next == null) {
            tail = prev
        } else {
            next.prev = prev
            curr.next = null
        }
        size--
        return element
    }

    private fun linkHead(element: E) {
        val h = head
        val newNode = Node(null, element, h)
        head = newNode
        if (h == null) {
            tail = newNode
        } else {
            h.prev = newNode
        }
        size++
    }

    private fun linkTail(element: E) {
        val t = tail
        val newNode = Node(t, element, null)
        tail = newNode
        if (t == null) {
            head = newNode
        } else {
            t.next = newNode
        }
        size++
    }

    private fun unlinkHead() {
        head?.let {
            val next = it.next
            it.next = null
            head = next
            if (next == null) {
                tail = null
            } else {
                next.prev = null
            }
            size--
        }
    }

    private fun unlinkTail() {
        tail?.let {
            val prev = it.prev
            it.prev = null
            tail = prev
            if (prev == null) {
                tail = null
            } else {
                prev.next = null
            }
            size--
        }
    }

    private fun indexOf(element: E): Int {
        var index = 0
        var x = head
        while (x != null) {
            if (element == x.element) {
                return index
            }
            index++
            x = x.next
        }
        return -1
    }


    private fun validateElementIndex(index: Int) {
        if (index < 0 || index >= size)
            throw IndexOutOfBoundsException(outOfBoundMsg(index))
    }

    private fun validatePositionIndex(index: Int) {
        if (index in (size + 1)..-1)
            throw java.lang.IndexOutOfBoundsException(outOfBoundMsg(index))
    }

    private fun outOfBoundMsg(index: Int): String {
        return "Index: $index, Size: $size"
    }
}
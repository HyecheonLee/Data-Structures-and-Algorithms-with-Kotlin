package ch02

fun main(args: Array<String>) {
    val numbers = arrayOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6), arrayOf(7, 8, 9))
    val food = arrayOf(
        arrayOf("Apple", "Apricot", "Avocado"),
        arrayOf("Banana", "Broccoli", "Beetroot"),
        arrayOf("Cherry", "Carrot")
    )

    val row1 = food[0]
    val row2 = food[1]
    val firstFoodWithA = row1[0]
    val firstFoodWithB = row2[0]

    println("2nd food item which starts from B is : ${food[1][1]}")
    println("2nd food item which starts from C is : ${food[2][1]}")

    food[0] = arrayOf("Date", "Damson", "Durian")
    food[2][1] = "Coconut"

    //Iterating over the array
    for (row in food) {
        print("Item: ")
        for (item in row) {
            print("$item ")
        }
        println()
    }
}

fun isValidMatrix(arr: Array<Array<*>>): Boolean {
    var isValid = true
    var sizeOfRow = arr[0].size
    for (row in arr) {
        if (sizeOfRow != row.size) {
            isValid = false
            break
        }
    }
    return isValid
}

fun add(a: Array<DoubleArray>, b: Array<DoubleArray>): Array<DoubleArray> {
    val m = a.size
    val n = a[0].size
    val c = Array(m) { DoubleArray(n) }
    for (i in 0 until m) {
        for (j in 0 until n) {
            c[i][j] = a[i][j] + b[i][j]
        }
    }
    return c
}

fun multiply(a: Array<DoubleArray>, b: Array<DoubleArray>): Array<DoubleArray> {
    val m1 = a.size
    val n1 = a[0].size

    val m2 = b.size
    val n2 = b[0].size
    if (n1 != m2) throw RuntimeException("Illegal matrix dimensions.")
    val c = Array(m1) { DoubleArray(n2) }
    for (i in 0 until m1)
        for (j in 0 until n2)
            for (k in 0 until n1)
                c[i][j] += a[i][k] * b[k][j]
    return c
}
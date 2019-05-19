package ch02

import java.util.*

data class User(
    val firstName: String,
    val lastName: String,
    val phone: String,
    val email: String
)


fun main(args: Array<String>) {
    val friends = arrayOf("Rivu", "Subin", "Sid", "Susri", "Ramya", "Sachin")
    val info = arrayOf("Rivu", 32, 5.11, "rivu@abc.com")
    //타입을 강제 에러 발생
//    val info: Array<String> = arrayOf("Rivu", 32, 5.11, "rivu@abc.com")
    //wrapper type array
    val numbers = arrayOf(1, 3, 5, 7, 9)
    // primitive type array
    val oddNums = intArrayOf(1, 3, 5, 7, 9)

    val users = arrayOf(
        User("Chandra Sekhar", "Nayak", "0909090909", "chansek@live.com"),
        User("Utkarsh", "Asthana", "1234123412", "utku@xyz.com"),
        User("Sachin", "Kamble", "7878787878", "sachin@abc.com"),
        User("Ramya", "K", "0000000000", "ramu@zzz.com"),
        User("Subin", "S", "1234512345", "sub@s.com")
    )

    //자료를 모를때 null 체워 당연함?
    val serverUsers: Array<User?> = arrayOf(null, null, null, null, null)
    val biggerNullArray: Array<User?> = arrayOfNulls(100)

//    Creating an array using the constructor
    val squares = Array(51) { i -> i * i }

//    Accessing elements from an array
    val languages = arrayOf("Kotlin", "Java", "C", "C++", "C#", "Javascript", "Python")
    val firstLanguage = languages[0]
    val fifthLanguage = languages[4]

//    val invalidLanguage = languages[-1]
//    val outLanguage = languages[100]

    val secondLanaguage = languages.get(1)
    val sixthLanaguage = languages.get(5)

    val firstLang = languages.component1()
    val secLang = languages.component2()

    val firstItem = languages.elementAt(0)
    val secItem = languages.elementAt(1)
    val tenthItem = languages.elementAtOrElse(9, { _ -> "Not Available" })
    val eleventhItem = languages.elementAtOrNull(10)

    for (i in languages.indices) {
        if (i % 2 == 0) {
            println(languages[i])
        } else {
            println(languages[i].toUpperCase())
        }
    }
    for (language in languages) {
        println("Language - $language")
    }
    languages.forEach { println("Language in Upper Case - ${it.toUpperCase()}") }
    languages[1] = "Swift"
    languages[4] = "Objective-C"
    println("Newly updated languages are - ${Arrays.toString(languages)}")
    // output - Newly updated languages are - [Kotlin, Swift, C, C++, Objective-C, JavaScript, Python]
    languages.set(5, "Typescript")
    languages.set(6, "Dart")
    println("Newly updated languages are - ${Arrays.toString(languages)}")
    // Output - Newly updated languages are - [Kotlin, Swift, C, C++, Objective-C, TypeScript, Dart]

}
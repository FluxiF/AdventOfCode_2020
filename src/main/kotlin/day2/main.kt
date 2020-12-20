package day2

import java.io.File

fun main() {
    val passwords = mutableListOf<List<String>>()

    File("/home/felixfm/adventOfCode_2020/src/main/kotlin/day2/passwords.txt").useLines { lines ->
        lines.forEach { passwords.add(it.split(" ", ":"))}
    }

    val parsedPasswords = passwords.map { ls -> convertToTriple(ls) }


    println(question1(parsedPasswords))
    println(question2(parsedPasswords))
}

fun question1(ls: List<Triple<IntRange, Char, String>>): Int {
    var count = 0

    for (t in ls) {
        if (t.third.count { c -> c == t.second } in t.first) count++
    }

    return count
}

fun question2(ls: List<Triple<IntRange, Char, String>>): Int {
    var count = 0

    for (t in ls) {
        if (t.third[t.first.first - 1] == t.second && t.third[t.first.last - 1] != t.second) count++
        if (t.third[t.first.first - 1] != t.second && t.third[t.first.last - 1] == t.second) count++
    }

    return count
}

fun convertToTriple(ls: List<String>): Triple<IntRange, Char, String> {
    val rangeString = ls[0].split("-")
    val rangeInt = rangeString.map { s -> s.toInt() }
    val range = rangeInt[0]..rangeInt[1]

    return Triple(range, ls[1].single(), ls[3])
}
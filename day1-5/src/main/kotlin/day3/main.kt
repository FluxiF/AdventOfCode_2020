package day3

import java.io.File
import java.math.BigInteger

fun main() {
    val lineList = mutableListOf<String>()

    File("adventOfCode_2020/src/main/kotlin/day3/trees.txt").useLines { lines ->
        lines.forEach { lineList.add(it.repeat(3000)) }
    }

    //question 1
    println(findSlope3Down1Up(lineList))

    //question 2
    //val queries = mutableListOf(1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2)
    //val queries = mutableListOf(1 to 1, 3 to 1, 5 to 1, 1 to 2)
    //val sumOfQueries = queries.fold(1, {acc, p -> acc * nmbrTreesOfSlope(lineList, p.first, p.second)})
    //println(sumOfQueries)

    val q1 = nmbrTreesOfSlope(lineList, 1, 1)
    val q2 = nmbrTreesOfSlope(lineList, 3, 1)
    val q3 = nmbrTreesOfSlope(lineList, 5, 1)
    val q4 = nmbrTreesOfSlope(lineList, 7, 1)
    val q5 = nmbrTreesOfSlope(lineList, 1, 2)

    println((q1 + BigInteger.ONE) * (q2 + BigInteger.ONE) * q3 * q4 * q5) //ugly hax solution by adding the ONES
    println(q2)
}

fun findSlope3Down1Up(ls: MutableList<String>): Int {
    var count = 0
    var row = 0

    for (s in ls) {
        if (row != 0 && s[row*3] == '#') count++
        row++
    }

    return count
}

fun nmbrTreesOfSlope(treeMatrix: MutableList<String>, right: Int, down: Int): BigInteger {
    var index = 0
    var count = 0
    var row = 0 //index from 0

    while (row != treeMatrix.size - 1) {
        if (row != 0 && treeMatrix[row][index] == '#') count++
        index += right
        row += down
    }

    return count.toBigInteger()
}
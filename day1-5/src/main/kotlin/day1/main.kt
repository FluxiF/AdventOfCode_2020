package day1
import java.io.File
import java.io.BufferedReader


fun main(args: Array<String>) {

    // segment taken from stackoverflow https://stackoverflow.com/questions/55182578/how-to-read-plain-text-file-in-kotlin
    // author: Anisuzzaman Babla
    val lineList = mutableListOf<Int>()

    File("adventOfCode_2020/src/main/kotlin/day1/day1.1.txt").useLines { lines ->
        lines.forEach { lineList.add(it.toInt()) }
    }
    // stackoverflow end

    val sumPair = findSumPair(lineList, 2020)
    println(sumPair)
    println(sumPair.first * sumPair.second)

    val sumTriple = findSumTriple(lineList, 2020)
    println(sumTriple)
    println(sumTriple.first * sumTriple.second * sumTriple.third)
}

// return first two numbers whos sum is targetSum
fun findSumPair (ls: List<Int>, targetSum: Int): Pair<Int, Int> {
    ls.map { s1 -> ls.map { s2 -> if (s1 + s2 == targetSum) return (s1 to s2) } }

    return (0 to 0)
}

fun findSumTriple (ls: List<Int>, targetSum: Int): Triple<Int, Int, Int> {
    ls.map { s1 -> ls.map { s2 -> ls.map { s3 ->  if (s1 + s2 + s3 == targetSum) return Triple(s1,s2,s3) } } }

    return Triple(0,0,0)
}





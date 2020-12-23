package day4

import java.io.File
import java.lang.Character.isLetter


fun main() {
    val lineList = mutableListOf<Int>()

    val input = File("adventOfCode_2020/src/main/kotlin/day4/passports.txt").readText().split("\n\n")

    // produce a list of passports with data fields sorted as pairs
    val passList= input.map {
        it.split(" ", "\n", ":").windowed(2, 2, transform = {ls -> Pair(ls[0], ls[1])} )
    }
    println(input.size)
    //question2
    println(countValidPassports(passList))
}

fun countValidPassports(passes: List<List<Pair<String, String>>>): Int {
    var nmbrInvalid = 0
    val validSet = setOf<String>("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

    passes.forEach { list ->
        if(list.unzip().first.containsAll(validSet)) {
           conditions@ for (pair in list) {
                var key = pair.first
                var value = pair.second

                // Check conditions for invalid passport, if any are true end the loop without incrementing nmbrValid
                when(key) {
                    "byr" -> if (value.toInt() !in 1920..2002) {nmbrInvalid++; break@conditions}
                    "iyr" -> if (value.toInt() !in 2010..2020) {nmbrInvalid++; break@conditions}
                    "eyr" -> if (value.toInt() !in 2020..2030) {nmbrInvalid++; break@conditions}
                    "hgt" -> when(value.takeLast(2)) {
                        "cm" -> if (value.take(value.length - 2).toInt() !in 150..190) {nmbrInvalid++; break@conditions}
                        "in" -> if (value.take(value.length - 2).toInt() !in 59..76) {nmbrInvalid++; break@conditions}
                    }
                    "hcl" ->  {
                        var code = value.takeLast(value.length - 1)
                        if (value.first() != '#') {nmbrInvalid++; break@conditions}
                        if (code.length != 6) {nmbrInvalid++; break@conditions}
                        if (code.all(Character::isLetter)) {
                            if (code.all { it in 'a'..'f' }) continue
                        } else if (code.all(Character::isDigit)) {
                            if (code.all { it.toString().toInt() in 0..9}) continue
                        } else {nmbrInvalid++; break@conditions}
                    }
                    "ecl" -> if (value.length != 3 || value !in setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")) {nmbrInvalid++; break@conditions}
                    "pid" -> if (value.toIntOrNull() != null && value.length != 9) {nmbrInvalid++; break@conditions}
                }
            }
        }
    }

    return passes.size - nmbrInvalid
}



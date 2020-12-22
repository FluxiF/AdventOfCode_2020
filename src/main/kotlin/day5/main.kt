package day5

import java.io.File
import kotlin.math.roundToInt

fun main() {
    val input = File("/home/felixfm/adventOfCode_2020/src/main/kotlin/day5/seats.txt").readLines()

    //println(getHighestSeatId(input))
    println(getOurSeatId(input))
}

fun getOurSeatId(seatList: List<String>): Int {
    var seats = mutableListOf<Pair<Int, Int>>()

    for(s in seatList) {
        seats.add(getSeat(s))
    }

    val sortedSeats = seats.sortedWith(compareBy({it.first}, {it.second}))
    var seatRange = 6..113 // hardcoded, should obv be dynamic
    val neighbourSeats = sortedSeats.filter({it.first in seatRange}).zipWithNext().filter {
        if (it.first.first == it.second.first) { // they share a row
            it.second.second - it.first.second != 1 // see if there is any empty chairs between two chair numbers
        } else false
    }
    val row = neighbourSeats[0].first.first
    val chair = neighbourSeats[0].first.second + 1

    return row * 8 + chair
}

fun getHighestSeatId(seatList: List<String>): Int {
    val seatIds = mutableListOf<Int>()

    for (s in seatList) {
        seatIds.add(getSeatId(s))
    }

    return seatIds.maxOrNull() ?: -1 //if no seat id was found, return -1
}

fun getSeatId(seat: String): Int {
    // find row
    var upperRow = 127
    var lowerRow = 0
    for (c in seat.take(seat.length - 3)) {
        when(c) {
            'F' -> upperRow = (upperRow + lowerRow) / 2
            'B' -> lowerRow = ((upperRow.toDouble() + lowerRow.toDouble()) / 2).roundToInt()
        }
    }

    // find seat
    var upperSeat = 7
    var lowerSeat = 0
    for (c in seat.takeLast(3)) {
        when(c) {
            'L' -> upperSeat = (upperSeat + lowerSeat) / 2
            'R' -> lowerSeat = ((upperSeat.toDouble() + lowerSeat.toDouble()) / 2).roundToInt()
        }
    }

    return upperRow * 8 + upperSeat// upperRow == lowerRow, upperSeat == lowerSeat
}

// return pairOf(Row, seat number)
fun getSeat(seat: String): Pair<Int, Int> {
    // find row
    var upperRow = 127
    var lowerRow = 0
    for (c in seat.take(seat.length - 3)) {
        when(c) {
            'F' -> upperRow = (upperRow + lowerRow) / 2
            'B' -> lowerRow = ((upperRow.toDouble() + lowerRow.toDouble()) / 2).roundToInt()
        }
    }

    // find seat
    var upperSeat = 7
    var lowerSeat = 0
    for (c in seat.takeLast(3)) {
        when(c) {
            'L' -> upperSeat = (upperSeat + lowerSeat) / 2
            'R' -> lowerSeat = ((upperSeat.toDouble() + lowerSeat.toDouble()) / 2).roundToInt()
        }
    }

    return Pair(upperRow, upperSeat)// upperRow == lowerRow, upperSeat == lowerSeat
}
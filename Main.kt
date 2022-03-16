
fun main() {

    val list = createList()
    printList(list)


    loopTwo@ while(true) {
        // checkGameState
        if (gameStateO(list)) return

        if (list[0].contains(" ")  || list[1].contains(" ") || list[2].contains(" ")) else break@loopTwo

        loop@ while (list[0].contains(" ") || list[1].contains(" ") || list[2].contains(" ")) {
            var n = readln()

//    check number
            if (isNumber(n)) {
                println("You should enter numbers!")

                break@loop
            }
//  check if correct coordinates
            while (n[0].digitToInt() < 1 || n[0].digitToInt() > 3 || n[2].digitToInt() < 1 || n[2].digitToInt() > 3) {
                println("Coordinates should be from 1 to 3!")

                break@loop
            }
//    check if cell is unoccupied
            while (isCellUnoccupied(n[0].digitToInt() - 1, n[2].digitToInt() - 1, list) != true) {
                println("This cell is occupied! Choose another one!")

                break@loop
            }

//    place X
            placeX(n[0].digitToInt() - 1, n[2].digitToInt() - 1, list)

//      print board
            printList(list)

//      checkGameState
            if (gameStateX(list)) return

            while (true){
                if (gameStateO(list)) return

                if (list[0].contains(" ")  || list[1].contains(" ") || list[2].contains(" ")) else break@loopTwo
                loopthree@  while(true){
                    if (gameStateO(list)) return

                    if (list[0].contains(" ")  || list[1].contains(" ") || list[2].contains(" ")) else break@loopTwo
                    n = readln()

//    check number
                    while (isNumber(n)) {
                        println("You should enter numbers!")
                        break@loopthree
                    }
//  check if correct coordinates
                    while (n[0].digitToInt() < 1 || n[0].digitToInt() > 3 || n[2].digitToInt() < 1 || n[2].digitToInt() > 3) {
                        println("Coordinates should be from 1 to 3!")
                        break@loopthree
                    }
//    check if cell is unoccupied
                    while (isCellUnoccupied(n[0].digitToInt() - 1, n[2].digitToInt() - 1, list) != true) {
                        println("This cell is occupied! Choose another one!")
                        break@loopthree
                    }

//    place O
                    placeO(n[0].digitToInt() - 1, n[2].digitToInt() - 1, list)
//      print board
                    printList(list)
                    break@loop

                }}}
    }
    println("Draw")

}


fun isNumber(s: String?): Boolean {
    return !s.isNullOrEmpty() && s.matches(Regex("\\d+"))
}


fun isCellUnoccupied(inputFirst: Int, inputSecond: Int, list: MutableList<MutableList<String>>): Boolean {
    return list[inputFirst][inputSecond].toString() == " "
}

fun placeX(inputFirst: Int, inputSecond: Int, list: MutableList<MutableList<String>>) {
    list[inputFirst].set(inputSecond, "X")
}

fun placeO(inputFirst: Int, inputSecond: Int, list: MutableList<MutableList<String>>) {
    list[inputFirst].set(inputSecond, "O")
}

fun readUserInput(inputReader: String): MutableList<Int> {
    val inputFirst = inputReader[0].digitToInt() - 1
    val inputSecond = inputReader[2].digitToInt() - 1
    return mutableListOf<Int>(inputFirst, inputSecond)
}

fun createList(): MutableList<MutableList<String>> {
    val iS = "_________".replace("_", " ")
    val list = mutableListOf(
        mutableListOf(iS[0].toString(), iS[1].toString(), iS[2].toString()),
        mutableListOf(iS[3].toString(), iS[4].toString(), iS[5].toString()),
        mutableListOf(iS[6].toString(), iS[7].toString(), iS[8].toString()),
    )
    return list
}

fun printList(list: MutableList<MutableList<String>>) {
    println("--------")
    println("| ${list[0][0]} ${list[0][1]} ${list[0][2]} |")
    println("| ${list[1][0]} ${list[1][1]} ${list[1][2]} |")
    println("| ${list[2][0]} ${list[2][1]} ${list[2][2]} |")
    println("--------")
}


fun gameStateX(list: MutableList<MutableList<String>>): Boolean {
    var xWins = false
    var oWins = false
    when {
        list[0].joinToString("") == "XXX" -> xWins = true
        list[1].joinToString("") == "XXX" -> xWins = true
        list[2].joinToString("") == "XXX" -> xWins = true
        list[0][0] + list[1][1] + list[2][2] == "XXX" -> xWins = true
        list[0][2] + list[1][1] + list[2][0] == "XXX" -> xWins = true
        list[0][0] + list[1][0] + list[2][0] == "XXX" -> xWins = true
        list[0][1] + list[1][1] + list[2][1] == "XXX" -> xWins = true
        list[0][2] + list[1][2] + list[2][2] == "XXX" -> xWins = true

    }
    if (xWins) {
        println("X wins")

    }
    return xWins

}

fun gameStateO(list: MutableList<MutableList<String>>): Boolean {
    var oWins = false
    when {
        list[0].joinToString("") == "OOO" -> oWins = true
        list[1].joinToString("") == "OOO" -> oWins = true
        list[2].joinToString("") == "OOO" -> oWins = true
        list[0][0] + list[1][1] + list[2][2] == "OOO" -> oWins = true
        list[0][2] + list[1][1] + list[2][0] == "OOO" -> oWins = true
        list[0][0] + list[1][0] + list[2][0] == "OOO" -> oWins = true
        list[0][1] + list[1][1] + list[2][1] == "OOO" -> oWins = true
        list[0][2] + list[1][2] + list[2][2] == "OOO" -> oWins = true

    }
    if (oWins) {
        println("O wins")

    }
    return oWins

}
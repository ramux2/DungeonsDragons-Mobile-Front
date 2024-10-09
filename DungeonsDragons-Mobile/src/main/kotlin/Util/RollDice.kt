package org.example.Util

class RollDice {
    companion object {
        fun rollAtributes(): Int {
            val rolling = MutableList(4) { Dices.d6() }
            val minValue = rolling.min()

            // Remover a primeira ocorrência do menor valor
            if (minValue != null) {
                rolling.remove(minValue)
            }
            print("(")
            rolling.forEach {
                print(it)
                print(",")
            }
            print(")")
            val maxSum = rolling.sorted().drop(1).sum()

            return maxSum;
        }
    }
}
package org.example.Util

data class Abilities(
    var strength: Int = 0,
    var dexterity: Int = 0,
    var constitution: Int = 0,
    var intelligence: Int = 0,
    var wisdom: Int = 0,
    var charisma: Int = 0,
    var life: Int = 10
)
{
    // Construtor secundário que recebe uma lista de valores inteiros
    constructor(attributeValues: List<Int>) : this() {
        require(attributeValues.size == 6) { "A lista deve conter exatamente 6 valores." }

        this.strength += attributeValues[0]
        this.dexterity += attributeValues[1]
        this.constitution += attributeValues[2]
        this.intelligence += attributeValues[3]
        this.wisdom += attributeValues[4]
        this.charisma += attributeValues[5]
        this.life += (attributeValues[2] - 10) / 2
    }
}

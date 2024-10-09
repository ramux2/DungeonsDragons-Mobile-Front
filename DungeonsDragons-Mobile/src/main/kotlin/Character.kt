package org.example

import org.example.Races.Race
import org.example.Util.Abilities
import org.example.Util.AttributesModifier

class Character(var race: Race, attributes: List<Int>)  {
    var abilities: Abilities = Abilities(attributes)
    val modifier = AttributesModifier()

    fun generateCharacter() {
        race.applyRacialBonuses(abilities)
        abilities = modifier.applyModifier(abilities)
    }
}
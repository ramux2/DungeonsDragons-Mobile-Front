package org.example.Races

import org.example.Util.Abilities

class Human : Race {
    override fun applyRacialBonuses(abilities: Abilities) {
        abilities.strength += 1
        abilities.dexterity += 1
        abilities.constitution += 1
        abilities.intelligence += 1
        abilities.wisdom += 1
        abilities.charisma += 1
    }
}

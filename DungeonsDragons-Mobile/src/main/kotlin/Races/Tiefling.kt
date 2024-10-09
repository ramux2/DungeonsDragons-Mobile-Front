package org.example.Races

import org.example.Util.Abilities

class Tiefling : Race {
    override fun applyRacialBonuses(abilities: Abilities) {
        abilities.charisma += 2
        abilities.intelligence += 1
    }
}
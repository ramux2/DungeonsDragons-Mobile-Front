package org.example.Races

import org.example.Util.Abilities

class HalfOrc : Race {
    override fun applyRacialBonuses(abilities: Abilities) {
        abilities.constitution += 1
        abilities.strength += 2
    }
}
package org.example.Races

import org.example.Util.Abilities

class Dwarf : Race {
    override fun applyRacialBonuses(abilities: Abilities) {
        abilities.constitution += 2
    }
}
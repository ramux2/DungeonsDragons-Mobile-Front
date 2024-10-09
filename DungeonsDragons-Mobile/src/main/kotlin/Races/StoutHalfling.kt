package org.example.Races

import org.example.Util.Abilities

class StoutHalfling : Race {
    override fun applyRacialBonuses(abilities: Abilities) {
        abilities.constitution += 1
    }
}
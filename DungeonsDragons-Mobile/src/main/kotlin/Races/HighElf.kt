package org.example.Races

import org.example.Util.Abilities

class HighElf : Race {
    override fun applyRacialBonuses(abilities: Abilities) {
        abilities.intelligence += 1
    }
}
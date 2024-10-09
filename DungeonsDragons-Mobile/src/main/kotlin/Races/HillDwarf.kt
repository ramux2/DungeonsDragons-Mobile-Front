package org.example.Races

import org.example.Util.Abilities

class HillDwarf : Race {
    override fun applyRacialBonuses(abilities: Abilities) {
        abilities.wisdom += 1
    }
}
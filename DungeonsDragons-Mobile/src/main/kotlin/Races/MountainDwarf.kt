package org.example.Races

import org.example.Util.Abilities

class MountainDwarf : Race {
    override fun applyRacialBonuses(abilities: Abilities) {
        abilities.strength += 2
    }
}
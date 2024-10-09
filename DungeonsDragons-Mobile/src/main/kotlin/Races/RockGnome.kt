package org.example.Races

import org.example.Util.Abilities

class RockGnome : Race {
    override fun applyRacialBonuses(abilities: Abilities) {
        abilities.constitution += 1
    }
}
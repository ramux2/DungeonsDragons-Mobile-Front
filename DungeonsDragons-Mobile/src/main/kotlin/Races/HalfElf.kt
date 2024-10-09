package org.example.Races

import org.example.Util.Abilities

class HalfElf : Race {
    override fun applyRacialBonuses(abilities: Abilities) {
        abilities.charisma += 1
    }
}
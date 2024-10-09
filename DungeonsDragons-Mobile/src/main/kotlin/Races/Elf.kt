package org.example.Races

import org.example.Util.Abilities

class Elf : Race {
    override fun applyRacialBonuses(abilities: Abilities) {
        abilities.dexterity += 2
    }
}
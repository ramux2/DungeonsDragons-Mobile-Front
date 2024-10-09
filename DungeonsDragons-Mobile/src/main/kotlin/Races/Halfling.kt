package org.example.Races

import org.example.Util.Abilities

class Halfling : Race {
    override fun applyRacialBonuses(abilities: Abilities) {
        abilities.dexterity += 2
    }
}
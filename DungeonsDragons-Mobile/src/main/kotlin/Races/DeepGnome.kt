package org.example.Races

import org.example.Util.Abilities

class DeepGnome : Race {
    override fun applyRacialBonuses(abilities: Abilities) {
        abilities.dexterity += 1
    }
}
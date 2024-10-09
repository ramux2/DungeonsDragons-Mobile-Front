package org.example.Races

import org.example.Util.Abilities

class Drow : Race{
    override fun applyRacialBonuses(abilities: Abilities) {
        abilities.charisma += 1
    }
}
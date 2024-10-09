package org.example.Races

import org.example.Util.Abilities

class Gnome : Race {
    override fun applyRacialBonuses(abilities: Abilities) {
        abilities.intelligence += 2
    }
}
package org.example.Races

import org.example.Util.Abilities

interface Race {
    fun applyRacialBonuses(abilities: Abilities)
}
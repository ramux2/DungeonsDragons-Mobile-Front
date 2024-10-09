package org.example.Races

import org.example.Util.Abilities

class LightfootHalfling : Race {
    override fun applyRacialBonuses(abilities: Abilities) {
        abilities.charisma += 1
    }
}
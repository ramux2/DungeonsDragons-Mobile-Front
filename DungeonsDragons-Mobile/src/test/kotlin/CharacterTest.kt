package org.example

import org.example.Races.Race
import org.example.Util.Abilities
import kotlin.test.assertEquals
import kotlin.test.Test

class CharacterTest {

    @Test
    fun `test generateCharacter with racial bonuses and modifiers`() {
        val mockRace = object : Race {
            override fun applyRacialBonuses(abilities: Abilities) {
                abilities.strength += 2
                abilities.dexterity += 1
            }
        }
        val initialAttributes = listOf(15, 14, 13, 12, 10, 8)

        val character = Character(mockRace, initialAttributes)

        character.generateCharacter()

        assertEquals(20, character.abilities.strength)
        assertEquals(17, character.abilities.dexterity)
    }

    @Test
    fun `test abilities initialization`() {
        val race = object : Race {
            override fun applyRacialBonuses(abilities: Abilities) {

            }
        }
        val initialAttributes = listOf(10, 11, 12, 13, 14, 15)
        val character = Character(race, initialAttributes)

        assertEquals(10, character.abilities.strength)
        assertEquals(11, character.abilities.dexterity)
        assertEquals(12, character.abilities.constitution)
        assertEquals(13, character.abilities.intelligence)
        assertEquals(14, character.abilities.wisdom)
        assertEquals(15, character.abilities.charisma)
    }

    @Test
    fun `test life + constitution modifier`() {
        val race = object : Race {
            override fun applyRacialBonuses(abilities: Abilities) {
                abilities.constitution += 2
            }
        }
        val initialAttributes = listOf(13, 13, 13, 13, 13, 10)
        val character = Character(race, initialAttributes)

        character.generateCharacter()

        assertEquals(13, character.abilities.life)
    }
}

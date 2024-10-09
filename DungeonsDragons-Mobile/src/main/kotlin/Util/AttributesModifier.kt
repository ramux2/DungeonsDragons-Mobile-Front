package org.example.Util

class AttributesModifier {

    //Função para modificar habilidades
    fun applyModifier(abilities: Abilities): Abilities {
        // Modo onde recebe lista de attributes e itera a lista para modificar
//        val modifiedAttributes = attributes.toMutableList()
//
//        attributes.forEachIndexed { index, value ->  modifiedAttributes[index] = value + (value - 10) / 2 }
//        modifiedAttributes.forEachIndexed { index, value ->  println("$index -> $value") }

        // Calcula os modificadores para cada atributo
        val modifiedStrength = abilities.strength + (abilities.strength - 10) / 2
        val modifiedDexterity = abilities.dexterity + (abilities.dexterity - 10) / 2
        val modifiedConstitution = abilities.constitution + (abilities.constitution - 10) / 2
        val modifiedIntelligence = abilities.intelligence + (abilities.intelligence - 10) / 2
        val modifiedWisdom = abilities.wisdom + (abilities.wisdom - 10) / 2
        val modifiedCharisma = abilities.charisma + (abilities.charisma - 10) / 2
        val modifiedLife = 10 + (modifiedConstitution - 10) / 2

        return Abilities(
            strength = modifiedStrength,
            dexterity = modifiedDexterity,
            constitution = modifiedConstitution,
            intelligence = modifiedIntelligence,
            wisdom = modifiedWisdom,
            charisma = modifiedCharisma,
            life = modifiedLife
        )
    }
}
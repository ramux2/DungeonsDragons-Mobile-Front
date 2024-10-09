package org.example

import org.example.Races.*
import org.example.Util.AttributeDistributor
import org.example.Util.AttributesModifier

fun main() {
    var validDistruitedPoints = true;

    val distributor = AttributeDistributor()
    val attributes = mutableListOf<Int>()
    val races = listOf("Anão", "Anão da Colina", "Anão da Montanha", "Elfo", "Alto Elfo", "Elfo da Floresta", "Drow", "Meio-Elfo", "Halfling", "Halfing Pés Leves", "Halfling Robusto", "Humano", "Draconato", "Gnomo", "Gnomo da Floresta", "Gnomo das rochas", "Meio Orc", "Tiefling");

    println("===== Raças =====");

    races.forEachIndexed() { index, race -> println("$index - $race") }

    print("Escolha sua raça: ");
    var chosenRace = readln().toInt();

    val race : Race = when(chosenRace) {
        0 -> Dwarf()
        1 -> HillDwarf()
        2 -> MountainDwarf()
        3 -> Elf()
        4 -> HighElf()
        5 -> WoodElf()
        6 -> Drow()
        7 -> HalfElf()
        8 -> Halfling()
        9 -> LightfootHalfling()
        10 -> StoutHalfling()
        11 -> Human()
        12 -> Dragonborn()
        13 -> Gnome()
        14 -> DeepGnome()
        15 -> RockGnome()
        16 -> HalfOrc()
        17 -> Tiefling()
        else -> Human()
    }

    do {
        println("=== Distribua valores para as respectivas habilidades ===")
        println("Força")
        println("Destreza")
        println("Constituição")
        println("Inteligência")
        println("Sabedoria")
        println("Carisma")

        do {
            print("Digite o valor para habilidade:")
            var value = readln().toInt();
            if (value in 8..15 ) {
                attributes.add(value);
                println("Valor adicionado!")
            } else
                println("Valores de habilidade devem estar entre 8 e 15.")
        } while (attributes.size < 6)

        if (!distributor.distributePoints(attributes))
            attributes.clear();
        else
            validDistruitedPoints = false;

    } while (validDistruitedPoints)


    var myCharacter = Character(race, attributes)
    myCharacter.generateCharacter()
    println(myCharacter.abilities)

    println("\n-+- Seus valores de habilidade -+-\n")

    println("Força: " + myCharacter.abilities.strength)
    println("Destreza: " + myCharacter.abilities.dexterity)
    println("Constituição: " + myCharacter.abilities.constitution)
    println("Inteligencia: " + myCharacter.abilities.intelligence)
    println("Sabedoria: " + myCharacter.abilities.wisdom)
    println("Carisma: " + myCharacter.abilities.charisma)
    println("Vida: " + myCharacter.abilities.life)
}
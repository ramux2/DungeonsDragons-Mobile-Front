package org.example.Util

class AttributeDistributor {

    // Tabela de custos dos valores de habilidade
    private val pointCostTable = mapOf(
        8 to 0,
        9 to 1,
        10 to 2,
        11 to 3,
        12 to 4,
        13 to 5,
        14 to 7,
        15 to 9
    )

    // Função para calcular o custo total da distribuição de atributos
    fun calculateTotalCost(attributes: List<Int>): Int {
        return attributes.sumOf { pointCostTable[it] ?: throw IllegalArgumentException("Valor de habilidade inválido: $it") }
    }

    // Função para verificar se a distribuição é válida
    fun distributePoints(attributes: List<Int>): Boolean {
        // Verifica se a distribuição é válida
        if (attributes.any { it !in 8..15 }) {
            println("Valores de habilidade devem estar entre 8 e 15.")
            return false
        }

        // Calcula o custo total
        val totalCost = calculateTotalCost(attributes)

        // Verifica se o custo total é válido
        return if (totalCost <= 27) {
            println("Distribuição válida! Custo total: $totalCost")
            true
        } else {
            println("Distribuição inválida! Custo total excede os 27 pontos.")
            false
        }
    }
}
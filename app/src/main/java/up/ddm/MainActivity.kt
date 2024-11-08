package up.ddm

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.example.Character
import org.example.Races.*
import org.example.Util.AttributeDistributor
import up.ddm.data.CharacterDAO
import up.ddm.data.CharacterDatabase
import up.ddm.data.CharacterEntity
import up.ddm.ui.theme.Purple40

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = "racaScreen") {
        composable("racaScreen") { RacaScreen(navController) }
        composable("atributosScreen/{raca}/{index}") { backStackEntry ->
            val raca = backStackEntry.arguments?.getString("raca")
            val index = backStackEntry.arguments?.getString("index")?.toIntOrNull()
            AtributosScreen(navController, raca, index)
        }
        composable("resumoScreen/{raceIndex}/{attributes}") { backStackEntry ->
            val raceIndex = backStackEntry.arguments?.getString("raceIndex")?.toIntOrNull()
            val attributes = backStackEntry.arguments?.getString("attributes")
            ResumoScreen(navController, raceIndex, attributes, context)
        }
        composable("listaPersonagensScreen") {
            ListaPersonagensScreen(navController, context)
        }
    }
}


@Composable
fun RacaScreen(navController: NavHostController) {
    // Lista de raças
    val races = listOf(
        "Anão", "Anão da Colina", "Anão da Montanha", "Elfo", "Alto Elfo", "Elfo da Floresta", "Drow",
        "Meio-Elfo", "Halfling", "Halfing Pés Leves", "Halfling Robusto", "Humano", "Draconato", "Gnomo",
        "Gnomo da Floresta", "Gnomo das rochas", "Meio Orc", "Tiefling"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Escolha sua Raça", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Define uma altura específica para o LazyColumn para não ocupar todo o espaço
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), // Usa o weight para garantir que a coluna reserve espaço para o botão
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(races) { index, race ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            // Navega para a tela de atributos passando a raça escolhida
                            navController.navigate("atributosScreen/$race/$index")
                        }
                        .padding(2.dp)
                        .border(
                            BorderStroke(2.dp, Purple40), // Define a borda com cor e espessura
                            shape = RoundedCornerShape(8.dp) // Define um contorno arredondado
                        )
                        .padding(8.dp) // Adiciona padding dentro da borda
                ) {
                    Text(
                        text = race,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate("listaPersonagensScreen")
        }) {
            Text("Ver Personagens Cadastrados")
        }
    }
}


@Composable
fun AtributosScreen(navController: NavHostController, race: String?, raceIndex: Int?) {
    var strength by remember { mutableStateOf("") }
    var dexterity by remember { mutableStateOf("") }
    var constitution by remember { mutableStateOf("") }
    var intelligence by remember { mutableStateOf("") }
    var wisdom by remember { mutableStateOf("") }
    var charisma by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val minAttribute = 8
    val maxAttribute = 15

    val distributor = AttributeDistributor()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Raça selecionada: $race", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        // Inputs para os atributos
        OutlinedTextField(
            value = strength,
            onValueChange = { value -> strength = value },
            label = { Text("Força") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = dexterity,
            onValueChange = { value -> dexterity = value },
            label = { Text("Destreza") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = constitution,
            onValueChange = { value -> constitution = value },
            label = { Text("Constituição") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = intelligence,
            onValueChange = { value -> intelligence = value },
            label = { Text("Inteligência") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = wisdom,
            onValueChange = { value -> wisdom = value },
            label = { Text("Sabedoria") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = charisma,
            onValueChange = { value -> charisma = value },
            label = { Text("Carisma") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Exibe mensagem de erro, se houver
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão de criação de personagem
        Button(onClick = {
            val strengthValue = strength.toIntOrNull()
            val dexterityValue = dexterity.toIntOrNull()
            val constitutionValue = constitution.toIntOrNull()
            val intelligenceValue = intelligence.toIntOrNull()
            val wisdomValue = wisdom.toIntOrNull()
            val charismaValue = charisma.toIntOrNull()

            // Validação dos atributos
            val attributesList = listOfNotNull(strengthValue, dexterityValue, constitutionValue, intelligenceValue, wisdomValue, charismaValue)

            if (strengthValue == null || strengthValue !in minAttribute..maxAttribute ||
                dexterityValue == null || dexterityValue !in minAttribute..maxAttribute ||
                constitutionValue == null || constitutionValue !in minAttribute..maxAttribute ||
                intelligenceValue == null || intelligenceValue !in minAttribute..maxAttribute ||
                wisdomValue == null || wisdomValue !in minAttribute..maxAttribute ||
                charismaValue == null || charismaValue !in minAttribute..maxAttribute
            ) {
                // Se algum atributo estiver fora do intervalo, exibe mensagem de erro
                errorMessage = "Todos os valores de atributo devem estar entre $minAttribute e $maxAttribute."
            } else if(!distributor.distributePoints(attributesList)) {

                // Se o custo total exceder 27 exibe a mensagem de erro
                errorMessage = "Distribuição inválida! Custo total excede os 27 pontos."
            }
            else {
                // Se tudo estiver válido, prossegue para a próxima tela
                errorMessage = ""
                navController.navigate("resumoScreen/${raceIndex}/${attributesList.joinToString(",")}")
            }
        }) {
            Text("Criar Personagem")
        }
    }
}

@Composable
fun ResumoScreen(navController: NavHostController, raceIndex: Int?, attributesString: String?, context: Context) {
    // Converter a string recebida para uma lista de inteiros
    val attributes = attributesString?.split(",")?.mapNotNull { it.toIntOrNull() } ?: listOf(8, 8, 8, 8, 8, 8)


    // Criar um objeto de Race com base no índice
    val race: Race = when(raceIndex) {
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

    // Criar o personagem
    val character = Character(race, attributes)
    character.generateCharacter()

    val characterEntity = CharacterEntity(
        strength = character.abilities.strength,
        dexterity = character.abilities.dexterity,
        constitution = character.abilities.constitution,
        intelligence = character.abilities.intelligence,
        wisdom = character.abilities.wisdom,
        charisma = character.abilities.charisma,
        life = character.abilities.life
    )

    LaunchedEffect(Unit) {
        val db = CharacterDatabase.getDatabase(context)
        db.characterDao().insert(characterEntity)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Resumo do Personagem", style = MaterialTheme.typography.headlineMedium)
        Text("Raça: ${race::class.simpleName}")
        Text("Força: ${character.abilities.strength}")
        Text("Destreza: ${character.abilities.dexterity}")
        Text("Constituição: ${character.abilities.constitution}")
        Text("Inteligência: ${character.abilities.intelligence}")
        Text("Sabedoria: ${character.abilities.wisdom}")
        Text("Carisma: ${character.abilities.charisma}")
        Text("Vida: ${character.abilities.life}")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate("listaPersonagensScreen")
        }) {
            Text("Ver Personagens Cadastrados")
        }
    }
}

@Composable
fun ListaPersonagensScreen(navController: NavHostController, context: Context) {
    // Lista de personagens armazenados no banco
    var characters by remember { mutableStateOf(emptyList<CharacterEntity>()) }

    // Busca os personagens no banco de dados ao carregar a tela
    LaunchedEffect(Unit) {
        val db = CharacterDatabase.getDatabase(context)
        characters = db.characterDao().getAllCharacters()  // Assume que você tenha implementado getAllCharacters()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Personagens Cadastrados",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de personagens
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(characters) { character ->
                CharacterCard(character, context)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate("racaScreen")
        }) {
            Text("Tela Inicial")
        }
    }
}

@Composable
fun CharacterCard(character: CharacterEntity, context: Context) {
    val db = CharacterDatabase.getDatabase(context)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Purple40), shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Column {
            Text("ID: ${character.id}")
            Text("Força: ${character.strength}")
            Text("Destreza: ${character.dexterity}")
            Text("Constituição: ${character.constitution}")
            Text("Inteligência: ${character.intelligence}")
            Text("Sabedoria: ${character.wisdom}")
            Text("Carisma: ${character.charisma}")
            Text("Vida: ${character.life}")
            Spacer(modifier = Modifier.width(8.dp))

            val coroutineScope = rememberCoroutineScope() // Cria um CoroutineScope

            Button(onClick = {
                coroutineScope.launch {
                    db.characterDao().delete(character.id)
                }
            }) {
                Text("Excluir")
            }
        }
    }
}


//@Composable
//fun EditarPersonagemScreen(navController: NavHostController, characterDao: CharacterDAO) {
//    val characterIdState = remember { mutableStateOf("") }
//    val character = remember { mutableStateOf<CharacterEntity?>(null) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        // Campo para inserir o ID do personagem
//        TextField(
//            value = characterIdState.value,
//            onValueChange = { characterIdState.value = it },
//            label = { Text("ID do Personagem") },
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//        )
//
//        // Botão para carregar o personagem
//        Button(onClick = {
//            val id = characterIdState.value.toIntOrNull()
//            if (id != null) {
//                character.value = characterDao.getAllCharacters().find { it.id == id }
//            } else {
//                character.value = null // Se o ID for inválido, limpa o personagem
//            }
//        }) {
//            Text("Carregar Personagem")
//        }
//
//        character.value?.let { personagem ->
//            var name by remember { mutableStateOf(personagem.name) }
//            var race by remember { mutableStateOf(personagem.race) }
//            // Outros atributos que deseja permitir a edição
//
//            TextField(value = name, onValueChange = { name = it }, label = { Text("Nome") })
//            TextField(value = race, onValueChange = { race = it }, label = { Text("Raça") })
//            // Outros campos de edição
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Button(onClick = {
//                // Atualiza o personagem com os novos valores
//                val updatedCharacter = personagem.copy(name = name, race = race)
//                characterDao.update(updatedCharacter)
//                navController.popBackStack()
//            }) {
//                Text("Salvar Alterações")
//            }
//        } ?: run {
//            // Mensagem caso o personagem não seja encontrado
//            Text("Personagem não encontrado. Insira um ID válido.")
//        }
//    }
//}




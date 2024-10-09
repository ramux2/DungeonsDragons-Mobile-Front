package up.ddm

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "atributos")
data class Atributos(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val strength: Int = 0,
    val dexterity: Int = 0,
    val constitution: Int = 0,
    val intelligence: Int = 0,
    val wisdom: Int = 0,
    val charisma: Int = 0,
    val life: Int = 10
)

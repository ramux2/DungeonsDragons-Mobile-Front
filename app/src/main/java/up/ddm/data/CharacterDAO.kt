package up.ddm.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CharacterDAO {
    @Insert
    suspend fun insert(character: CharacterEntity)

    @Query("SELECT * FROM characters")
    suspend fun getAllCharacters(): List<CharacterEntity>

    @Update
    suspend fun update(character: CharacterEntity)

    @Delete
    suspend fun delete(characterId: Int)
}



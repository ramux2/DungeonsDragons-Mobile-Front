package up.ddm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import up.ddm.Atributos

@Database(entities = [Atributos::class], version = 1, exportSchema = false)
abstract class AtributosDB: RoomDatabase() {

    abstract fun atributosDAO(): AtributosDAO

    companion object{

        @Volatile
        private var INSTANCIA: AtributosDB? =null

        fun getDatabase(context: Context): AtributosDB{
            return INSTANCIA ?: synchronized(this){
                val instancia = Room.databaseBuilder(
                    context.applicationContext,
                    AtributosDB::class.java,
                    "atributosDB"
                ).build()
                INSTANCIA = instancia
                return instancia
            }
        }
    }
}
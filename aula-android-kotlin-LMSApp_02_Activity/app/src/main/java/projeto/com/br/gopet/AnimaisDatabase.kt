package projeto.com.br.gopet

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

// anotação define a lista de entidades e a versão do banco
@Database(entities = arrayOf(Animal::class), version = 1)
abstract class AnimaisDatabase : RoomDatabase() {
    abstract fun animalDAO(): AnimalDAO
}
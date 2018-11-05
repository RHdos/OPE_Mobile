package projeto.com.br.gopet

import android.arch.persistence.room.Room

object DatabaseManager {
    // singleton
    private var dbInstance: AnimaisDatabase
    init {
        val appContext = LMSApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
                appContext, // contexto global
                AnimaisDatabase::class.java, // Referência da classe do banco
                "gopet.sqlite" // nome do arquivo do banco

        ).build()
    }
    fun getAnimalDAO(): AnimalDAO {
        return dbInstance.animalDAO()
    }
}
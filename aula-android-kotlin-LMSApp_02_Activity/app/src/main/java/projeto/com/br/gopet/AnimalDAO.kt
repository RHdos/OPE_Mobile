package projeto.com.br.gopet

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface AnimalDAO {

    @Query("SELECT * FROM animal where id = :id")
    fun getById(id: Long) : Animal?

    @Query("SELECT * FROM animal")
    fun findAll(): List<Animal>

    @Insert
    fun insert(animal: Animal)

    @Delete
    fun delete(animal: Animal)
}
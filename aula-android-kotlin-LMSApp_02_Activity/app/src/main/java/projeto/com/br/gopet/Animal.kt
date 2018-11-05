package projeto.com.br.gopet

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.GsonBuilder

@Entity(tableName = "animal")
class Animal {

    @PrimaryKey
    var id:Long = 0

    var nome = ""
    var raca = ""
    var foto = ""
    var tamanho = ""
    override fun toString(): String {
        return "Pet(nome='$nome')"
    }
    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}
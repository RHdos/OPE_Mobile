package projeto.com.br.gopet

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "animal")
class Animal :Serializable {

    @PrimaryKey(autoGenerate = true)
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
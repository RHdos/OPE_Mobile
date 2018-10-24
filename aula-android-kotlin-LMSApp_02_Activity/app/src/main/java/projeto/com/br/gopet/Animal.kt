package projeto.com.br.gopet

import com.google.gson.GsonBuilder

class Animal {
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
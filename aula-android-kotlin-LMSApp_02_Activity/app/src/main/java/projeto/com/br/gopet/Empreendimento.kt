package projeto.com.br.gopet

import com.google.gson.GsonBuilder

class Empreendimento {
    var id:Long = 0
    var nome = ""
    var endereco = ""
    var foto = ""
    var slogan = ""
    override fun toString(): String {
        return "Empresa(nome='$nome')"
    }
    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}
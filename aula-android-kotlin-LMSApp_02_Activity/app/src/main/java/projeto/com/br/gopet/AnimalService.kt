package projeto.com.br.gopet

import android.content.Context
import android.util.Log
import br.com.fernandosousa.lmsapp.HttpHelper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import fernandosousa.com.br.lmsapp.AndroidUtils
import fernandosousa.com.br.lmsapp.Response
import java.net.URL

object AnimalService {

    val host = "http://gopet.pythonanywhere.com"
    //val host = "http://lbernardessilvaoutlookcom.pythonanywhere.com"
    val TAG = "WS_GOPET"
    fun getAnimais (context: Context): List<Animal> {
        var animais = ArrayList<Animal>()

        if(AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/animais"
            val json = HttpHelper.get(url)
            animais = parserJson(json)

            // salvar offline
            for(a in animais){
                saveOffline(a)
            }

            return animais
        } else {
            val dao = DatabaseManager.getAnimalDAO()
            val animais = dao.findAll()
            return animais
        }
    }

    fun save(animal: Animal): Response {
        val json = HttpHelper.post("$host/animais", animal.toJson())
        return parserJson<Response>(json)
    }

    fun saveOffline(animal: Animal): Boolean{
        val dao = DatabaseManager.getAnimalDAO()

        if (! existeAnimal(animal)){
            dao.insert(animal)
        }
        return true
    }
    fun existeAnimal(animal: Animal) : Boolean{
        val dao = DatabaseManager.getAnimalDAO()
        return dao.getById(animal.id) != null
    }

    fun delete(animal: Animal): Response {
        if (AndroidUtils.isInternetDisponivel(LMSApplication.getInstance().applicationContext)){
            val url = "$host/animais/${animal.id}"
            val json = HttpHelper.delete(url)
            return parserJson(json)
        }else{
            val dao = DatabaseManager.getAnimalDAO()
            dao.delete(animal)
            return Response(status = "OK", msg = "Dados salvos localmente")
        }
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }


}
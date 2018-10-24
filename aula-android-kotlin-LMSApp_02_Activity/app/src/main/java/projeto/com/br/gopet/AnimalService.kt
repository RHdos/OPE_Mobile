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

    val host = "http://fesousa.pythonanywhere.com/"
    //val host = "http://lbernardessilvaoutlookcom.pythonanywhere.com"
    val TAG = "WS_LMSApp"
    fun getAnimais (context: Context): List<Animal> {
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/disciplinas"
            val json = HttpHelper.get(url)
            //val json = URL(url).readText()
            //Log.d(TAG, json)
            return parserJson<List<Animal>>(json)
        } else {

            return ArrayList<Animal>()
        }
    }

    fun save(animal: Animal): Response {
        val json = HttpHelper.post("$host/disciplinas", animal.toJson())
        return parserJson<Response>(json)
    }
    fun delete(animal: Animal): Response {
        val url = "$host/disciplinas/${animal.id}"
        val json = HttpHelper.delete(url)
        return parserJson<Response>(json)
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }


}
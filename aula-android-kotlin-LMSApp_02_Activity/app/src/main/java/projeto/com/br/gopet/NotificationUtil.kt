package projeto.com.br.gopet

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat

object NotificationUtil {
    val context:Context get() = LMSApplication.getInstance().applicationContext
    val CHANNEL_ID = "1"

    fun createChannel(){
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val appName = context.getString(R.string.app_name)
            var c = NotificationChannel(CHANNEL_ID, appName, NotificationManager.IMPORTANCE_HIGH)

            manager.createNotificationChannel(c)

        }
    }

    fun create(id: Int, intent: Intent, titulo: String, texto:String){

        createChannel()

        val p = PendingIntent.getActivity(context, 0,intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentIntent(p) // intent de direcionamento
                .setContentTitle(titulo) // titulo notificacao
                .setContentText(texto) // texto da notificacao
                .setSmallIcon(R.drawable.imagem_login)// icone da notificacao
                .setAutoCancel(true)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC) // exibir notificao mesmo com tela bloqueada

        with(NotificationManagerCompat.from(LMSApplication.getInstance())){
            val n = builder.build() // Cria a Build da notificacao
            notify(id,n) // Executa a notificacao
        }

    }
}
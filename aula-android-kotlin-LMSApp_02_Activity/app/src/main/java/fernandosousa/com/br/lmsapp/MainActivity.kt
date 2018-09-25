package fernandosousa.com.br.lmsapp

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.login.*

class MainActivity : DebugActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // encontra objeto pelo id
        val imagem = findViewById<ImageView>(R.id.campo_imagem)
        imagem.setImageResource(R.drawable.imagem_login)

        val texto = findViewById<TextView>(R.id.texto_login)
        texto.text = getString(R.string.mensagem_login)


        val botaoLogin = findViewById<Button>(R.id.botao_login)

        // evento no botao de login forma 1
//        botaoLogin.setOnClickListener {
//            val campoUsuario = findViewById<EditText>(R.id.campo_usuario)
//            val campoSenha = findViewById<EditText>(R.id.campo_senha)
//            val valorUsuario = campoUsuario.text.toString()
//            val valorSenha = campoSenha.text.toString()
//            Toast.makeText(this, "$valorUsuario : $valorSenha", Toast.LENGTH_LONG).show()
//        }

        // segunda forma: delegar para método
        botaoLogin.setOnClickListener {onClickLogin() }

    }

    fun onClickLogin(){
        val usuario = "aluno"
        val senha = "impacta"
        //val campoUsuario = findViewById<EditText>(R.id.campo_usuario)
        val campoUsuario = campo_usuario.text.toString()
        //val campoSenha = findViewById<EditText>(R.id.campo_senha)
        val campoSenha =  campo_senha.text.toString()
        //val valorUsuario = campoUsuario.text.toString()
        //val valorSenha = campoSenha.text.toString()
        //Toast.makeText(context, "$valorUsuario : $valorSenha", Toast.LENGTH_LONG).show()

        // criar intent
        val intent = Intent(context, TelaInicialActivity::class.java)
        // colocar parâmetros (opcional)
        val params = Bundle()

        params.putString("usuario", campoUsuario)
        params.putString("Senha", campoUsuario)
        intent.putExtras(params)
        if(campoUsuario.equals(usuario) && campoSenha.equals(senha)){
            // enviar parâmetros simplificado
            intent.putExtra("nome", campoUsuario)

            // fazer a chamada esperando resultado
            startActivityForResult(intent, 1)

        }
        //if(campoUsuario == "aluno" && campoSenha == "impacta"){intent.putExtras(params)}
        else{
            // fazer a chamada esperando resultado
            Toast.makeText(context, "Usuário ou senha incorretos", Toast.LENGTH_LONG).show()
        }



        // fazer a chamada
        //startActivity(intent)



    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            val result = data?.getStringExtra("result")
            Toast.makeText(context, "$result", Toast.LENGTH_LONG).show()
        }
        else if (requestCode == 0){
            texto_login.text ="Usuário ou senha incorretos"
        }
    }
}

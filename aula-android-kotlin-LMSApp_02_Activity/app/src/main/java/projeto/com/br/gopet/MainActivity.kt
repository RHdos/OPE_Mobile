package projeto.com.br.gopet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*

class MainActivity : DebugActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        //setContentView(R.layout.relative_layout)


        // encontra objeto pelo id
        val imagem = findViewById<ImageView>(R.id.campo_imagem)
        imagem.setImageResource(R.drawable.imagem_login)

        val texto = findViewById<TextView>(R.id.texto_login)
        texto.text = getString(R.string.mensagem_login)


        val botaoLogin = findViewById<Button>(R.id.botao_login)

        // segunda forma: delegar para método
        botaoLogin.setOnClickListener {onClickLogin() }
        supportActionBar?.title = "GOPET"
    }

    fun onClickLogin(){
        val campoUsuario = findViewById<EditText>(R.id.campo_usuario)
        val campoSenha = findViewById<EditText>(R.id.campo_senha)
        val valorUsuario = campoUsuario.text.toString()
        val valorSenha = campoSenha.text.toString()
        //Toast.makeText(context, "$valorUsuario : $valorSenha", Toast.LENGTH_LONG).show()

        // criar intent
        val intent = Intent(context, TelaInicialActivity::class.java)
        // colocar parâmetros (opcional)
        val params = Bundle()
        params.putString("nome", valorUsuario)
        intent.putExtras(params)

        // enviar parâmetros simplificado
        intent.putExtra("numero", 10)
        val senha = "impacta"
        val login = "aluno"
        // fazer a chamada
        //startActivity(intent)
        if (valorSenha.equals(senha) && valorUsuario.equals(login)){
            // fazer a chamada esperando resultado
            startActivityForResult(intent, 1)
        }else{
            Toast.makeText(this,"Senha ou Login inválidos !!!",Toast.LENGTH_SHORT).show()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            val result = data?.getStringExtra("result")
            Toast.makeText(context, "$result", Toast.LENGTH_LONG).show()
        }
    }
}

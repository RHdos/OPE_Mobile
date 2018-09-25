package fernandosousa.com.br.lmsapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.*
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import fernandosousa.com.br.lmsapp.R.id.*


class TelaInicialActivity : DebugActivity(){

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        // acessar parametros da intnet
        // intent é um atributo herdado de Activity
        val args = intent.extras
        // recuperar o parâmetro do tipo String
        val nome = args.getString("nome")

        // recuperar parâmetro simplificado
        val numero = intent.getIntExtra("nome",0)

        Toast.makeText(context, "Parâmetro: $nome", Toast.LENGTH_LONG).show()
        Toast.makeText(context, "Numero: $numero", Toast.LENGTH_LONG).show()

        val mensagem = findViewById<TextView>(R.id.mensagemInicial)
        mensagem.text = "Bem vindo $nome"

        // criar intent
        val intent = Intent(context, TelaActionActivity::class.java)
        // colocar parâmetros (opcional)
        val params = Bundle()

        val botaoSair = findViewById<Button>(R.id.botaoSair)
        botaoSair.setOnClickListener {cliqueSair()}

        val botaoBuscar = findViewById<Button>(R.id.botaoBuscar)
        botaoBuscar.setOnClickListener {
            intent.putExtra("titulo", "Buscar")
            startActivityForResult(intent, 1)
        }

        val botaoConfiguracao = findViewById<Button>(R.id.botaoConfiguracao)
        // enviar parâmetros simplificado
        botaoConfiguracao.setOnClickListener {
            intent.putExtra("titulo", "Configuração")
            startActivityForResult(intent, 1)
        }


        // colocar toolbar
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // alterar título da ActionBar
        supportActionBar?.title = "Disciplinas"
        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun cliqueSair() {
        val returnIntent = Intent();
        returnIntent.putExtra("result","Saída do BrewerApp");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main, menu)
        // vincular evento de buscar
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                // ação enquanto está digitando
                Toast.makeText(context, "$newText", Toast.LENGTH_LONG).show()
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // ação  quando terminou de buscar e enviou
                Toast.makeText(context, "$query", Toast.LENGTH_LONG).show()
                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == action_buscar){
            Toast.makeText(context, "Botão de buscar", Toast.LENGTH_LONG).show()
        }else if (id == R.id.action_atualizar){
            Toast.makeText(this,
                    "Clicou atualizar",
                    Toast.LENGTH_SHORT).show()
        }else if (id == android.R.id.home){
            finish()
        }else if(id == action_sair){
            cliqueSair()
        }else if(id == action_config){

        }

        return super.onOptionsItemSelected(item)
    }

}

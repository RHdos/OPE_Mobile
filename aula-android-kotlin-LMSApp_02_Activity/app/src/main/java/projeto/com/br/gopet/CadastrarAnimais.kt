package projeto.com.br.gopet

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastrar_animais.*
import kotlinx.android.synthetic.main.activity_dados_pessoais.*
import kotlinx.android.synthetic.main.toolbar.*
import android.R.attr.button
import android.widget.Button
import android.widget.EditText


class CadastrarAnimais : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_dados -> {
                Toast.makeText(this, "Cadastre suas informações", Toast.LENGTH_SHORT).show()
                supportActionBar?.title = "Meus Dados"
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
            R.id.nav_cadastrar_animais -> {
                Toast.makeText(this, "Cadastre animais", Toast.LENGTH_SHORT).show()
                supportActionBar?.title = "Cadastrar Animais"
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
            R.id.nav_buscar_animais -> {
                Toast.makeText(this, "Busque por Animais", Toast.LENGTH_SHORT).show()
                supportActionBar?.title = "Buscar Animais"
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
            R.id.nav_buscar_empreendimentos -> {
                Toast.makeText(this, "Busque por empreendimentos", Toast.LENGTH_SHORT).show()
                supportActionBar?.title = "Buscar Empreendimentos"
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
            R.id.nav_sair -> {
                cliqueSair()
            }

        }
        layoutMenuLateral3.closeDrawer(GravityCompat.START)
        return true
    }

    fun configuraMenuLateral(nome_usuario: String) {
        val toolbar = toolbar
        val layoutMenuLateral = layoutMenuLateral3
        var toggle = ActionBarDrawerToggle(this, layoutMenuLateral, toolbar, R.string.drawer_open, R.string.drawer_close)

        layoutMenuLateral.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = menu_tela_inicial3
        var text_nome = navigationView.getHeaderView(0).findViewById(R.id.nome_menu_lateral) as TextView
        text_nome.text = "$nome_usuario"
        navigationView.setNavigationItemSelectedListener(this)

    }

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_animais)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Cadastrar Animais"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val botaoSalvar = findViewById<Button>(R.id.submit_animal)
        botaoSalvar.setOnClickListener {
            val animal = Animal()
            animal.nome = input_nome_animal.text.toString()
            animal.raca = input_raca_animal.text.toString()
            animal.tamanho = input_tamanho_animal.text.toString()
            animal.foto = urlFoto.text.toString()
            taskAtualizar(animal)
        }

        //val nome_menu = findViewById<TextView>(R.id.nome_menu_lateral)
        //nome_menu.text = "$nome"
    }
    private fun taskAtualizar(animal: Animal) {
        // Thread para salvar o animal
        Thread {
            if(AndroidUtils.isInternetDisponivel(context)) {
                AnimalService.save(animal)
            }else{
                AnimalService.saveOffline(animal)
            }
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }
    fun cliqueSair() {
        val returnIntent = Intent();
        returnIntent.putExtra("result","Saiu do GOPET!");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if(id == R.id.action_buscar){
            Toast.makeText(this,"Clicou buscar: ", Toast.LENGTH_SHORT).show()
        }else if (id == R.id.action_atualizar){
            Toast.makeText(this,"Clicou para atualizar: ", Toast.LENGTH_SHORT).show()

        }else if (id == R.id.action_config){
            Toast.makeText(this,"Clicou no Configurações: ", Toast.LENGTH_SHORT).show()
        }
        else if (id == android.R.id.home){
            Toast.makeText(this,"Clicou no voltar: ", Toast.LENGTH_SHORT).show()
            cliqueSair()
        }
        return super.onOptionsItemSelected(item)
    }


}
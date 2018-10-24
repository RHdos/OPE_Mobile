package projeto.com.br.gopet

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_buscar_animais.*
import kotlinx.android.synthetic.main.activity_dados_pessoais.*
import kotlinx.android.synthetic.main.toolbar.*

class BuscarAnimais : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var animais = listOf<Animal>()
    var recyclerAnimais: RecyclerView? = null
    private val context: Context get() = this

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
           layoutMenuLateral1.closeDrawer(GravityCompat.START)
           return true
       }

    fun configuraMenuLateral(nome_usuario: String) {
           val toolbar = toolbar
           val layoutMenuLateral = layoutMenuLateral1
           var toggle = ActionBarDrawerToggle(this, layoutMenuLateral, toolbar, R.string.drawer_open, R.string.drawer_close)

           layoutMenuLateral.addDrawerListener(toggle)
           toggle.syncState()

           val navigationView = menu_tela_inicial1
           var text_nome = navigationView.getHeaderView(0).findViewById(R.id.nome_menu_lateral) as TextView
           text_nome.text = "$nome_usuario"
           navigationView.setNavigationItemSelectedListener(this)

       }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_animais)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Buscar Animais"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerAnimais = findViewById<RecyclerView>(R.id.recyclerAnimais)
        recyclerAnimais?.layoutManager = LinearLayoutManager(context)
        recyclerAnimais?.itemAnimator = DefaultItemAnimator()
        recyclerAnimais?.setHasFixedSize(true)
       //configuraMenuLateral(nome)

        //val nome_menu = findViewById<TextView>(R.id.nome_menu_lateral)
        //nome_menu.text = "$nome"
    }
    override fun onResume() {
        super.onResume()
        // task para recuperar as animais
        taskAnimais()
    }
    fun taskAnimais() {
        Thread {
            this.animais = AnimalService.getAnimais(context)
            runOnUiThread {
                recyclerAnimais?.adapter = AnimalAdapter(animais) { onClickAnimal(it) }
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

    fun onClickAnimal(animal: Animal) {
        Toast.makeText(context, "Clicou animal ${animal.nome}", Toast.LENGTH_SHORT)
                .show()
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
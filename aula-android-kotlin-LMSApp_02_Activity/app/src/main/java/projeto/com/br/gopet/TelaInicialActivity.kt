package projeto.com.br.gopet
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*
import projeto.com.br.gopet.R.id.layoutMenuLateral5
import projeto.com.br.gopet.R.id.recyclerAnimais

//import projeto.com.br.gopet.R.id.toolbar

class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    var recyclerAnimais: RecyclerView? = null
    private var animais = listOf<Animal>()
    //private val context: Context get() = this
    fun enviaNotificacao() {
        // Intent para abrir tela quando clicar na notificação
        val intent = Intent(this, BuscarAnimais::class.java)
        // intent.putExtra("animal",animal)
        NotificationUtil.create(1, intent,"Buscar Animais","Adote um de nossos animais")
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
                //recyclerAnimais?.adapter = AnimalAdapter(animais) { onClickAnimal(it) }
                enviaNotificacao()
            }

        }.start()
    }
    /*fun onClickAnimal(animal: Animal) {
        Toast.makeText(context, "Clicou animal ${animal.nome}", Toast.LENGTH_SHORT)
                .show()
    }*/


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_dados -> {
                Toast.makeText(this,"Cadastre suas informações",Toast.LENGTH_SHORT).show()

                val intent = Intent(context, DadosPessoais::class.java)
                //val params = Bundle()


                startActivityForResult(intent, 1)
            }
            R.id.nav_cadastrar_animais -> {
                Toast.makeText(this,"Cadastre animais",Toast.LENGTH_SHORT).show()
                val intent = Intent(context, CadastrarAnimais::class.java)
                //val params = Bundle()


                startActivityForResult(intent, 1)
            }
            R.id.nav_buscar_animais -> {
                Toast.makeText(this,"Busque por Animais",Toast.LENGTH_SHORT).show()
                val intent = Intent(context, BuscarAnimais::class.java)
                //val params = Bundle()


                startActivityForResult(intent, 1)
            }
            R.id.nav_buscar_empreendimentos -> {
                Toast.makeText(this,"Busque por empreendimentos",Toast.LENGTH_SHORT).show()
                val intent = Intent(context, BuscarEmpreendimentos::class.java)
                //val params = Bundle()


                startActivityForResult(intent, 1)
            }
            R.id.nav_sair -> {
                cliqueSair()
            }

        }
        layoutMenuLateral5.closeDrawer(GravityCompat.START)
        return true
    }


    fun configuraMenuLateral(nome_usuario:String){
        val toolbar = toolbar
        val layoutMenuLateral = layoutMenuLateral5
        var toggle = ActionBarDrawerToggle(this,layoutMenuLateral,toolbar, R.string.drawer_open,R.string.drawer_close)

        layoutMenuLateral.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = menu_tela_inicial5
        var text_nome = navigationView.getHeaderView(0).findViewById(R.id.nome_menu_lateral) as TextView
        text_nome.text = "$nome_usuario"
        navigationView.setNavigationItemSelectedListener(this)

    }

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
//        val numero = intent.getIntExtra("nome",0)

        //Toast.makeText(context, "Parâmetro: $nome", Toast.LENGTH_LONG).show()
        //Toast.makeText(context, "Numero: $numero", Toast.LENGTH_LONG).show()

        val mensagem = findViewById<TextView>(R.id.mensagemInicial)
        mensagem.text = "Bem vindo $nome"



        setSupportActionBar(toolbar)

        supportActionBar?.title = "HOME"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        configuraMenuLateral(nome)

        //val nome_menu = findViewById<TextView>(R.id.nome_menu_lateral)
        //nome_menu.text = "$nome"
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

package projeto.com.br.gopet

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.squareup.picasso.Picasso

class AnimalAdapter (
        val animais: List<Animal>,
        val onClick: (Animal) -> Unit):
        RecyclerView.Adapter<AnimalAdapter.AnimaisViewHolder>() {

    // ViewHolder com os elementos da tela
    class AnimaisViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val cardNome: TextView
        val cardImg : ImageView
        var cardProgress: ProgressBar
        var cardView: CardView
        init {
            cardNome = view.findViewById<TextView>(R.id.cardNome)
            cardImg = view.findViewById<ImageView>(R.id.cardImg)
            cardProgress = view.findViewById<ProgressBar>(R.id.cardProgress)
            cardView = view.findViewById<CardView>(R.id.card_animais)
        }
    }
    // Quantidade de animais na lista
    override fun getItemCount() = this.animais.size

    // inflar layout do adapter
    override fun onCreateViewHolder(
            parent: ViewGroup, viewType: Int): AnimaisViewHolder {

        // infla view no adapter
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_animal, parent, false)
        // retornar ViewHolder
        val holder = AnimaisViewHolder(view)
        return holder
    }
    // bind para atualizar Views com os dados
    override fun onBindViewHolder(holder: AnimaisViewHolder, position: Int) {
        val context = holder.itemView.context
        // recuperar objeto animal
        val animal = animais[position]
        // atualizar dados de animal
        holder.cardNome.text = animal.nome
        holder.cardProgress.visibility = View.VISIBLE
        // download da imagem
        Picasso.with(context).load(animal.foto).fit().into(holder.cardImg,
                object: com.squareup.picasso.Callback{
                    override fun onSuccess() {
                        holder.cardProgress.visibility = View.GONE
                    }
                    override fun onError() {
                        holder.cardProgress.visibility = View.GONE
                    }
                })
        // adiciona evento de clique
        holder.itemView.setOnClickListener {onClick(animal)}
    }
}
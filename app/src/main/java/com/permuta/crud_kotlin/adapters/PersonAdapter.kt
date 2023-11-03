package com.permuta.crud_kotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.permuta.crud_kotlin.R
import com.permuta.crud_kotlin.entities.Person

class PersonAdapter : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {
    private var list = mutableListOf<Person>()
    private var actionEdit: ((Person) -> Unit)? = null
    private var actionDelete: ((Person) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item_person_view_holder, parent, false)

        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = list[position]

        holder.vName.text = person.name
        holder.vEmail.text = person.email
        holder.vPhone.text = person.phone
        holder.vbirthDate.text = person.birthDate
        holder.vAddress.text = person.address

        holder.actionEdit.setOnClickListener{actionEdit?.invoke(person)}
        holder.actionDelete.setOnClickListener{actionDelete?.invoke(person)}
    }

    override fun getItemCount() = list.size

    fun setData(data: List<Person>){
        list.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    fun setOnActionEditListener(callback: (Person) -> Unit){
        this.actionEdit = callback
    }

    fun setOnActionDeleteListener(callback: (Person) -> Unit){
        this.actionDelete = callback
    }

//VERIFICAR DE VAI DAR BOM COM OS NOMES NO V_NOME E VNAME

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vName : TextView = itemView.findViewById(R.id.v_nome)
        val vEmail : TextView = itemView.findViewById(R.id.v_email)
        val vPhone : TextView = itemView.findViewById(R.id.v_telefone)
        val vbirthDate : TextView = itemView.findViewById(R.id.v_data_nascimento)
        val vAddress : TextView = itemView.findViewById(R.id.v_endereco)

        val actionEdit: ImageView = itemView.findViewById(R.id.action_alterar)
        val actionDelete: ImageView = itemView.findViewById(R.id.action_excluir)
    }
}
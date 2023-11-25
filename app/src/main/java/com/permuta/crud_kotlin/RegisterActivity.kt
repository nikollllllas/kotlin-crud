package com.permuta.crud_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.permuta.crud_kotlin.databases.AppDatabase
import com.permuta.crud_kotlin.databinding.ActivityRegisterBinding
import com.permuta.crud_kotlin.entities.Person
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private var person: Person? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        person = intent.getSerializableExtra("Data") as? Person

        if(person == null){
            binding.buttonCadastroAtualizar.text = "Cadastrar"
            binding.cadastro.text = "Cadastro"
        }
        else {
            binding.cadastro.text = "Atualização"
            binding.buttonCadastroAtualizar.text = "Atualizar"
            binding.nome.setText(person?.name.toString())
            binding.email.setText(person?.email.toString())
            binding.telefone.setText(person?.phone.toString())
            binding.endereco.setText(person?.address.toString())
            binding.nascimento.setText(person?.birthDate.toString())
        }

        binding.buttonCadastroAtualizar.setOnClickListener{ addPerson() }

    }

    private fun addPerson() {
        val name = binding.nome.text.toString()
        val email = binding.email.text.toString()
        val phone = binding.telefone.text.toString()
        val address = binding.endereco.text.toString()
        val birthDate = binding.nascimento.text.toString()

        lifecycleScope.launch {
            if (person == null){
                val person = Person(
                    name = name,
                    email = email,
                    birthDate = birthDate,
                    phone = phone,
                    address = address
                )
                AppDatabase(this@RegisterActivity).getPersonDao().addPerson(person)
                finish()
            } else {
                val p = Person(name, email, birthDate, phone, address)
                p.id = person?.id ?: 0
                AppDatabase(this@RegisterActivity).getPersonDao().updatePerson(p)
                finish()
            }
        }
    }
}
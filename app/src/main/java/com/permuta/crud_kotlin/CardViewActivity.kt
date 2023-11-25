package com.permuta.crud_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.permuta.crud_kotlin.adapters.PersonAdapter
import com.permuta.crud_kotlin.databases.AppDatabase
import com.permuta.crud_kotlin.databinding.ActivityCardViewBinding
import com.permuta.crud_kotlin.entities.Person
import kotlinx.coroutines.launch


class CardViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCardViewBinding
    private var mAdapter: PersonAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.adicionar.setOnClickListener(){
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setAdapter(list: List<Person>){
        mAdapter?.setData(list)
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            val personList = AppDatabase(this@CardViewActivity).getPersonDao().getALLPerson()

            mAdapter = PersonAdapter()
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(this@CardViewActivity)
                adapter = mAdapter
                setAdapter(personList)

                mAdapter?.setOnActionEditListener {
                    val intent = Intent(this@CardViewActivity, RegisterActivity::class.java)
                    intent.putExtra("Data", it)
                    startActivity(intent)
                }

                mAdapter?.setOnActionDeleteListener {
                    val builder = AlertDialog.Builder(this@CardViewActivity)
                    builder.setMessage("Tem certeza que quer excluir?")
                    builder.setPositiveButton("Sim") { p0, p1 ->
                        lifecycleScope.launch {
                            AppDatabase(this@CardViewActivity).getPersonDao().deletePerson(it)
                            val pList = AppDatabase(this@CardViewActivity).getPersonDao().getALLPerson()
                            setAdapter(pList)
                        }
                        p0.dismiss()
                    }

                    builder.setNegativeButton("Não") { p0, p1 ->
                        p0.dismiss()
                    }

                    val dialog = builder.create()
                    dialog.show()
                }
            }
        }
    }


}
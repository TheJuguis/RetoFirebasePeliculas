package com.example.retofirebasepeliculas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.retofirebasepeliculas.databinding.ActivityMainBinding
import com.google.firebase.database.FirebaseDatabase
import org.json.JSONArray
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.ktx.database
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = Firebase.database
        val myRef = database.reference

        binding.btnGuardar.setOnClickListener{
            val Id = binding.etId.text.toString()

            val Title = binding.etTitulo.text.toString()
            val Year = binding.etAnio.text.toString()
            val imdbID = binding.etimdbID.text.toString()
            val Type = binding.etTipo.text.toString()
            val Poster = binding.etPoster.text.toString()
            val Country = binding.etPais.text.toString()
            val Genre = binding.etGenero.text.toString()



            //Subir a la firebase


            myRef.child("Cartelera").child(Id).setValue(Peliculas(
                Id, Title, Year, imdbID, Type, Poster, Country, Genre))
            Toast.makeText(this, "Pelicula Guardada", Toast.LENGTH_SHORT).show()


        }



        binding.btnBusqueda.setOnClickListener{


            val myDB = FirebaseDatabase.getInstance()

            val valor = binding.etBusqueda.text.toString()
            myRef.child("Cartelera").child(valor).get().addOnSuccessListener { record ->
                val json = JSONObject(record.value.toString())
                Log.d("ValoresFirebase", "got value ${record.value}")
                binding.idMuestra.setText(json.getString("id").toString())
                binding.tituloMuestra.setText(json.getString("title").toString())
                binding.anioMuestra.setText(json.getString("year").toString())
                binding.imdbIDMuestra.setText(json.getString("imdbID").toString())
                binding.tipoMuestra.setText(json.getString("type").toString())
                binding.posterMuestra.setText(json.getString("poster").toString())
                binding.paisMuestra.setText(json.getString("country").toString())
                binding.generoMuestra.setText(json.getString("genre").toString())

            }


        }

    }

}
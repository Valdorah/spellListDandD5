package com.valdorah.mangareader

import android.content.Context
import android.util.Log
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest

class DataController {
    private val principalPath = "http://dnd5eapi.co/api/spells/"
    private lateinit var context: Context
    private val spellList = mutableListOf<Spell>()
    private lateinit var spell: Spell

    fun request(context: Context, callback: (List<Spell>) -> Unit) {
        this.context = context
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, this.principalPath, null,

            Response.Listener { response ->
                val spellList = response.getJSONArray("results")

                for (i in 0 until spellList.length()) {
                    val item = spellList.getJSONObject(i)
                    val spell = Spell(item.getString("name"), item.getString("url"))
                    this.spellList.add(spell)
                }
                callback.invoke(this.spellList)
//                Log.d("TEST_JSON", spellList.toString())
            },

            Response.ErrorListener { error ->
                Log.d("TEST_JSON", "ERROR : $error")
            }
        )

        MySingleton.getInstance(this.context).addToRequestQueue(jsonObjectRequest)
    }

    fun requestDetails(context: Context, spell: Spell, callback: (Spell) -> Unit){
        this.context = context
        this.spell = spell
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, this.spell.url, null,

            Response.Listener { response ->
                this.spell.desc = response.getString("desc")
                this.spell.school = response.getJSONObject("school").getString("name")
                this.spell.level = response.getInt("level")
                callback.invoke(this.spell)
            },

            Response.ErrorListener { error ->
                Log.d("TEST_JSON", "ERROR : $error")
            }
        )

        MySingleton.getInstance(this.context).addToRequestQueue(jsonObjectRequest)
    }
}
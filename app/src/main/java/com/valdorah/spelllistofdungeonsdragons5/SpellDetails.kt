package com.valdorah.spelllistofdungeonsdragons5


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.valdorah.mangareader.DataController
import com.valdorah.mangareader.Spell
import kotlinx.android.synthetic.main.fragment_spell_details.*

class SpellDetails : Fragment() {
    private lateinit var spell: Spell
    private lateinit var dc: DataController
    private lateinit var fieldName: TextView
    private lateinit var fieldLevel: TextView
    private lateinit var fieldSchool: TextView
    private lateinit var fieldDesc: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.spell = Spell(arguments?.getString("spell_name") ?: "undefined", arguments?.getString("spell_url") ?: "undefined")
        return inflater.inflate(R.layout.fragment_spell_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.dc = DataController()
        this.fieldName = detail_spell_name
        this.fieldLevel = detail_spell_level
        this.fieldSchool = detail_spell_school
        this.fieldDesc = detail_spell_desc

        activity?.let {
            this.dc.requestDetails(it.applicationContext, this.spell) { spell ->
                this.spell = spell
                Log.d("TEST_JSONjj", this.spell.toString())
                this.fieldName.text = this.spell.name
                this.fieldDesc.text = this.spell.desc
                this.fieldLevel.text = this.spell.level.toString()
                this.fieldSchool.text = this.spell.school

            }
        }
    }
}

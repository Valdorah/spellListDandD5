package com.valdorah.mangareader


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import com.valdorah.spelllistofdungeonsdragons5.MainActivity
import com.valdorah.spelllistofdungeonsdragons5.R
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {
    private lateinit var dc: DataController
    private lateinit var list: ListView
    private lateinit var spellBook: List<Spell>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.dc = DataController()
        this.list = spell_list

        activity?.let {
            this.dc.request(it.applicationContext) { spellList ->
                spellBook = spellList
                val adapt = SpellListAdapter(it.applicationContext, this.spellBook)
                this.list.adapter = adapt
            }
        }

        this.list.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            (activity as MainActivity).test(this.spellBook[position])
        }
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//    }

//    override fun onResume() {
//        super.onResume()
//        activity?.let {
//            this.dc.request(it.applicationContext) { spellList ->
//                spellBook = spellList
//                val adapt = SpellListAdapter(it.applicationContext, this.spellBook)
//                this.list.adapter = adapt
//            }
//        }
//
//        this.list.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
//
//            (activity as MainActivity).test(this.spellBook[position])
//        }
//    }
}

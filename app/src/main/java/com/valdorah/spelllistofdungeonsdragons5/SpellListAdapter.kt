package com.valdorah.mangareader

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.valdorah.spelllistofdungeonsdragons5.R
import kotlinx.android.synthetic.main.list_spell_item.view.*

class SpellListAdapter(context: Context, list: List<Spell>) : BaseAdapter() {
    private var spellList: List<Spell> = list
    private var inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutItem: ConstraintLayout = if (convertView == null){
            this.inflater.inflate(R.layout.list_spell_item, parent, false) as ConstraintLayout
        } else{
            convertView as ConstraintLayout
        }

        val spellName: TextView = layoutItem.spell_name

        spellName.text = spellList[position].name

        return layoutItem
    }

    override fun getItem(position: Int): Any {
        return this.spellList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return this.spellList.size
    }

}
package com.valdorah.spelllistofdungeonsdragons5

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.valdorah.mangareader.ListFragment
import com.valdorah.mangareader.Spell
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(main_activity != null){
            if (savedInstanceState != null) {
                return
            }

            val list = ListFragment()
            supportFragmentManager.beginTransaction().add(R.id.main_activity, list).commit()
        }
    }

    fun test(s: Spell){
        val details = SpellDetails()
        val args = Bundle()

        args.putString("spell_name" , s.name)
        args.putString("spell_url", s.url)

        details.arguments = args

        supportFragmentManager.beginTransaction().replace(R.id.main_activity, details).addToBackStack(null).commit()
    }
}

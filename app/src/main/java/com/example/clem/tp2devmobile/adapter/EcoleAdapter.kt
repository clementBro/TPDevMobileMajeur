package com.example.clem.tp2devmobile.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.ColorSpace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.example.clem.tp2devmobile.R
import com.example.clem.tp2devmobile.models.EcolePrimaire

/**
 * Created by Clem on 02/02/2018.
 */
class EcoleAdapter(context: Context, data: List<EcolePrimaire>) : BaseAdapter() {

    private val myContext : Context
    private val ecolesList : List<EcolePrimaire>
    private val inflator : LayoutInflater


    init {
        myContext = context
        ecolesList = data
        inflator = LayoutInflater.from(context)
    }

    override fun getItem(position: Int): EcolePrimaire {
        return ecolesList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return ecolesList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        //get layout components
        val view : View = inflator?.inflate(R.layout.list_ecole_item, parent, false)
        val name : TextView = view.findViewById(R.id.name)
        val nbEleve : TextView = view.findViewById(R.id.nbStudent)
        val addresse : TextView = view.findViewById(R.id.address)

        // set layout text
        val ecole : EcolePrimaire = getItem(position)
        name.setText(ecole.nom)
        nbEleve.setText(ecole.nbEleve.toString() + " élèves")
        addresse.setText(ecole.addresse)

        //set background
        if(ecole.nbEleve < 150)
            view.setBackgroundColor(Color.GREEN)
        if(ecole.nbEleve > 150 && ecole.nbEleve < 300)
            view.setBackgroundColor(Color.rgb(255,165,0))
        if(ecole.nbEleve > 300)
            view.setBackgroundColor(Color.RED)

        return view
    }

    override fun OnItemCli
}
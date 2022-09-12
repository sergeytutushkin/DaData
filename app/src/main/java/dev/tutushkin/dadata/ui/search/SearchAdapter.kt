package dev.tutushkin.dadata.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import dev.tutushkin.dadata.R
import dev.tutushkin.dadata.domain.models.Search

class SearchAdapter(context: Context, resource: Int, objects: MutableList<Search>) :
    ArrayAdapter<Search>(context, resource, objects) {

    private val inflater = LayoutInflater.from(context)
    private var layout: Int = resource
    private val list = objects

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = inflater.inflate(layout, parent, false)

        val textValue = view.findViewById<TextView>(R.id.text_value)
        val textInn = view.findViewById<TextView>(R.id.text_inn)

        val suggestion = list[position]

        textValue.text = suggestion.value
        textInn.text = suggestion.inn

        return view
    }
}

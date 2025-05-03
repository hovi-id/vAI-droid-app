package com.goodwy.dialer.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.goodwy.commons.adapters.MyRecyclerViewAdapter
import com.goodwy.commons.helpers.VIEW_TYPE_GRID
import com.goodwy.commons.views.MyRecyclerView
import com.goodwy.dialer.R
import com.goodwy.dialer.activities.SimpleActivity
import com.goodwy.dialer.fragments.DIDCommFragment
import com.goodwy.dialer.fragments.DIDCommFragment.Credential

class DIDCommAdapter(
    activity: SimpleActivity,
    private var items: ArrayList<DIDCommFragment.DIDConnection>,
    recyclerView: MyRecyclerView,
    var viewType: Int,
    private val onItemClick: (DIDCommFragment.DIDConnection) -> Unit  // Renamed to avoid conflict
) : MyRecyclerViewAdapter(activity, recyclerView, items as (Any) -> Unit) {

    // Add this method to update items
    fun updateItems(newItems: ArrayList<DIDCommFragment.DIDConnection>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutRes = when (this.viewType) {
            VIEW_TYPE_GRID -> R.layout.item_did_connection_grid
            else -> R.layout.item_did_connection_list
        }
        val view = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
        return createViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val connection = items[position]
        (holder as DIDConnectionViewHolder).bind(connection)
        holder.itemView.setOnClickListener { onItemClick(connection) }
    }

    override fun getItemCount() = items.size
    override fun actionItemPressed(id: Int) {
        TODO("Not yet implemented")
    }

    override fun getActionMenuId(): Int {
        TODO("Not yet implemented")
    }

    override fun getIsItemSelectable(position: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun getItemKeyPosition(key: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getItemSelectionKey(position: Int): Int? {
        TODO("Not yet implemented")
    }

    override fun getSelectableItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onActionModeCreated() {
        TODO("Not yet implemented")
    }

    override fun onActionModeDestroyed() {
        TODO("Not yet implemented")
    }

    override fun prepareActionMode(menu: Menu) {
        TODO("Not yet implemented")
    }

    inner class DIDConnectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameView: TextView = view.findViewById(R.id.connection_name)
        private val didView: TextView = view.findViewById(R.id.connection_did)
        private val iconView: ImageView = view.findViewById(R.id.connection_icon)

        fun bind(connection: DIDCommFragment.DIDConnection) {
            nameView.text = connection.name
            didView.text = connection.did
            iconView.setImageResource(connection.iconRes)
        }
    }
}

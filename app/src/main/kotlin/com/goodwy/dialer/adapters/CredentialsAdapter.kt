package com.goodwy.dialer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.goodwy.dialer.R
import androidx.recyclerview.widget.RecyclerView
import com.goodwy.dialer.activities.SimpleActivity
import com.goodwy.dialer.extensions.formatDate
import com.goodwy.dialer.fragments.DIDCommFragment

class CredentialsAdapter(
    private var credentials: List<DIDCommFragment.Credential>,
    private val recyclerView: RecyclerView,
    private val onCredentialClick: (DIDCommFragment.Credential) -> Unit,
    activity: SimpleActivity
) : RecyclerView.Adapter<CredentialsAdapter.CredentialViewHolder>() {

    fun updateItems(newCredentials: List<DIDCommFragment.Credential>) {
        credentials = newCredentials
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CredentialViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_credential, parent, false)
        return CredentialViewHolder(view)
    }

    override fun onBindViewHolder(holder: CredentialViewHolder, position: Int) {
        val credential = credentials[position]
        holder.bind(credential)
        holder.itemView.setOnClickListener { onCredentialClick(credential) }
    }

    override fun getItemCount() = credentials.size

    inner class CredentialViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val issuerView: TextView = itemView.findViewById(R.id.credential_issuer)
        private val typeView: TextView = itemView.findViewById(R.id.credential_type)
        private val dateView: TextView = itemView.findViewById(R.id.credential_date)
        private val iconView: ImageView = itemView.findViewById(R.id.credential_icon)

        fun bind(credential: DIDCommFragment.Credential) {
            issuerView.text = credential.issuer
            typeView.text = credential.type
            dateView.text = credential.dateIssued.formatDate()
            iconView.setImageResource(getIconForCredentialType(credential.type))
        }

        private fun getIconForCredentialType(type: String): Int {
            return when {
                else -> R.drawable.ic_check_circle_vector
            }
        }
    }
}

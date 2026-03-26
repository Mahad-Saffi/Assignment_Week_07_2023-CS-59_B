package com.example.contact_app_recycler_view

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(
    private val contactList: MutableList<Contact>,
    private val listener: OnContactActionListener
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    interface OnContactActionListener {
        fun onItemClick(contact: Contact)
        fun onEditClick(contact: Contact)
        fun onDeleteClick(contact: Contact)
    }

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivProfile: ImageView = itemView.findViewById(R.id.ivProfile)
        val tvContactName: TextView = itemView.findViewById(R.id.tvContactName)
        val tvContactPhone: TextView = itemView.findViewById(R.id.tvContactPhone)
        val btnEdit: Button = itemView.findViewById(R.id.btnEdit)
        val btnDelete: Button = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentContact = contactList[position]

        holder.tvContactName.text = currentContact.name
        holder.tvContactPhone.text = currentContact.phone

        if (!currentContact.photoUri.isNullOrBlank()) {
            holder.ivProfile.setImageURI(Uri.parse(currentContact.photoUri))
            if (holder.ivProfile.drawable == null) {
                holder.ivProfile.setImageResource(android.R.drawable.ic_menu_myplaces)
            }
        } else {
            holder.ivProfile.setImageResource(android.R.drawable.ic_menu_myplaces)
        }

        holder.itemView.setOnClickListener {
            listener.onItemClick(currentContact)
        }

        holder.btnEdit.setOnClickListener {
            listener.onEditClick(currentContact)
        }

        holder.btnDelete.setOnClickListener {
            listener.onDeleteClick(currentContact)
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }
}
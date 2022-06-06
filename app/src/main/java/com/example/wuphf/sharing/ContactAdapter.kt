package com.example.drilltwo

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wuphf.R
import com.example.wuphf.databinding.ContactLayoutBinding
import com.example.wuphf.sharing.Contact

class ContactAdapter(private val contacts:List<Contact>, private val listener : ContactListener) : RecyclerView.Adapter<ContactAdapter.ItemViewHolder>() {

    interface ContactListener  {
        fun onContactClicked(index: Int)
        fun onContactLongClicked(index: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(ContactLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = holder.bind(contacts[position])

    override fun getItemCount() = contacts.size

    inner class ItemViewHolder(private val binding: ContactLayoutBinding)
        : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener, View.OnLongClickListener {

        init {
            binding.root.setOnClickListener(this)
            binding.root.setOnLongClickListener(this)
        }

        override fun onClick(p0: View?) {
            listener.onContactClicked(adapterPosition)
        }

        override fun onLongClick(p0: View?): Boolean {
            listener.onContactLongClicked(adapterPosition)
            return true
        }

        fun bind(contact: Contact) {
            binding.contactName.text = contact.name
            binding.contactNumber.text = contact.number
            if (contact.imageUri != null) binding.contactImage.setImageURI(Uri.parse(contact.imageUri))
            else binding.contactImage.setImageResource(R.mipmap.ic_launcher_round)
        }
    }
}
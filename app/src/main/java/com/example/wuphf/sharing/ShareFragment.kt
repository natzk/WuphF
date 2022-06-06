package com.example.wuphf.sharing

import android.content.ActivityNotFoundException
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drilltwo.ContactAdapter
import com.example.wuphf.R
import com.example.wuphf.databinding.FragmentShareBinding
import java.lang.Exception

class ShareFragment : Fragment() {
    private var _binding : FragmentShareBinding? = null
    private val binding get() = _binding!!
    private var contactList: MutableList<Contact> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShareBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initContactList()
    }

    private fun initRecycler() {
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = ContactAdapter(contactList,object : ContactAdapter.ContactListener {
            override fun onContactClicked(index: Int) {
                val appChoice = arguments?.getString("appChoice")
                val contact = contactList[index]
                val number = contact.number
                val message = arguments?.getString("message")
                if (appChoice != null && message != null) chooseAppAndComposeMessage(appChoice, number, message)
            }
            override fun onContactLongClicked(index: Int) {
            }
        })
    }

    private fun chooseAppAndComposeMessage(appChoice: String, number: String, message: String) {
        when (appChoice) {
            "sms" -> composeSmsMessage(number, message)
            "whatsapp" -> composeWhatsappMessage(number, message)
        }
    }

    private fun initContactList() {
        val contacts: Cursor? = requireActivity().contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)

        while (contacts != null && contacts.moveToNext()) {
            val name = contacts.getString(contacts.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val number = contacts.getString(contacts.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val imageUri = contacts.getString(contacts.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Photo.PHOTO_URI))
            if (contactList.size > 0 && number != null && name == contactList[contactList.size - 1].name) continue
            val contact = Contact(name, number, imageUri)
            contactList.add(contact)
            binding.recycler.adapter?.notifyDataSetChanged()
        }
        contacts?.close()
    }

    private fun composeSmsMessage(number: String, message: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("smsto:")
            putExtra("address", number)
            putExtra("sms_body", message)
        }
        try { requireContext().startActivity(intent) }
        catch (e: Exception) { Toast.makeText(requireContext(), "REPLACE THIS", Toast.LENGTH_LONG).show()
        }
    }

    private fun composeWhatsappMessage(inputNumber: String, message: String) {
        var number = inputNumber
        if (inputNumber[0] == '0') number = "+972" + inputNumber.substring(1)
        val url ="https://api.whatsapp.com/send?phone=$number&text=$message"
        val intent = Intent(Intent.ACTION_VIEW).apply {
            this.data = Uri.parse(url)
            this.`package` = "com.whatsapp"
        }
        try { requireContext().startActivity(intent) }
        catch (e: ActivityNotFoundException) { Toast.makeText(requireContext(), "REPLACE THIS", Toast.LENGTH_LONG).show()
        }
    }
}
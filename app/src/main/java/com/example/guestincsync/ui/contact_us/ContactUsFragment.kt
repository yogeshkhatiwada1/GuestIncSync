package com.example.guestincsync.ui.contact_us

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.guestincsync.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ContactUsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_contact_us, container, false)

        // Set click listener for ImageViewPhone
        val phone: ImageView? = view.findViewById(R.id.imageViewPhone)
        phone?.setOnClickListener {
            onDialer()
        }

        // Set click listener for other social media icons
        val facebook: ImageView? = view.findViewById(R.id.imageViewFacebook)
        facebook?.setOnClickListener {
            openSocialMedia("https://www.facebook.com/yogesh.khatiwada.773")
        }

        val linkedin: ImageView? = view.findViewById(R.id.imageViewLinkedin)
        linkedin?.setOnClickListener {
            openSocialMedia("https://www.linkedin.com/in/yogesh-khatiwada-b490381b9?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app")
        }

        val whatsapp: ImageView? = view.findViewById(R.id.imageViewWhatsapp)
        whatsapp?.setOnClickListener {
            openWhatsapp("+977-9807962829")
        }

        val twitter: ImageView? = view.findViewById(R.id.imageViewTwitter)
        twitter?.setOnClickListener {
            openSocialMedia("https://twitter.com")
        }

        return view
    }

    private fun onDialer() {
        val phoneNumber = "+977-9807962829"
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:$phoneNumber")
        startActivity(dialIntent)
    }

    private fun openSocialMedia(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun openWhatsapp(number: String) {
        val uri = Uri.parse("https://wa.me/$number")
        val whatsappIntent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(whatsappIntent)
    }

}

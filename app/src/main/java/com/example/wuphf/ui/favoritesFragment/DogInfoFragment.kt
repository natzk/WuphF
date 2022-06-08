package com.example.wuphf.ui.favoritesFragment

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.wuphf.BuildConfig
import com.example.wuphf.databinding.FragmentDogInfoBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


class DogInfoFragment : Fragment() {

    private var _binding : FragmentDogInfoBinding? = null
    private val binding get() = _binding!!

    private val viewModel : FavoritesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDogInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRemoveButton()
        initShareButton()
    }

    private fun initRemoveButton() {
        binding.removeButton.setOnClickListener {
//            viewModel.removeSelectedItem()
            activity?.onBackPressed()
        }
    }

    private fun initShareButton() {
        binding.shareButton.setOnClickListener {
            val b = getBitmapFromView(binding.rootLayout)
            saveImageExternal(b!!)

            val file = File(requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "to-share.png")

            var temp = FileProvider.getUriForFile(
                Objects.requireNonNull(requireContext()),
                BuildConfig.APPLICATION_ID + ".provider", file);

            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, temp)
                type = "image/png"
            }
            startActivity(Intent.createChooser(shareIntent, null))

//            val file = File(context?.filesDir, "to-share.png")
//            val u: Uri = FileProvider.getUriForFile(
//                requireContext(),
//                "com.example.wuphf.provider",
//                file)
//
//
//
//            val intent = Intent()
//            intent.setAction(Intent.ACTION_SEND)
//            intent.setPackage("com.whatsapp")
//            intent.putExtra(Intent.EXTRA_TEXT, "hi")
//            intent.putExtra(Intent.EXTRA_STREAM, u)
//            intent.type = ("image/png")

//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

//            startActivity(intent)
            // Toast.makeText(context, "DID THIS WORK?", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getBitmapFromView(view: View): Bitmap? {
        val bitmap =
            Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        Log.d("test", bitmap.toString())
        return bitmap
    }

    private fun saveImageExternal(image: Bitmap): Uri? {
        //TODO - Should be processed in another thread
        var uri: Uri? = null
        try {
            val file: File =
                File(requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "to-share.png")
            val stream = FileOutputStream(file)
            image.compress(Bitmap.CompressFormat.PNG, 90, stream)
            stream.close()
            uri = Uri.fromFile(file)
        } catch (e: IOException) {
            Log.d("TEST", "IOException while trying to write file for sharing: " + e.message)
        }
        return uri
    }

}
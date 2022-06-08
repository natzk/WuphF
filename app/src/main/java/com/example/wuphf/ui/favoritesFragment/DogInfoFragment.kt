package com.example.wuphf.ui.favoritesFragment

import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.wuphf.databinding.FragmentDogInfoBinding
import java.io.File
import java.io.FileOutputStream


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
        initPicButton()
    }

    private fun initRemoveButton() {
        binding.removeButton.setOnClickListener {
//            viewModel.removeSelectedItem()
            activity?.onBackPressed()
        }
    }

    private fun initPicButton() {
        binding.pic.setOnClickListener {
            val b = getBitmapFromView(binding.dogImage)

            saveImageExternal(b!!)

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
        Log.d("TAG", "Successful? 1")
        val file = File(context?.filesDir, "to-share.png")
        val stream = FileOutputStream(file)
        image.compress(Bitmap.CompressFormat.PNG, 90, stream)
        stream.close()
        Log.d("test", "Successfully saved file?")
        uri = Uri.fromFile(file)
        return uri
    }

}
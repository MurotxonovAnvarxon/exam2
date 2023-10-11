package uz.gita.imtihonexample2.presentation.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import uz.gita.imtihonexample2.R
import uz.gita.imtihonexample2.data.room.ContactEntity
import uz.gita.imtihonexample2.databinding.SecondScreenBinding
import java.net.URL

class SecondScreen : Fragment(R.layout.second_screen) {
    val binding: SecondScreenBinding by viewBinding(SecondScreenBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val data = arguments?.getSerializable("data") as ContactEntity
        binding.age.text = data.age.toString()
        binding.email.text = data.email.toString()
        binding.firstName.text = data.firstName.toString()
        binding.lastName.text = data.lastName
        binding.title.text = data.title
        binding.phone.text = data.phone
        Glide.with(binding.root).load(data.image).into(binding.circleImage)
        binding.buttonblack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
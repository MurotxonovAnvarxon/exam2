package uz.gita.imtihonexample2.presentation.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.imtihonexample2.R
import uz.gita.imtihonexample2.databinding.ScreenHomeBinding
import uz.gita.imtihonexample2.presentation.adapter.MyAdapter
import uz.gita.imtihonexample2.presentation.screen.viewmodel.ScreenViewModel
import uz.gita.imtihonexample2.presentation.screen.viewmodel.ScreenViewModelImpl
import uz.gita.imtihonexample2.toEntity

class ScreenHome : Fragment(R.layout.screen_home) {

    private val binding by viewBinding(ScreenHomeBinding::bind)
    private val adapter = MyAdapter()
    private lateinit var vm: ScreenViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this)[ScreenViewModelImpl::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycle.adapter = adapter
        vm.contactsLiveData.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list.map { it })
        }
        vm.loadDAta()
        setAdapterClick()
    }


    fun setAdapterClick() {
        adapter.setClicklistener {
            val bundle = Bundle()
            bundle.putSerializable("data", it)
            findNavController().navigate(R.id.action_screenHome_to_secondScreen, bundle)
        }
    }




}
package com.example.a4kwallpapers.bottom_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.a4kwallpapers.R
import com.example.a4kwallpapers.adapters.PhotoAdapter
import com.example.a4kwallpapers.databinding.FragmentPopularBinding
import com.example.a4kwallpapers.databinding.FragmentTabBinding
import com.example.a4kwallpapers.models2.Hit
import com.example.a4kwallpapers.viewmodels.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PopularFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PopularFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentPopularBinding
    lateinit var photoAdapter: PhotoAdapter
    lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPopularBinding.inflate(layoutInflater,container,false)

        photoAdapter = PhotoAdapter(object:PhotoAdapter.OnItemClickListener{
            override fun onItemClick(hit: Hit?) {
                var bundle = Bundle()
                bundle.putSerializable("wall",hit!!.largeImageURL)
                findNavController().navigate(R.id.rawImageFragment,bundle)
            }

        })
        binding.rvAll.adapter = photoAdapter

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        userViewModel.word = "Famous person"
        userViewModel.liveData.observe(this, Observer {

            GlobalScope.launch(Dispatchers.Main) {
                photoAdapter.submitData(it)
            }
        })

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PopularFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PopularFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
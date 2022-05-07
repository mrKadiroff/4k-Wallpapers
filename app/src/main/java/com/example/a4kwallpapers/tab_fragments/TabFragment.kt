package com.example.a4kwallpapers.tab_fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a4kwallpapers.R
import com.example.a4kwallpapers.adapters.PhotoAdapter
import com.example.a4kwallpapers.databinding.FragmentHomeBinding
import com.example.a4kwallpapers.databinding.FragmentTabBinding
import com.example.a4kwallpapers.models.Photos
import com.example.a4kwallpapers.models2.Rasmlar
import com.example.a4kwallpapers.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"


/**
 * A simple [Fragment] subclass.
 * Use the [TabFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TabFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var categoryID: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            categoryID = it.getInt(ARG_PARAM1)

        }
    }
    lateinit var binding: FragmentTabBinding
    lateinit var photoAdapter: PhotoAdapter
    private val TAG = "TabFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTabBinding.inflate(layoutInflater,container,false)


        RetrofitClient.apiService.getListPhotos("27240519-6e85e045b4edde1049de33f01","spring","photo",true,1,15).enqueue(object:
        Callback<Rasmlar>{
            override fun onResponse(call: Call<Rasmlar>, response: Response<Rasmlar>) {
                val body = response.body()
                Log.d(TAG, "onResponse: ${body!!.hits}")
                photoAdapter = PhotoAdapter(body.hits)
                binding.rvAll.adapter = photoAdapter
            }

            override fun onFailure(call: Call<Rasmlar>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }


        })


//        RetrofitClient.apiService.getImageResults("27240519-6e85e045b4edde1049de33f01","car",12,100)!!.enqueue(object :
//        Callback<Rasmlar?>{
//            override fun onResponse(call: Call<Rasmlar?>, response: Response<Rasmlar?>) {
//                if (response.isSuccessful){
//                    Log.d(TAG, "onResponse: ${response.body()!!.hits}")
//                }
//            }
//
//            override fun onFailure(call: Call<Rasmlar?>, t: Throwable) {
//                Log.d(TAG, "onFailure: ${t.message}")
//            }
//
//        })

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TabFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(categoryID: Int) =
            TabFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, categoryID)
                }
            }
    }
}
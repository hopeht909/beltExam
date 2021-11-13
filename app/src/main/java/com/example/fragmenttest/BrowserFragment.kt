package com.example.fragmenttest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmenttest.adapters.RVAdapter
import com.example.fragmenttest.model.MyViewModel
import com.example.fragmenttest.retrofit.APIClient
import com.example.fragmenttest.retrofit.APIInterface
import com.example.fragmenttest.retrofit.TvShows
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BrowserFragment : Fragment() {

    lateinit var viewModel: MyViewModel
    lateinit var etSearch : EditText
    lateinit var btSearch: Button
    lateinit var rvMain : RecyclerView


    private lateinit var info: ArrayList<TvShows.TvShowsItem>
    var apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_browser, container, false)

        etSearch = view.findViewById(R.id.etSearch)
        btSearch = view.findViewById(R.id.btSearch)
        rvMain = view.findViewById(R.id.rvMain)
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        info =  arrayListOf()

        rvMain.adapter = RVAdapter(info,this)
        rvMain.layoutManager = LinearLayoutManager(requireContext())
        btSearch.setOnClickListener {
            if (etSearch.text.isNotEmpty() ) {

                requestAPI()
                etSearch.text.clear()
                etSearch.clearFocus()
                info.clear()

            } else {
                Toast.makeText(activity, "please add a name", Toast.LENGTH_SHORT)
                    .show()
            }}

        return view
    }

    private fun requestAPI() {
        apiInterface?.getShowData(etSearch.text.toString())?.enqueue(object : Callback<TvShows?> {
            override fun onResponse(call: Call<TvShows?>, response: Response<TvShows?>) {
                for(photo in response.body()!!) {
                    info.add(photo!!)
                }
                rvMain.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<TvShows?>, t: Throwable) {
                Toast.makeText(activity, "Error!", Toast.LENGTH_SHORT).show();
            }

        })
    }

    fun addShow(showName: String, language: String, summary:String, image: String){
        viewModel.addShow(showName,language, summary, image)
    }
}
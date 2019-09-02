package com.example.dog24.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.dog24.R
import com.example.dog24.viewmodel.ListViewMode
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private lateinit var viewModel:ListViewMode
    private  var dogListAdaptor= DogListAdaptor(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel= ViewModelProviders.of(this).get(ListViewMode::class.java)
        viewModel.refresh()

        dogsList.apply {
            layoutManager=LinearLayoutManager(context)
            adapter=dogListAdaptor
        }

        refreshLayout.setOnRefreshListener {
            dogsList.visibility=View.GONE
            listError.visibility=View.GONE
            loadingView.visibility=View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing=false
        }
        observeViewModel()

    }

    private fun observeViewModel() {
      viewModel.dogs.observe(this, Observer { manyDods->
          manyDods?.let {
              dogsList.visibility=View.VISIBLE
              dogListAdaptor.updateDogList(manyDods)
          }
      })
        viewModel.dogsLoadError.observe(this, Observer { isError->
            isError?.let {
                listError.visibility=if (it) View.VISIBLE else View.GONE
            }
        })
        viewModel.loading.observe(this, Observer { isLoading->
            isLoading?.let {
                loadingView.visibility=if (it) View.VISIBLE else View.GONE
                if (it){
                    dogsList.visibility=View.GONE
                    listError.visibility=View.GONE
                }
            }
        })
    }


}

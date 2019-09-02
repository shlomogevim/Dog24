package com.example.dog24.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.dog24.R
import com.example.dog24.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel

    private  var dogId=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            dogId= DetailFragmentArgs.fromBundle(it).dogUuid
        }
        viewModel=ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.fetch()
        observeIt()
    }

    private fun observeIt() {
        viewModel.dog.observe(this, Observer { dog1->
            dog1?.let {
                dogName.text=dog1.dogBreed
                dogLifespan.text=dog1.lifeSpan
                dogPurpose.text=dog1.bredFor
                dogTemprament.text=dog1.temperament
            }
        })


    }

}

/*

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
*
*
*
* */






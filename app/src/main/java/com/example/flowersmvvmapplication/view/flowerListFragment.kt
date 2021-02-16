package com.example.flowersmvvmapplication.view

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowersmvvmapplication.R
import com.example.flowersmvvmapplication.adapter.flowersAdapter
import com.example.flowersmvvmapplication.databinding.ActivityMainBinding
import com.example.flowersmvvmapplication.databinding.FragmentFlowerListBinding
import com.example.flowersmvvmapplication.viewmodel.FlowersViewModel
import kotlinx.coroutines.flow.flow


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [flowerListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class flowerListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var mRecyclerView: RecyclerView?= null
    private var mAdapter: flowersAdapter?= null
    private var pDialog: ProgressDialog?= null

    private var _binding: FragmentFlowerListBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val flowersViewModel by lazy {
            ViewModelProviders.of(this).get(FlowersViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentFlowerListBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_flower_list,
                container,
                false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = flowersViewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialRecyclerView()
        intialaizeProgress()
        retrieveData()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        hideDialog()
    }
/*
    override fun onDestroy() {
        super.onDestroy()
        hideDialog()
    }*/

    private fun hideDialog() {
        pDialog!!.dismiss()
        pDialog = null
    }

    private fun intialaizeProgress() {
        pDialog = ProgressDialog(context)
        // Showing progress dialog before making http request
        pDialog!!.setMessage("Loading...")
        pDialog!!.show()
    }

    private fun initialRecyclerView() {
        this.mRecyclerView = view!!.findViewById(R.id.list)
        this.mRecyclerView!!.layoutManager = LinearLayoutManager(context)
        this.mRecyclerView!!.itemAnimator = DefaultItemAnimator()
    }

    private fun hidePDialog() {
        if (pDialog != null) {
            pDialog!!.dismiss()
            pDialog = null
        }
    }

    fun retrieveData() {
        mAdapter = flowersAdapter(
            R.layout.card_row,
            context!!
        )
            mRecyclerView!!.adapter = mAdapter
            hidePDialog()

        flowersViewModel.flowersList?.observe(viewLifecycleOwner, { flowersList ->
            flowersList?.apply {
                mAdapter?.mItems = this
            }
        })
    }
}
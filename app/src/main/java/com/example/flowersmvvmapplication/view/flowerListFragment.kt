package com.example.flowersmvvmapplication.view

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowersmvvmapplication.R
import com.example.flowersmvvmapplication.adapter.flowersAdapter
import com.example.flowersmvvmapplication.api.ApiInterface
import com.example.flowersmvvmapplication.api.RetrofitClient
import com.example.flowersmvvmapplication.model.flowerModel
import com.example.flowersmvvmapplication.util.RxUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import io.reactivex.Observer
import retrofit2.Call

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

    /**
     * Subscription that represents a group of Subscriptions that are unsubscribed together. */
    private val _subscriptions = CompositeSubscription()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.recycler_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrieveData()
        initialRecyclerView()
        intialaizeProgress()
    }

    override fun onResume() {
        super.onResume()
        RxUtils.unsubscribeIfNotNull(_subscriptions)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        hideDialog()
    }

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
        val apiService: ApiInterface = RetrofitClient.getClient()!!.create(ApiInterface::class.java)
        apiService.getFlowerslist()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<flowerModel>?> {
                override fun onSubscribe(d: Disposable) {}

                override fun onError(e: Throwable) {
                    /*hidePDialog();*/
                    Log.d("message", e.message!!)
                }

                override fun onComplete() {
                    println("Done")
                }

                override fun onNext(flowerModel: List<flowerModel>) {
                    //Call that passes the adapter to the view
                    mAdapter = flowersAdapter(
                            flowerModel!!,
                        R.layout.card_row,
                        context!!
                    )
                    mRecyclerView!!.adapter = mAdapter
                    hidePDialog()
                }
            })

        /*apiService.getImageUrl("image")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<>)*/

    }
}
package com.example.jsonplaceholderretrofitkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonplaceholderretrofitkotlin.adapter.ApiAdapter
import com.example.jsonplaceholderretrofitkotlin.databinding.ActivityMainBinding
import com.example.jsonplaceholderretrofitkotlin.model.ApiModel
import com.example.jsonplaceholderretrofitkotlin.service.ApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), ApiAdapter.Listener {
    private val baseUrl = "https://raw.githubusercontent.com/atilsamancioglu/"
    private var apiModels : ArrayList<ApiModel>? = null
    private var compositeDisposable : CompositeDisposable? = null
    private lateinit var recyclerView : RecyclerView
    private lateinit var apiAdapter: ApiAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        compositeDisposable = CompositeDisposable()

        val layout : RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layout

        getData()
    }

    private fun getData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build().create(ApiService::class.java)

        val getAlbums = retrofit.getAlbumsData()

        compositeDisposable?.add(
            getAlbums
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse)
        )
    }

    private fun handleResponse(apiList : List<ApiModel>) {
        apiModels = ArrayList(apiList)

        apiModels?.let {
            apiAdapter = ApiAdapter(it, this@MainActivity)
        }

        binding.recyclerView.adapter = apiAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }

    override fun onItemClick(apiModel: ApiModel) {
        Toast.makeText(this, "Currency: ${apiModel.currency}\nPrice: ${apiModel.price}", Toast.LENGTH_LONG).show()
    }
}
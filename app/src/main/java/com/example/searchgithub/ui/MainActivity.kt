package com.example.searchgithub.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.searchgithub.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    private val recyclerviewAdapter: MainAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBinding()
        observeData()

    }

    private fun initBinding(){
        with(binding){
            recyclerView.run{
                adapter=recyclerviewAdapter
                itemAnimator = null
                // rcyclerView 최하단 스크롤 감지
                addOnScrollListener(object: RecyclerView.OnScrollListener(){
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if(!recyclerView.canScrollVertically(1)){
                            page++
                            progressBar.visibility = View.VISIBLE
                            searchGitHub()
                        }
                    }
                })
            }
            // 검색 버튼
            btnSearch.setOnClickListener {
                if(edtSearch.text.isNotEmpty()){
                    imgLogo.visibility = View.GONE
                    load()
                }
            }
            // 입력창
            edtSearch.setOnEditorActionListener(object : TextView.OnEditorActionListener{
                override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                    imgLogo.visibility = View.GONE
                    load()
                    return false
                }

            })
        }
    }

    private fun searchGitHub(){
        with(viewModel){
            getGitHub(searchWord,page)
        }

    }
    private fun observeData(){
        with(viewModel){
            getDataRepository.observe(this@MainActivity){
                if(it.isEmpty()){
                    binding.progressBar1.visibility=View.GONE
                    binding.progressBar.visibility=View.GONE
                    Toast.makeText(applicationContext,"찾으시는 레포지토리가 없습니다.",Toast.LENGTH_SHORT).show()
                }else{
                    it.forEach{ RepositoryModel->

                        val avatar = RepositoryModel.avatar_url ?: ""
                        val name = RepositoryModel.full_name ?: ""
                        val language = RepositoryModel.language ?: ""

                        if(page==0){
                            recyclerviewAdapter.setList(avatar,name,language)
                        }else{
                            recyclerviewAdapter.moreList(avatar,name,language)
                        }

                        binding.progressBar.visibility = View.GONE
                        binding.progressBar1.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                    }
                }


            }
            isError.observe(this@MainActivity){
                binding.progressBar.visibility = View.GONE
                binding.progressBar1.visibility = View.GONE
                Toast.makeText(applicationContext,"다시 시도해주세요.",Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun load(){
        recyclerviewAdapter.clearList()
        page=0
        searchWord = binding.edtSearch.text.toString()
        binding.recyclerView.visibility = View.GONE
        binding.progressBar.visibility=View.GONE
        binding.progressBar1.visibility = View.VISIBLE

        searchGitHub()

        // 키패드 내려가는 함수
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.edtSearch.windowToken, 0)
    }

    companion object{
        var page: Int = 0
        var searchWord: String = ""
    }
}
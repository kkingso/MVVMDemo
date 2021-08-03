package com.kkw.mymvvm.ui.group

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kkw.mymvvm.R
import com.kkw.mymvvm.logic.network.appObserve
import com.kkw.mymvvm.ui.BaseActivity
import com.kkw.mymvvm.ui.main.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroupListActivity : BaseActivity() {

    private val groupListAdapter: GroupListAdapter by lazy { GroupListAdapter() }
    private val coroutineScopeMain: CoroutineScope by lazy { CoroutineScope(Dispatchers.Main) }
    private val model: MainViewModel by viewModels()

    private var toolbar: Toolbar? = null
    private var recycler: RecyclerView? = null
    private var refresh: SwipeRefreshLayout? = null

    private val pageNum = 20
    private var index = 0

    companion object {
        @JvmStatic
        fun startActivity(context: Context?) {
            context?.let {
                it.startActivity(Intent(it, GroupListActivity::class.java))
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_group_list
    }

    override fun initView() {
        toolbar = findViewById(R.id.tool_bar)
        recycler = findViewById(R.id.recyclerView)
        refresh = findViewById(R.id.refreshLayout)

        setSupportActionBar(toolbar)

        recycler?.layoutManager = LinearLayoutManager(this)
        recycler?.adapter = groupListAdapter
        groupListAdapter.loadMoreModule.setOnLoadMoreListener {
            coroutineScopeMain.launch {
                loadData(true)
            }
        }
        refresh?.setOnRefreshListener {
            coroutineScopeMain.launch {
                loadData(false)
            }
        }
    }

    override fun initData() {
        super.initData()
        coroutineScopeMain.launch {
            loadData(false)
        }
    }

    private suspend fun loadData(isLoadMore: Boolean) {
        if (!isLoadMore) {
            index = 0
        }
        model.getGroupInfo("54AF9BC6F1B746EA9R798AUS", index)?.appObserve(this, {
            Log.e("kkww", "getGroupInfo: ${it.groups}")
            it.groups?.let { list ->
                if (list.size < pageNum) {
                    if (isLoadMore) {
                        groupListAdapter.addData(list)
                        groupListAdapter.loadMoreModule.loadMoreEnd()
                    } else {
                        groupListAdapter.setList(list)
                        groupListAdapter.loadMoreModule.loadMoreEnd()
                    }
                } else {
                    if (isLoadMore) {
                        groupListAdapter.addData(list)
                        groupListAdapter.loadMoreModule.loadMoreComplete()
                    } else {
                        groupListAdapter.setList(list)
                        groupListAdapter.loadMoreModule.loadMoreComplete()
                    }
                    index += list.size
                }
            }
            refresh?.isRefreshing = false
        })
    }
}
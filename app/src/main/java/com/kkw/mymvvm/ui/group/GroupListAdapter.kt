package com.kkw.mymvvm.ui.group

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder

import com.kkw.mymvvm.MyApplication
import com.kkw.mymvvm.R
import com.kkw.mymvvm.logic.model.GroupListItemEntity

class GroupListAdapter : BaseQuickAdapter<GroupListItemEntity, BaseViewHolder>(R.layout.item_group_list),LoadMoreModule {

    override fun convert(holder: BaseViewHolder, item: GroupListItemEntity) {
        val tvName = holder.getView<TextView>(R.id.tvName)
        val avatar = holder.getView<ImageView>(R.id.avatar)
        val tvCount = holder.getView<TextView>(R.id.tvCount)
        val tvPayLogo = holder.getView<TextView>(R.id.tvPayLogo)
        val tvCreateLogo = holder.getView<TextView>(R.id.tvCreateLogo)
        val btnJoin = holder.getView<TextView>(R.id.tvJoin)

        item.let {
            tvPayLogo.visibility = if (it.fee == 0) View.GONE else View.VISIBLE
            tvCreateLogo.visibility = View.VISIBLE
            btnJoin.visibility = View.VISIBLE
            when (it.status) {
                0 -> {
                    btnJoin.text = "加入"
                    btnJoin.setTextColor(Color.parseColor("#0077ff"))
                }
                1 -> {
                    btnJoin.text = "已申请"
                    btnJoin.setTextColor(Color.parseColor("#999999"))
                }
                2 -> {
                    btnJoin.text = "已加入"
                    btnJoin.setTextColor(Color.parseColor("#999999"))
                }
                else -> {
                    btnJoin.visibility = View.GONE
                }
            }
            tvCount.text = "${it.volume}人"
            tvName.text = it.groupName
            Glide.with(holder.itemView.context).load("https://img.zhaodao88.com/yemai/vip/camfs/qxb/${it.groupAvatar}").into(avatar)
        }

    }
}
package com.kkw.mymvvm.logic.model

import com.google.gson.annotations.SerializedName

data class GroupListEntity(
    @SerializedName("group_list")
    val groups: MutableList<GroupListItemEntity>?=null
)

data class GroupListItemEntity(
    @SerializedName("group_id")
    val groupId: String? = null,
    @SerializedName("group_name")
    val groupName: String? = null,
    @SerializedName("group_avatar")
    val groupAvatar: String? = null,
    @SerializedName("fee") // 是否付费群 0非付费，1付费
    val fee: Int = 0,
    @SerializedName("owner_accid")
    val ownerAccid: String? = null,

    @SerializedName("status")// 0没有申请过，1申请过，2已经在群中
    var status: Int = 0,

    @SerializedName("volume") //群成员数量
    var volume: Int = 0,

    @SerializedName("redeem_code_flag")
    var redeemCodeFlag: Int = 0   //是否使用兑换码标志 0  不使用 1 使用
)
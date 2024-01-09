package com.example.madcamp_week2.serverInterface.classComponents

data class assetsgroupInformation(
    val member_id: String,
    val assetsgroup_id: String,
    val assetsgroupname: String,
    val assetsgroupgoal: String,
    val categories: List<String>,
    val targetasset: Int,
    val currentasset: Int
)

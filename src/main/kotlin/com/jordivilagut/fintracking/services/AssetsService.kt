package com.jordivilagut.fintracking.services

import com.jordivilagut.fintracking.base.BaseFilter
import com.jordivilagut.fintracking.model.Asset

interface AssetsService {

    fun get(id: String): Asset?

    fun findAll(): List<Asset>

    fun findByUserId(userId: String): List<Asset>

    fun findByFilter(filter: Filter): List<Asset>

    fun addAsset(asset: Asset): Asset

    fun updateAsset(id: String, asset: Asset): Asset

    fun deleteAsset(id: String)

    class Filter : BaseFilter() {

        var userId: String? = null

        companion object {
            /** Convenience method to build [Filter] */
            fun assetsFilter(f: Filter.() -> Unit) = Filter().apply(f)
        }
    }
}
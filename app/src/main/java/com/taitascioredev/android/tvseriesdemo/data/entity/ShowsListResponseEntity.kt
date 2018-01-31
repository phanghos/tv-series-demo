package com.taitascioredev.android.tvseriesdemo.data.entity

import com.google.gson.annotations.SerializedName

data class ShowsListResponseEntity(

	@field:SerializedName("page")
	var page: Int? = null,

	@field:SerializedName("total_pages")
	var totalPages: Int? = null,

	@field:SerializedName("results")
	var results: List<ResultsItem?>? = null,

	@field:SerializedName("total_results")
	var totalResults: Int? = null
)
package com.taitascioredev.android.tvseriesdemo.feature.tvshowdetails.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.drawee.view.SimpleDraweeView
import com.jakewharton.rxbinding2.view.RxView
import com.taitascioredev.android.tvseriesdemo.R
import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by rrtatasciore on 26/01/18.
 */
class SimilarShowAdapter : RecyclerView.Adapter<SimilarShowAdapter.SimilarShowViewHolder>() {

    private val shows: List<MovieDbTvShow>

    private val clickSubject = PublishSubject.create<MovieDbTvShow>()

    companion object {
        private val IMG_BASE_URL = "http://image.tmdb.org/t/p/w342"
    }

    init {
        shows = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SimilarShowViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.similar_show_row_layout, parent, false)
        return SimilarShowViewHolder(v)
    }

    override fun getItemCount(): Int {
        return shows.size
    }

    override fun onBindViewHolder(holder: SimilarShowViewHolder?, position: Int) {
        val show = shows[position]
        holder?.showImg?.setImageURI(IMG_BASE_URL + show.posterPath)
    }

    fun add(newList: List<MovieDbTvShow>) {
        Observable.fromIterable((ArrayList<MovieDbTvShow>(newList)))
                .filter { !shows.contains(it) }
                .subscribe {
                    (shows as ArrayList).add(it)
                    notifyItemInserted(itemCount)
                }
    }

    fun getClickObservable(): Observable<MovieDbTvShow> = clickSubject

    inner class SimilarShowViewHolder(v : View) : RecyclerView.ViewHolder(v) {

        val showImg: SimpleDraweeView = v.findViewById(R.id.drawee_view)

        init {
            RxView.clicks(v).subscribe { clickSubject.onNext(shows[adapterPosition]) }
        }
    }
}
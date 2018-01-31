package com.taitascioredev.android.tvseriesdemo.feature.tvshowslist.view.adapter

import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.jakewharton.rxbinding2.view.RxView
import com.taitascioredev.android.tvseriesdemo.R
import com.taitascioredev.android.tvseriesdemo.domain.model.MovieDbTvShow
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by rrtatasciore on 25/01/18.
 */
class MovieDbShowAdapter : RecyclerView.Adapter<MovieDbShowAdapter.ShowViewHolder>() {

    companion object {
        private val IMG_BASE_URL = "http://image.tmdb.org/t/p/w342"
    }

    private val shows: List<MovieDbTvShow>

    private val clickSubject = PublishSubject.create<MovieDbTvShow>()

    init {
        shows = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShowViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.show_row_layout, parent, false)
        return ShowViewHolder(v)
    }

    override fun getItemCount(): Int {
        return shows.size
    }

    override fun onBindViewHolder(holder: ShowViewHolder?, position: Int) {
        val show = shows[position]
        holder?.let {
            with (holder) {
                showPosterImg.setImageURI(IMG_BASE_URL + show.posterPath)
                showName.text = show.name
                showVoteAvg.text = "Vote avg: ${show.voteAverage.toString()}"
                showVoteCount.text = "Vote count: ${show.voteCount.toString()}"
                setSpans(showVoteAvg)
                setSpans(showVoteCount)
            }
        }
    }

    fun add(newList: List<MovieDbTvShow>) {
        Observable.fromIterable(ArrayList(newList))
                .filter { !shows.contains(it) }
                .subscribe {
                    (shows as ArrayList).add(it)
                    notifyItemInserted(itemCount)
                }
    }

    fun clear() {
        (shows as ArrayList).clear()
        notifyDataSetChanged()
    }

    fun getClickObservable(): Observable<MovieDbTvShow> = clickSubject

    private fun setSpans(textView: TextView) {
        val text = textView.text.toString()
        val ssb = SpannableStringBuilder(text)
        val lastSpace = text.lastIndexOf(" ")
        val styleSpan = StyleSpan(Typeface.BOLD)
        ssb.setSpan(styleSpan, lastSpace + 1, text.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        textView.text = ssb
    }

    inner class ShowViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val showPosterImg: SimpleDraweeView = v.findViewById(R.id.drawee_view)

        val showName: TextView = v.findViewById(R.id.tv_show_name)

        val showVoteAvg: TextView = v.findViewById(R.id.tv_show_vote_avg)

        val showVoteCount: TextView = v.findViewById(R.id.tv_show_vote_count)

        init {
            RxView.clicks(v).subscribe { clickSubject.onNext(shows[adapterPosition]) }
        }
    }
}
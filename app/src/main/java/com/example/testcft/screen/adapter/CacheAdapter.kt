package com.example.testcft.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testcft.data.cache.BankCardInfoEntity
import com.example.testcft.databinding.CacheItemBinding
import com.example.testcft.util.Const.DASH_TEXT

class CacheAdapter: ListAdapter<BankCardInfoEntity, CacheAdapter.CacheViewHolder>(DiffUtilCallbackCache) {

    private var onItemClick: OnItemClick? = null

    fun setOnClick(onItemClick: OnItemClick){
        this.onItemClick = onItemClick
    }

    inner class CacheViewHolder(private val binding: CacheItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(cardDetail: BankCardInfoEntity){
            with(binding){
                requestNumber.text = cardDetail.number
                schema.text = cardDetail.scheme
                currencyText.text = cardDetail.currency ?: DASH_TEXT
                countryText.text = cardDetail.countryName ?: DASH_TEXT
                countryEmoji.text = cardDetail.countryEmoji
                timestamp.text = cardDetail.timestamp

                root.setOnClickListener { onItemClick?.onItemClick(cardDetail) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CacheViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CacheItemBinding.inflate(inflater, parent, false)

        return CacheViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CacheViewHolder, position: Int) {
        val bankCardInfo = getItem(position)

        holder.bind(bankCardInfo)
    }

    interface OnItemClick{
        fun onItemClick(cardDetail: BankCardInfoEntity)
    }

    companion object DiffUtilCallbackCache: DiffUtil.ItemCallback<BankCardInfoEntity>() {
        override fun areItemsTheSame(oldItem: BankCardInfoEntity, newItem: BankCardInfoEntity): Boolean {
            return oldItem.nameBank == newItem.nameBank
        }

        override fun areContentsTheSame(oldItem: BankCardInfoEntity, newItem: BankCardInfoEntity): Boolean {
            return oldItem == newItem
        }
    }


}
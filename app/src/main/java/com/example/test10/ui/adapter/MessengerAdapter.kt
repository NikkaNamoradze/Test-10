package com.example.test10.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.test10.R
import com.example.test10.common.enums.MessageType
import com.example.test10.common.extension.setImage
import com.example.test10.databinding.LayoutItemBinding
import com.example.test10.ui.model.ItemModelUI
import java.text.SimpleDateFormat
import java.util.*


class MessengerAdapter : RecyclerView.Adapter<MessengerAdapter.ItemsViewHolder>() {

    private var adapterList = listOf<ItemModelUI>()

    inner class ItemsViewHolder(private val binding: LayoutItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind() {
            val currentItem = adapterList[adapterPosition]

            binding.apply {
                if (currentItem.updatedDate != null) {
                    tvTime.text = getDate(currentItem.updatedDate, "hh:mm a")
                } else {
                    tvTime.text = "00:00 AM"
                }
                ivImage.setImage(currentItem.avatar)
                tvTitle.text = "${currentItem.firstName} ${currentItem.lastName}"
                if (currentItem.isTyping) {
                    tvUnreadMessages.visibility = View.GONE
                    isTyping.visibility = View.VISIBLE
                } else {
                    isTyping.visibility = View.GONE
                }
                if (currentItem.unreaMessage > 0) {
                    tvUnreadMessages.visibility = View.VISIBLE
                    isTyping.visibility = View.GONE
                    tvUnreadMessages.text = currentItem.unreaMessage.toString()
                }
                when (currentItem.messageType) {
                    MessageType.text.name -> {
                        attachOrVoice.visibility = View.GONE
                        tvSubTitle.text = currentItem.lastMessage
                        tvSubTitle.setTextColor(
                            ContextCompat.getColor(tvSubTitle.context, R.color.grey)
                        )
                    }
                    MessageType.attachment.name -> {
                        attachOrVoice.visibility = View.VISIBLE
                        attachOrVoice.setBackgroundResource(R.drawable.attachment)
                        tvSubTitle.text = "Sent an attachment"
                    }
                    MessageType.voice.name -> {
                        attachOrVoice.visibility = View.VISIBLE
                        attachOrVoice.setBackgroundResource(R.drawable.voice)
                        tvSubTitle.text = "Sent a voice message"
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(
            LayoutItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = adapterList.size

    fun setData(data: List<ItemModelUI>) {
        val diffUtil = DiffUtils(adapterList, data)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        adapterList = data
        diffResult.dispatchUpdatesTo(this)
    }


    class DiffUtils(
        private val newList: List<ItemModelUI>,
        private val oldList: List<ItemModelUI>
    ) : DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]
    }

    fun getDate(milliSeconds: Long, dateFormat: String?): String? {
        val formatter = SimpleDateFormat(dateFormat)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }
}
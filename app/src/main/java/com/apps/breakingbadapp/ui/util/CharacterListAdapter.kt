package com.apps.fullandroidcourseclassa.breakingbadapi.ui.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.apps.breakingbadapp.R
import com.apps.fullandroidcourseclassa.R
import com.apps.fullandroidcourseclassa.adapters.ShoppingItemAdapter
import com.apps.fullandroidcourseclassa.adapters.TodoAdapter
import com.apps.fullandroidcourseclassa.breakingbadapi.model.BBCharacter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.character_item.view.*

class CharacterListAdapter(
    private val clickCallBack: ((BBCharacter) -> Unit)?
) : androidx.recyclerview.widget.ListAdapter<BBCharacter, CharacterListAdapter.CharacterViewHolder>(CharacterComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.create(parent)
    }
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {

       val character = getItem(position)
        holder.bind(character)
        holder.itemView.setOnClickListener { clickCallBack?.invoke(character) }
    }

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvNickName: TextView = itemView.findViewById(R.id.tvNickName)
        private val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
        private val tvBirthday: TextView = itemView.findViewById(R.id.tvBirthday)
        private val tvOccupation: TextView = itemView.findViewById(R.id.tvOccupation)
        private val ivCharacterImage: ImageView = itemView.findViewById(R.id.ivCharacterImage)

        fun bind(character: BBCharacter) {
            tvName.text = character.name
            tvNickName.text = character.nickname
            tvStatus.text = character.status
            tvBirthday.text = character.birthday
            tvOccupation.text = character.occupation.joinToString { "," }

            if (character.img != null) {
                Glide.with(itemView).load(character.img).centerCrop().into(ivCharacterImage)
            }
        }

        companion object {
            fun create(parent: ViewGroup): CharacterViewHolder {
                val view: View =
                    LayoutInflater.from(parent.context).inflate(R.layout.character_item,parent, false)
                return CharacterViewHolder(view)
            }
        }
    }
}

class CharacterComparator : DiffUtil.ItemCallback<BBCharacter>() {
    override fun areItemsTheSame(oldItem: BBCharacter, newItem: BBCharacter): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BBCharacter, newItem: BBCharacter): Boolean {
        return oldItem.name == newItem.name
                && oldItem.nickname == newItem.nickname
                && oldItem.status == newItem.status
                && oldItem.img == newItem.img
                && oldItem.birthday == newItem.birthday
                && oldItem.occupation == newItem.occupation
    }

}
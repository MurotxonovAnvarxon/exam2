package uz.gita.imtihonexample2.presentation.adapter

import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import uz.gita.imtihonexample2.R
import uz.gita.imtihonexample2.data.room.ContactEntity
import uz.gita.imtihonexample2.databinding.ItemUserBinding
import java.net.URL

class MyAdapter : ListAdapter<ContactEntity, MyAdapter.MyViewHolder>(MyDiff) {
    private var clicklistener: ((ContactEntity) -> Unit)? = null

    object MyDiff : DiffUtil.ItemCallback<ContactEntity>() {
        override fun areItemsTheSame(oldItem: ContactEntity, newItem: ContactEntity): Boolean = true
        override fun areContentsTheSame(oldItem: ContactEntity, newItem: ContactEntity): Boolean =
            true
    }

    fun setClicklistener(block: (ContactEntity) -> Unit) {
        clicklistener = block
    }

    inner class MyViewHolder(private val binding: ItemUserBinding) : ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                clicklistener?.invoke(getItem(adapterPosition))
            }
        }

        fun bind(position: Int) {
            binding.title.text = getItem(position).title
            binding.firstName.text = getItem(position).firstName
            binding.lastName.text = getItem(position).lastName
            binding.email.text = getItem(position).email
            binding.age.text = getItem(position).age.toString()
            binding.phone.text = getItem(position).phone
            binding.buttonblack.setImageResource(if (getItem(adapterPosition).gender == "male") R.drawable.male else R.drawable.female)
            val url = URL(getItem(position).url)
            Glide.with(binding.root).load(url).into(binding.imageUser)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position)
    }


}
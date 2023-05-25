package ddwu.com.mobile.adaptereventtest

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ddwu.com.mobile.adaptereventtest.databinding.ListItemBinding

class FoodAdapter(val foods : ArrayList<FoodDto>) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() { // FoodAdapter 가 가지고 있는 FoodViewHolder 를 사용한다

//    interface OnItemClickListener{
//        fun onItemClick(view : View, position : Int)
//    }
//
//    lateinit var listener : OnItemClickListener
//    fun setOnItemClickListener(listener : OnItemClickListener){
//        this.listener = listener
//    }

      interface OnItemLongClickListener{
          fun onItemLongClick(view : View, position : Int)
      }

      lateinit var listener : OnItemLongClickListener
      fun setOnItemLongClickListener(listener: OnItemLongClickListener){
          this.listener = listener
      }

    override fun getItemCount() = foods.size // 사이즈 반환 간단하게 하였음


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder { // 화면에 보여지는 칸 하나를 만듬. 화면에 출력되는 개수만 호출된다
//       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_view,parent,false)
         val itemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return FoodViewHolder(itemBinding,listener)
    }


    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) { // bind 하는 것이다
        holder.itemBinding.ivPhoto.setImageResource(foods[position].photo)
        holder.itemBinding.tvFood.text = foods[position].food
        holder.itemBinding.tvCount.text = foods[position].count.toString()
    }

    class FoodViewHolder(val itemBinding : ListItemBinding, val  listener : FoodAdapter.OnItemLongClickListener) : RecyclerView.ViewHolder (itemBinding.root){
//        init{
//            itemBinding.root.setOnClickListener{
//                listener.onItemClick(it,adapterPosition)
//            }

        init{
            itemBinding.root.setOnLongClickListener{
                listener.onItemLongClick(it,adapterPosition)
                true
            }
        }

    }
}
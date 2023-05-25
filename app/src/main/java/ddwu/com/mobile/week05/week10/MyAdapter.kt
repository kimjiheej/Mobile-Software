package ddwu.com.mobile.week05.week10

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val context : Context, val  layout: Int, val dataList : ArrayList<String>): RecyclerView.Adapter<MyAdapter.MyViewHolder> () {

    val TAG = "MyAdapter"
    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.d(TAG,"create ViewHolder!")
        val view = LayoutInflater.from(context).inflate(layout,parent,false)

        return MyViewHolder(view,dataList,this)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) { // 연결해서 원본 데이터와 화면의 textview 에서 보여주게 된다
        Log.d(TAG,"bind ViewHolder!")
        holder.tvText.setText(dataList[position])
        //holder.tvText.text = dataList[poisiton] 원본 데이터를 몇번째 보여주어야 하는지를 나타낸다
    }

  inner  class MyViewHolder(view: View, dataList : ArrayList<String>,adapter: MyAdapter) : RecyclerView.ViewHolder(view){

        val tvText : TextView = view.findViewById(R.id.tvText)

        init {
            view.setOnClickListener{
                dataList.removeAt(adapterPosition)
                adapter.notifyDataSetChanged()
            }

        }
    }
}
package ddwu.com.mobile.adaptereventtest

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import ddwu.com.mobile.adaptereventtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val foods = FoodDao().foods
//        val dao = SubjectDao()
//        val dataList = dao.dataList

        val adapter = FoodAdapter(foods)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL    // 생략 가능
        binding.recyclerView.layoutManager = layoutManager

        //  val adapter = MyAdapter(this, R.layout.list_view, dataList)
        binding.recyclerView.adapter = adapter

//        val itemClick = object : FoodAdapter.OnItemClickListener{
//            override fun onItemClick(view: View, position: Int) {
//                Toast.makeText(this@MainActivity,"${foods[position]}",
//                Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        adapter.setOnItemClickListener(itemClick)

         val itemClick = object : FoodAdapter.OnItemLongClickListener{
             override fun onItemLongClick(view: View, position: Int) {

                 AlertDialog.Builder(this@MainActivity).run{
                     setTitle("test dialog")
                     setMessage("정말 종료하시겠습니까?")
                     setPositiveButton("OK", object : DialogInterface.OnClickListener{
                         override fun onClick(p0: DialogInterface?, p1: Int) {
                             foods.removeAt(position)
                             adapter.notifyDataSetChanged()
                         }
                     })
                     setNegativeButton("Cancel",null)
                     show()
                 }
             }
         }
        adapter.setOnItemLongClickListener(itemClick)
    }
}
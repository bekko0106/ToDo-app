package developer.behzod.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import developer.behzod.todo.catch.MySharedPrefarance
import developer.bekzod.todoapp.Adapters.SpinerAdapter1
import developer.bekzod.todoapp.models.TodoPlan
import developer.bekzod.todoapp.models.User
import kotlinx.android.synthetic.main.activity_add_todo.*

class AddToDo : AppCompatActivity() {
    lateinit var userArray:ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)
        loadData()

        MySharedPrefarance.init(this)

        val spinerAdapter = SpinerAdapter1(userArray)
        spiner_add_to_do.adapter = spinerAdapter

        btn_save_add_to_do.setOnClickListener {
            val toDoName = edt_to_do_name.text.toString().trim()
            val toDoDescription = edt_to_do_description.text.toString().trim()
            val toDoCreateData = edt_to_do_create_data.text.toString().trim()
            val toDoDedline = edt_dedline.text.toString().trim()

            val degree = userArray[spiner_add_to_do.selectedItemPosition]

            if (toDoName!="" && toDoCreateData!="" && toDoDedline!="" && toDoDescription!=""){
                val myList = MySharedPrefarance.obektString
                myList.add(TodoPlan(toDoName, toDoDescription, degree, toDoCreateData, toDoDedline, "Open"))
                println(myList)
                MySharedPrefarance.obektString= myList
                Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this, "Error because editText is empty", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun loadData() {
        userArray = ArrayList()
        userArray.add(User(-1, "To do degree"))
        userArray.add(User(R.drawable.red_flag , "Urgent"))
        userArray.add(User(R.drawable.high_flag, "High"))
        userArray.add(User(R.drawable.normal_flag, "Normal"))
        userArray.add(User(R.drawable.low_flag, "Low"))
    }
}
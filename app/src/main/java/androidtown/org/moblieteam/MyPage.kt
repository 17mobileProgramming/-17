package androidtown.org.moblieteam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidtown.org.moblieteam.databinding.ActivityMyPageBinding
import androidtown.org.moblieteam.utils.FBAuth
import androidtown.org.moblieteam.utils.FBRef
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class MyPage : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var binding: ActivityMyPageBinding

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        database = Firebase.database.reference



        binding = DataBindingUtil.setContentView(this,R.layout.activity_my_page)

        /*
        val databaseUse = Firebase.database

        val myRef = databaseUse.getReference("user")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                try {
                    val dataModel = dataSnapshot.getValue(UserModel::class.java)

                    binding.nickname.text = dataModel?.nickname
                    binding.introduce.text = dataModel?.introduce

                } catch (e : Exception)
                {

                }


            }

            override fun onCancelled(databaseError: DatabaseError) {

            }

        }

        myRef.addValueEventListener(postListener)

        이게 원래 되야 하는 코드인데 아예 안됨 (ppt엔 이걸 넣어서 보여주기)

        */

        binding.nickname.setText(intent.getStringExtra("nick").toString())
        binding.introduce.text = intent.getStringExtra("intro").toString()

        Log.e("MyPage", intent.getStringExtra("nick").toString())

        }

    }
package androidtown.org.moblieteam.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidtown.org.moblieteam.R
import androidtown.org.moblieteam.databinding.ActivityBoardEditBinding
import androidtown.org.moblieteam.utils.FBAuth
import androidtown.org.moblieteam.utils.FBRef
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.lang.Exception

class BoardEditActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBoardEditBinding

    private lateinit var key : String

    private lateinit var writerUid : String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_edit)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_edit);

        key = intent.getStringExtra("key").toString()

        getBoardData(key)
        getImageData(key)
        binding.editBtn.setOnClickListener {
            editBoardData(key)
        }
    }

    private fun editBoardData(key : String){
        FBRef.boardRef
            .child(key)
            .setValue(BoardModel(binding.titleArea.text.toString(),
                binding.contentArea.text.toString(),
                writerUid,
                FBAuth.getTime())
            )

        Toast.makeText(this, "fix success", Toast.LENGTH_LONG).show()

        finish()
    }

    private fun getImageData(key : String){
        // Reference to an image file in Cloud Storage
        val storageReference = Firebase.storage.reference.child(key + ".png")

        // ImageView in your Activity
        val imageViewFromFB = binding.imageArea

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->
            if(task.isSuccessful)
            {
                Glide.with(this)
                    .load(task.result)
                    .into(imageViewFromFB)
            }
            else{

            }
        })
    }

    private fun getBoardData(key : String) {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val dataModel = dataSnapshot.getValue(BoardModel::class.java)

                binding.titleArea.setText(dataModel?.title)
                binding.contentArea.setText(dataModel?.content)
                writerUid = dataModel!!.uid
            }


            override fun onCancelled(databaseError: DatabaseError) {

            }

        }

        FBRef.boardRef.child(key).addValueEventListener(postListener)
    }
}
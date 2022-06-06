package androidtown.org.moblieteam.utils

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FBRef {

    companion object {
        private val database = Firebase.database

        val category1 = database.getReference("contents1")
        val category2 = database.getReference("contents2")
        val category3 = database.getReference("contents3")
        val category4 = database.getReference("contents4")

        val userRef = database.getReference("user")

        val bookmarkRef = database.getReference("bookmark_list")

        val boardRef = database.getReference("board")

        val commentRef = database.getReference("comment")
    }
}
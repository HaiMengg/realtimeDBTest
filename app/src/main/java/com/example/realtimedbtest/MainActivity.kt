package com.example.realtimedbtest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*

@IgnoreExtraProperties
data class User(val usr: String? = null, val pwd: String? = null) {
	// Null default values create a no-argument default constructor, which is needed
	// for deserialization from a DataSnapshot.

	@Exclude
	fun toMap(): Map<String, Any?> {
		return mapOf(
			"usr" to usr,
			"pwd" to pwd
		)
	}
}

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		FirebaseApp.initializeApp(this)
		FirebaseDatabase.getInstance("https://presentationtest-8832a-default-rtdb.asia-southeast1.firebasedatabase.app/")

		val database = Firebase.database.reference

//		// Read sth from DB
//		database.child("testVar").get().addOnSuccessListener {
//			Log.i("firebase", "Got value ${it.value}")
//		}.addOnFailureListener {
//			Log.e("firebase", "Error getting data", it)
//		}
//
//		// Enable realtime update
//		val postListener = object: ValueEventListener {
//			override fun onDataChange(dataSnapshot: DataSnapshot) {
//				val post = dataSnapshot.value
//				Log.i("TEST", post.toString())
//			}
//
//			override fun onCancelled(databaseError: DatabaseError) {
//				Log.w("TEST", "loadPost:onCancelled", databaseError.toException())
//			}
//		}
//		database.addValueEventListener(postListener)
//
//		// Add new data
//		val user = User("usr", "123")
//		database.child("testUser").setValue(user)
//
//		// Update data
//		val usrUpdate = User("usr", "123456")
//		val childUpdate = hashMapOf<String, Any>(
//			"/testUser" to usrUpdate.toMap()
//		)
//		database.updateChildren(childUpdate)
//
//		// Delete data 1
//		database.child("testUser").removeValue()
//
//		//Delete data 2
//		database.child("testUser").setValue(null)

		database.ref.orderByValue().equalTo("Đây là biến mới tạo").addValueEventListener(object: ValueEventListener {
			override fun onDataChange(snapshot: DataSnapshot) {
				snapshot.children.forEach {
					Log.i("firebase", it.key.toString())
				}
			}

			override fun onCancelled(error: DatabaseError) {

			}

		})
	}
}
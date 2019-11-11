package com.kilani.nowornever.data

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.kilani.nowornever.data.model.User

class UserRepository {
    private val dbCollection = FirebaseFirestore.getInstance().collection("users")

    private fun addUserToFirebase(user: User, onSuccess: () -> Unit, onFailure: (e: Exception) -> Unit) {
        dbCollection.document(user.id)
            .set(user)
            .addOnSuccessListener { onSuccess()}
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    fun getUser(user: FirebaseUser, onSuccess: (user: User) -> Unit) {
        dbCollection.document(user.uid)
            .get()
            .addOnSuccessListener { document ->
                if (document.data != null) {
                    onSuccess(document.toObject(User::class.java)!!)
                } else {
                    val newUser = user.toModel(0, listOf())
                    addUserToFirebase(newUser,
                        onSuccess = { Log.d("UserRepository", "User added to database")},
                        onFailure = { e -> Log.e("UserRepositoryError", "Error adding user" ,e)})
                    onSuccess(newUser)
                }
            }
            .addOnFailureListener { exception ->
                Log.w("UserRepository", "Error getting user: ", exception) }
    }
}
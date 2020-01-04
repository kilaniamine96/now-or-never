package com.kilani.nowornever.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.kilani.nowornever.data.model.User
import com.kilani.nowornever.data.toModel

class UserRepository {
    private val dbCollection = FirebaseFirestore.getInstance().collection("users")

    private fun addUserToFirebase(user: User, onSuccess: () -> Unit, onFailure: (e: Exception) -> Unit) {
        dbCollection.document(user.name!!)
            .set(user)
            .addOnSuccessListener { onSuccess()}
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    fun getUser(user: FirebaseUser, onSuccess: (user: User) -> Unit) {
        dbCollection.document(user.displayName!!)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
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

    fun listenForUserUpdates(user: FirebaseUser, onSuccess: (userUpdated: User) -> Unit) {
        dbCollection.document(user.displayName!!)
            .addSnapshotListener { snapshot, e ->
                if (e != null) Log.w("UserRepository", "Error listening for updates: ", e)
                if (snapshot!!.exists()) onSuccess(snapshot.toObject(User::class.java)!!)
            }
    }
}
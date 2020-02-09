package com.kilani.nowornever.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.kilani.nowornever.data.database.daos.UsersDao
import com.kilani.nowornever.data.enums.Level
import com.kilani.nowornever.data.model.User
import com.kilani.nowornever.data.toEntity
import com.kilani.nowornever.data.toModel
import com.kilani.nowornever.utils.Coroutines

class UserRepository constructor(private val usersDao: UsersDao){
    private val dbCollection = FirebaseFirestore.getInstance().collection("users")
    private lateinit var activityListener : ListenerRegistration

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
                    val newUser = user.toModel(0, mutableListOf())
                    addUserToFirebase(newUser,
                        onSuccess = { Log.d("UserRepository", "User added to database")},
                        onFailure = { e -> Log.e("UserRepositoryError", "Error adding user" ,e)})
                    onSuccess(newUser)
                }
            }
            .addOnFailureListener { exception ->
                Log.w("UserRepository", "Error getting user: ", exception) }
    }

    fun getAllUsers(onSuccess: (userList: MutableList<User>) -> Unit) {

        dbCollection.get()
            .addOnSuccessListener { userList ->
                val userListToSave = mutableListOf<User>()
                for (user in userList) {
                    userListToSave.add(user.toObject(User::class.java))
                }
                Coroutines.io{
                    usersDao.insertList(userListToSave.map { user -> user.toEntity() })
                }
                onSuccess(userListToSave)
            }
    }

    fun listenForUserUpdates(user: FirebaseUser, onSuccess: (userUpdated: User) -> Unit) {
        activityListener = dbCollection.document(user.displayName!!)
            .addSnapshotListener { snapshot, e ->
                if (e != null) Log.w("UserRepository", "Error listening for updates: ", e)
                if (snapshot != null) {
                    if (snapshot.exists()) onSuccess(snapshot.toObject(User::class.java)!!)
                }
            }
    }

    fun stopListeningForUpdates() = activityListener.remove()


    fun updateUserScoreAndLevel(newLevel: Level, newScore: Int, user: FirebaseUser, onSuccess: () -> Unit) {
        dbCollection.document(user.displayName!!)
            .update("score", newScore)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener{ e ->
                Log.w("UserRepository", "Could not update User score", e)
            }
        dbCollection.document(user.displayName!!)
            .update("level", newLevel)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener{ e ->
                Log.w("UserRepository", "Could not update User level", e)
            }
    }
}
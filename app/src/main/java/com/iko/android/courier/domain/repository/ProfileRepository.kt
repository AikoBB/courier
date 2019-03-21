package com.iko.android.courier.domain.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.iko.android.courier.data.database.entity.Person
import com.iko.android.courier.data.database.entity.Review
import com.iko.android.courier.domain.Callback
import javax.inject.Inject

interface ProfileRepository {

    fun createUser(person: Person, callback: Callback<Person, String?>)
    fun getUserByEmail(email: String, callback: Callback<Person, String?>)
    fun createReview(review: Review, callback: Callback<Review, String?>)
    fun getReviewsByEmail(email: String, callback: Callback<List<Review>, String?>)

    class Network @Inject constructor(
        private val database: FirebaseDatabase
    ) : ProfileRepository {

        private val TAG_USERS = "users"
        private val TAG_REVIEWS = "reviews"

        override fun createUser(person: Person, callback: Callback<Person, String?>) {
            val usersReference = database.getReference(TAG_USERS)
            val userId = usersReference.push().key
            usersReference.child(userId!!).setValue(person)
        }

        override fun getUserByEmail(email: String, callback: Callback<Person, String?>) {
            val usersReference = database.getReference(TAG_USERS)
            usersReference.addListenerForSingleValueEvent(CustomValueEventListener(
                {data ->
                    data.children.forEach {
                        val user = it.getValue(Person::class.java)
                        if(user?.email == email ){
                            callback.onSuccess(user!!)
                            return@CustomValueEventListener
                        }
                    }

                },
                { callback.onFailed(it) }
            ))
        }

        override fun createReview(review: Review, callback: Callback<Review, String?>) {
            val reviewsReference = database.getReference(TAG_REVIEWS)
            val reviewId = reviewsReference.push().key
            reviewsReference.child(reviewId!!).setValue(review)
        }

        override fun getReviewsByEmail(email: String, callback: Callback<List<Review>, String?>) {
            val reviewsReference = database.getReference(TAG_REVIEWS)
            reviewsReference.addListenerForSingleValueEvent(CustomValueEventListener(
                { data ->
                    val reviews = mutableListOf<Review>()
                    data.children.forEach {
                        val review = it.getValue(Review::class.java)
                        if(review?.reviewedEmail == email)
                            reviews.add(it.getValue(Review::class.java)!!)
                    }
                    callback.onSuccess(reviews)
                },
                { callback.onFailed(it) }
            ))

        }

        inner class CustomValueEventListener(
            private val onSuccess: (dataSnapshot: DataSnapshot) -> Unit,
            private val onFail: (message: String) -> Unit
        ) : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                onFail(p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                onSuccess(p0)
            }
        }
    }
}
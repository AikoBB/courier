package com.iko.android.courier.domain.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.iko.android.courier.data.database.entity.Cargo
import com.iko.android.courier.data.database.entity.CargoList
import com.iko.android.courier.data.database.entity.Person
import com.iko.android.courier.data.prefs.AppPreferences
import com.iko.android.courier.domain.Callback
import javax.inject.Inject

interface CargoRepository {

    fun createCargo(cargo: Cargo, callback: Callback<Cargo, String?>)
    fun getCargoById(cargoId: String, callback: Callback<Cargo, String?>)
    fun updateCargo(cargo: Cargo, callback: Callback<Cargo, String?>)
    fun deleteCargo(cargoId: String, callback: Callback<Any?, String?>)
    fun getCargoList(callback: Callback<CargoList, String?>)

    class Network @Inject constructor(
        private val database: FirebaseDatabase,
        private val prefs: AppPreferences
    ) : CargoRepository {

        private val cargoReference = database.getReference("cargoes")
        private val cargoList = CargoList()


        override fun createCargo(cargo: Cargo, callback: Callback<Cargo, String?>) {
            val cargoId = cargoReference.push().key
            cargo.id = cargoId!!
            updateCargo(cargo, callback)
        }

        override fun updateCargo(cargo: Cargo, callback: Callback<Cargo, String?>) {
            cargoReference.child(cargo.id!!).setValue(cargo)
            callback.onSuccess(cargo)
        }

        override fun deleteCargo(cargoId: String, callback: Callback<Any?, String?>) {
            cargoReference.child(cargoId).removeValue()
            callback.onSuccess(null)
        }

        override fun getCargoById(cargoId: String, callback: Callback<Cargo, String?>) {
            cargoReference.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    callback.onFailed(p0.message)
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    dataSnapshot.children.forEach {
                        val cargo = it.getValue(Cargo::class.java)
                        if(cargo?.id == cargoId){
                            callback.onSuccess(cargo)
                            return
                        }
                    }
                }
            })
        }

        override fun getCargoList(callback: Callback<CargoList, String?>) {
            callback.onSuccess(cargoList)
            cargoReference.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    callback.onFailed(p0.message)
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val cargoes = mutableListOf<Cargo>()
                    dataSnapshot.children.forEach {
                        val cargo = it.getValue(Cargo::class.java)
                        cargoes.add(cargo!!)
                    }
                    cargoList.own = cargoes.filter { it.sender!!.email == prefs.email }
                    cargoList.delivers = cargoes.filter { it.assignee?.email == prefs.email }
                    cargoList.readyForDeliver = cargoes.filter {
                        it.assignee == null
                                && it.sender!!.email != prefs.email
                    }
                    callback.onSuccess(cargoList)
                }
            })
        }

        private fun isRequestAlreadySent(requests: MutableList<Person>) =
            requests.filter { it.email == prefs.email }.isNotEmpty()
    }

}
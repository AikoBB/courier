package com.iko.android.courier.ui.cargo.create

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.lifecycle.Observer
import com.iko.android.core.extension.showConfirmDialog
import com.iko.android.core.extension.showMessage
import com.iko.android.core.extension.showWarningDialog
import com.iko.android.courier.CourierApp
import com.iko.android.courier.R
import com.iko.android.courier.data.CargoEvent
import com.iko.android.courier.data.Event
import com.iko.android.courier.data.database.entity.*
import com.iko.android.modularapp.base.CoreActivity
import kotlinx.android.synthetic.main.activity_create_cargo.*
import org.parceler.Parcels
import java.util.*

class CreateCargoActivity : CoreActivity<CreateCargoVM>(CreateCargoVM::class.java, R.layout.activity_create_cargo) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar.initForActivity(this, getString(R.string.label_create_cargo))
        Parcels.unwrap<Person>(intent.getParcelableExtra(Person::class.java.canonicalName))
            ?.let { viewModel.currentUser = it }
        subscribeliveData()
        setupViews()
    }

    override fun performDependencyInjection() {
        (applicationContext as CourierApp).appComponent.inject(this)
    }

    private fun subscribeliveData(){
        viewModel.event.observe(this, Observer {
            when(it){
                is Event.Notification -> showMessage(it.message)
                is CargoEvent.CargoCreated -> {
                    showConfirmDialog(getString(R.string.warning_cargo_created, it.cargo.id), onOkListener = {finish()}, onCancelListener = {finish()})
                }
            }
        })
    }

    private fun setupViews() {
        create.setOnClickListener { if (isInputsValid()) viewModel.createCargo(createCargo()) }
    }

    private fun isInputsValid(): Boolean {
        return pick_up_address.isValidInput()
                && delivery_address.isValidInput()
                && delivery_date.isValidInput()
                && receiver_info.isValidInput()
                && validateNumberInput(weight)
                && validateNumberInput(price)

    }

    private fun validateNumberInput(input: EditText): Boolean {
        val isNotEmpty = input.text.isNotEmpty()
        try {
            input.text.toString().toDouble()
        } catch (ex: Exception) {
            showWarningDialog(getString(R.string.warning_incorrect_number, input.hint))
            return false
        }
        if (!isNotEmpty) showWarningDialog(getString(R.string.warning_can_not_be_empty, input.hint))
        return isNotEmpty
    }

    private fun createCargo(): Cargo {
        val cargo = Cargo()
        cargo.addressFrom = Address(name = pick_up_address.getText())
        cargo.addressTo = Address(name = delivery_address.getText())
        delivery_date.setToCargo(cargo)
        val receiver = Person()
        receiver_info.setToPerson(receiver)
        cargo.receiver = receiver
        cargo.sender = viewModel.currentUser
        cargo.note = delivery_note.getText()
        cargo.price = price.text.toString().toFloat()
        cargo.weight = weight.text.toString().toFloat()
        cargo.states.add(CargoStateInfo(state = CargoState.CREATED, date = Date()))
        return cargo
    }

    companion object {

        fun start(activity: Activity?, user: Person) {
            val intent = Intent(activity, CreateCargoActivity::class.java)
            intent.putExtra(Person::class.java.canonicalName, Parcels.wrap(user))
            activity?.startActivity(intent)
        }

    }

}
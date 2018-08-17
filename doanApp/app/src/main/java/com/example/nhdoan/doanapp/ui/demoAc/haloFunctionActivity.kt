package com.example.nhdoan.doanapp.ui.demoAc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

interface haloFunctionDisplayLogic {
//    fun displaySomething()
}

class haloFunctionActivity : AppCompatActivity(), haloFunctionDisplayLogic {

    var interactor: haloFunctionBusinessLogic? = null
    var router: haloFunctionRouter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_vip_example)

        configure()
    }

    fun configure() {

        val activity = this

        val interactor = haloFunctionInteractor()
        val router = haloFunctionRouter()
        val presenter = haloFunctionPresenter()

        activity.interactor = interactor
        activity.router = router

        interactor.presenter = presenter

        router.activity = activity
//        router.dataStore = interactor

        presenter.activity = activity
    }

}

package com.example.nhdoan.doanapp.ui.demoAc

interface haloFunctionBusinessLogic {
//    fun doSomething()
}

interface haloFunctionDataStore {

}

class haloFunctionInteractor : haloFunctionBusinessLogic, haloFunctionDataStore {

    var presenter: haloFunctionPresenter? = null

}

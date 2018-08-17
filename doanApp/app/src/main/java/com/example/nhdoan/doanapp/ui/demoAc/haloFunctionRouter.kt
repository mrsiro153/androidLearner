package com.example.nhdoan.doanapp.ui.demoAc

interface haloFunctionRoutingLogic {
//    fun routeToSomewhere()
}

interface haloFunctionDataPassing {

}

class haloFunctionRouter : haloFunctionRoutingLogic, haloFunctionDataPassing {

    var activity: haloFunctionActivity? = null

}

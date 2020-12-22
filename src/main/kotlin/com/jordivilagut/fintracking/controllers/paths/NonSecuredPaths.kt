package com.jordivilagut.fintracking.controllers.paths

import com.jordivilagut.fintracking.base.Path
import com.jordivilagut.fintracking.controllers.ApplicationController
import com.jordivilagut.fintracking.controllers.AuthenticationController

class NonSecuredPaths {
    companion object {
        val STATUS = Path(ApplicationController.STATUS)
        val AUTH = Path(AuthenticationController.PATH)
    }
}
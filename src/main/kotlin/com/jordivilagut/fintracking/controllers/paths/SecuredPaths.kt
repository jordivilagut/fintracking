package com.jordivilagut.fintracking.controllers.paths

import com.jordivilagut.fintracking.base.Path
import com.jordivilagut.fintracking.controllers.SecuredController
import com.jordivilagut.fintracking.controllers.TransactionsController

class SecuredPaths {
    companion object {
        val HELLO = Path(SecuredController.PATH)
        val TRANSACTIONS = Path(TransactionsController.PATH)
    }
}
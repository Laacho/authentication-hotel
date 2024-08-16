package com.tinqinacademy.authenticationHotel.api.models.operations.logout;

import com.tinqinacademy.authenticationHotel.api.models.baseOperations.OperationProcess;
import com.tinqinacademy.authenticationHotel.api.models.operations.loginUser.LoginInput;
import com.tinqinacademy.authenticationHotel.api.models.operations.loginUser.LoginOutput;

public interface LoginOperation extends OperationProcess<LoginInput, LoginOutput> {
}

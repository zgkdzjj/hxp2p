package com.hxweaving.hxp2p.api.account;

import com.hxweaving.hxp2p.api.account.model.AccountDTO;
import com.hxweaving.hxp2p.api.account.model.AccountLoginDTO;
import com.hxweaving.hxp2p.api.account.model.AccountRegisterDTO;
import com.hxweaving.hxp2p.common.domain.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "account", description = "The account API")
public interface AccountAPI {

    /**
     * Get SMS code from SMS service
     * @param mobile
     * @return
     */
    @Operation(summary = "Get a SMS code from SMS service", description = "Get a SMS code from SMS service powered by Aliyun")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully get a SMS code", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "405", description = "Invalid input")})
    @Parameter(name = "mobile", description = "Mobile Number", required = true)
    RestResponse getSMSCode(String mobile);

    RestResponse<Integer> validateSMSCode(String mobile, String key, String code);

    @Operation(summary = "Register a user", description = "Register a user")
    RestResponse<AccountDTO> register(AccountRegisterDTO accountRegisterDTO);

    @Operation(summary = "User login", description = "User login")
    RestResponse<AccountDTO> login(AccountLoginDTO accountLoginDTO);
}

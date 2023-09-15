package com.vickbt.darajakmp.network.models

import com.vickbt.darajakmp.utils.C2BResponseType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.native.ObjCName

@ObjCName(swiftName = "C2BRegistrationRequest")
@Serializable
/***/
data class C2BRegistrationRequest(
    /**This is the URL that receives the confirmation request from API upon payment completion.*/
    @SerialName("ConfirmationURL")
    val confirmationURL: String,

    /**This is the URL that receives the validation request from the API upon payment submission. The validation URL is only called if the external validation on the registered shortcode is enabled. (By default External Validation is disabled).*/
    @SerialName("ValidationURL")
    val validationURL: String,

    /**This parameter specifies what is to happen if for any reason the validation URL is not reachable. Note that, this is the default action value that determines what M-PESA will do in the scenario that your endpoint is unreachable or is unable to respond on time. Only two values are allowed: Completed or Cancelled. Completed means M-PESA will automatically complete your transaction, whereas Cancelled means M-PESA will automatically cancel the transaction, in the event M-PESA is unable to reach your Validation URL.*/
    @SerialName("ResponseType")
    val responseType: String? = C2BResponseType.COMPLETED.name,

    /**A unique number is tagged to an M-PESA pay bill/till number of the organization.*/
    @SerialName("ShortCode")
    val shortCode: Int,
)

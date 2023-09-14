//
//  ContentView.swift
//  app-iOS
//
//  Created by Victor Kabata on 15/08/2023.
//

import DarajaMultiplatform
import SwiftUI

struct ContentView: View {
    
    @State private var amount: Int32 = 1
    @FocusState private var isAmountTextFieldFocused: Bool
    
    @State private var phoneNumber: String = ""
    @FocusState private var isPhoneTextFieldFocused: Bool
    
    var daraja=Daraja(
        consumerKey: "MAYA4V9yTveRBa3yP1syMfkgGzDNqSxO",
        consumerSecret: "WU6A0ojDuSJUSP77",
        passKey:"bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919",
        environment: DarajaEnvironment.sandboxEnvironment)
    
    var body: some View {
        VStack {
            
            // Amount textfield
            TextField("Amount", text: .init(get: {"\(amount)"}, set: {
                if let newValue = Int32($0) {
                    amount = newValue
                }
            }))
            .padding()
            .accentColor(.green)
            .background(.white)
            .keyboardType(.numberPad)
            .overlay(
                RoundedRectangle(cornerRadius: 10)
                    .stroke(isAmountTextFieldFocused ? Color.green: Color.gray.opacity(0.5), lineWidth: 1)
            )
            .cornerRadius(10)
            .shadow(color: Color.gray.opacity(0.4), radius: 4, x: 0, y: 2)
            .padding()
            .focused($isAmountTextFieldFocused)
            
            // Phone Number textfield
            TextField("Phone Number", text: $phoneNumber)
                .padding()
                .accentColor(.green)
                .background(.white)
                .keyboardType(.phonePad)
                .overlay(
                    RoundedRectangle(cornerRadius: 10)
                        .stroke(isPhoneTextFieldFocused ? Color.green:Color.gray.opacity(0.5), lineWidth: 1)
                )
                .cornerRadius(10)
                .shadow(color: Color.gray.opacity(0.4), radius: 4, x: 0, y: 2)
                .padding()
                .focused($isPhoneTextFieldFocused)
            
            // Pay Button
            Button(action: {
                initiateMpesaPayment(daraja: daraja, businessShortCode: "174379", amount: amount, phoneNumber: phoneNumber, transactionDesc: "M-Pesa payment", callbackUrl: "https://mydomain.com/path", accountReference: "174379")
            }) {
                Image(systemName: "plus")
                    .padding(20)
                    .background(Color.green)
                    .foregroundColor(.white)
                    .clipShape(Circle())
            }
        }.padding(.horizontal,24)
        
    }
}

func initiateMpesaPayment(daraja:Daraja,
                          businessShortCode: String,
                          amount: Int32,
                          phoneNumber: String,
                          transactionDesc: String,
                          callbackUrl: String,
                          accountReference: String){
    do{
        let response=try daraja.initiateMpesaExpressPayment(businessShortCode: businessShortCode, amount: amount, phoneNumber: phoneNumber,transactionType: DarajaTransactionType.customerpaybillonline, transactionDesc: "M-Pesa payment", callbackUrl: "https://mydomain.com/path", accountReference: "Daraja KMP iOS")
        
        response.onSuccess(action: {data in
            print(data.self)
        })
        .onFailure(action: {error in
            print(error)
        })
        
        print(response)
    }catch{
        print("An error occured")
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

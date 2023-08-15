//
//  ContentView.swift
//  app-iOS
//
//  Created by Victor Kabata on 15/08/2023.
//

import SwiftUI
//import DarajaMultiplatform

struct ContentView: View {
    
    @State private var amount: String = "1"
    @FocusState private var isAmountTextFieldFocused: Bool
    
    @State private var phoneNumber: String = ""
    @FocusState private var isPhoneTextFieldFocused: Bool
    
    var body: some View {
        VStack {
            
            // Amount textfield
            TextField("Amount", text: $amount)
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
                print("Button tapped")
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

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

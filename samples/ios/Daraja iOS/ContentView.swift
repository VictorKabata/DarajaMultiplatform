//
//  ContentView.swift
//  Daraja iOS
//
//  Created by Victor Kabata on 18/08/2024.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        @State var amount: Int32 = 1
            @FocusState var isAmountTextFieldFocused: Bool
            
            @State var phoneNumber: String = ""
            @FocusState var isPhoneTextFieldFocused: Bool
        
        List {
                    DisclosureGroup("M-Pesa Express") {
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
                                            .focused($isPhoneTextFieldFocused)
                                        
                                        // Pay Button
                                        Button(action: {                              }) {
                                            Image(systemName: "plus")
                                                .padding(20)
                                                .background(Color.green)
                                                .foregroundColor(.white)
                                                .clipShape(Circle())
                                        }
                                    }.padding(.horizontal,24)
                        }
                    }
                }
    }

#Preview {
    ContentView()
}

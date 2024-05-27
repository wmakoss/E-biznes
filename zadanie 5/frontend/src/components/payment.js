import React, {createContext, useEffect, useMemo, useState} from 'react';
import { v4 as uuidv4 } from 'uuid';
import sendPayment from "../api/payment";

export const Payment = () => {

    // console.log(uuidv4());
    const [Message, setMessage] = useState("");

    const handleSubmit = (event: React.FormEvent) => {
        event.preventDefault();

        let data = {
            "id": uuidv4(),
            "productId": parseInt(document.getElementsByName("productId")[0].value),
            "cardNumber": document.getElementsByName("cardNumber")[0].value,
            "CVC": parseInt(document.getElementsByName("CVC")[0].value),
            "expirationDate": document.getElementsByName("expirationDate")[0].value,
            "amount": parseFloat(document.getElementsByName("amount")[0].value)
        }

        sendPayment(data).then((resultData) => {setMessage(resultData);});
    };

    return (
        <div className="payment">
            <h1>Payment:</h1>
            <form onSubmit={handleSubmit}>
                Numer karty kredytowej:
                <br/>
                <input type="text" name="cardNumber"/>
                <br/>
                Ważność karty kredytowej:
                <br/>
                <input type="text" name="expirationDate"/>
                <br/>
                CVC:
                <br/>
                <input type="number" name="CVC" min="0"/>
                <br/>
                ProductID:
                <br/>
                <input type="number" name="productId" min="0"/>
                <br/>
                Amount:
                <br/>
                <input type="number" name="amount" min="0"/>
                <br/>
                <input type="submit" value="Pay"/>
                <h2>{Message}</h2>
            </form>
        </div>
    )
}
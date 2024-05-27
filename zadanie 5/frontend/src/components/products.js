import React, {createContext, useEffect, useMemo, useState} from 'react';
import getProducts from "../api/products";

export const Products = () => {

    const [Products, setProducts] = useState([]);

    useEffect(() => {
        getProducts()
            .then((products) => setProducts(products))
            .catch(console.log);
    },[]);

    return (
        <div className="products">
            <h1>Products:</h1>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                </tr>
                </thead>
                <tbody>
                {Products.map((item) => (
                    <tr key={item.id}>
                        <td>{item.id}</td>
                        <td>{item.name}</td>
                        <td>{item.price}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    )
}
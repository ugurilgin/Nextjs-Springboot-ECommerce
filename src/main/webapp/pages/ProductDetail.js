import React, {useEffect} from 'react'
import { useParams } from 'react-router-dom'
import { useState } from 'react';
import { useDispatch } from 'react-redux';
import {addChart, delChart} from '../actions/index'

const ProductDetail = () => {
    const [cartBtn, setCartBtn] = useState("Add to Cart")
    {/* Now we need a product id which is pass from the product page. */}
    const proid = useParams();
    const [product, setProduct] = useState();
    const [loading, setLoading] = useState(true);


    useEffect(() => {
        const getProducts = async () => {

            try {
                const response = await fetch("http://localhost:8080/api/products/"+proid.id);
                if ( response.ok) {
                    console.log(response)
                    const data = await response.json();
                    setProduct(data);
                    setLoading(false);
                } else {
                    throw new Error('Failed to fetch data');
                }
            } catch (error) {
                console.error('Error fetching data:', error);
                setLoading(false);
            }
        };


            getProducts();


    }, [proid]);

    // We need to store useDispatch in a variable
    const dispatch = useDispatch()

    const handleCart = (product) => {
        if (cartBtn === "Add to Cart") {
            dispatch(addChart(product))
            setCartBtn("Remove from Cart")
        }
        else{
            dispatch(delChart(product))
            setCartBtn("Add to Cart")
        }
    }

    return (
        <>
            {!loading && (
                <div className="container my-5 py-3">
                    <div className="row">
                        <div className="col-md-6 d-flex justify-content-center mx-auto product">
                            <img src={product.image} alt={product.title} height="400px" />
                        </div>
                        <div className="col-md-6 d-flex flex-column justify-content-center">
                            <h1 className="display-5 fw-bold">{product.title}</h1>
                            <hr />
                            <h2 className="my-4">${product.price}</h2>
                            <p className="lead">{product.description}</p>
                            <button onClick={() => handleCart(product)} className="btn btn-outline-primary my-5">
                                {cartBtn}
                            </button>
                        </div>
                    </div>
                </div>
            )}
        </>
    )
}

export default ProductDetail

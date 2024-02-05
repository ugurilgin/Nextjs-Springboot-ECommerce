import React, { useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { delChart } from '../actions/index';
import { NavLink } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faShoppingCart } from '@fortawesome/free-solid-svg-icons';

const Cart = () => {
    const [cartOpen, setCartOpen] = useState(false);
    const cartItems = useSelector((state) => state.addItem);
    const dispatch = useDispatch();

    const handleClose = (item) => {
        dispatch(delChart(item));
    };

    const toggleCart = () => {
        setCartOpen(!cartOpen);
    };

    const cartItemComponent = (cartItem) => {
        return (
            <div className="px-4 my-5 bg-light rounded-3" key={cartItem.id}>
                <div className="container py-4">
                    <button onClick={() => handleClose(cartItem)} className="btn-close float-end" aria-label="Close"></button>
                    <div className="row justify-content-center">
                        <div className="col-md-4">
                            <img src={cartItem.image} alt={cartItem.title} height="200px" width="180px" />
                        </div>
                        <div className="col-md-4">
                            <h3>{cartItem.title}</h3>
                            <p className="lead fw-bold">${cartItem.price}</p>
                        </div>
                    </div>
                </div>
            </div>
        );
    };

    return (
        <div className="position-relative">
            <button onClick={toggleCart} className="btn btn-link position-absolute top-0 end-0">
                <FontAwesomeIcon icon={faShoppingCart} size="lg" />
            </button>
            {cartOpen && (
                <div className="position-absolute top-100 end-0 mt-2 me-2">
                    {cartItems.length === 0 ? <h3>Your Cart is Empty</h3> : cartItems.map(cartItemComponent)}
                    <div className="container">
                        <div className="row">
                            <NavLink to="/checkout" className="btn btn-outline-primary mb-5 w-25 mx-auto">Proceed To checkout</NavLink>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default Cart;
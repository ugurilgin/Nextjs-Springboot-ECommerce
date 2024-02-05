import React from 'react';
import { useSelector } from 'react-redux';

const CartBtn = () => {
    const itemsInCart = useSelector(state => state.addItem);

    const cartItemCount = itemsInCart.length;

    if (cartItemCount === 0) {
        return (
            <button className="btn btn-outline-primary ms-2" onClick={() => { window.location.href = "/cart"; }}>
                <span className="fa fa-shopping-cart"></span>
            </button>
        );
    } else {
        return (
            <button className="btn btn-outline-primary ms-2" onClick={() => { window.location.href = "/cart"; }}>
                <span className="fa fa-shopping-cart me-1"></span> Cart ({cartItemCount})
            </button>
        );
    }
}

export default CartBtn;
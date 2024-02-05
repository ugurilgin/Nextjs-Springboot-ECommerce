import React, { useState } from 'react';
import { useSelector } from 'react-redux';

const Checkout = () => {
    const items = useSelector((state) => state.addItem);
    const [formData, setFormData] = useState({
        address: {
            id: 0,
            address: '',
            city: '',
            country: ''
        },
    });

    const handleSubmit = async(e) => {
        e.preventDefault();
        const addressData={
                id: -1,
                address: formData.address,
                city: formData.city,
                country: formData.country

        };
        const checkoutData = {
            address: addressData,
            products: items,
            sumPrice: items.reduce((acc, item) => acc + item.price, 0)
        };

        try {
            const response = await fetch('http://localhost:8080/api/orders', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(checkoutData)
            });

            if (response.ok) {
                console.log('Post request successful!');
            } else {
                console.error('Post request failed!');
            }
        } catch (error) {
            console.error('Error while sending post request:', error);
        }
        console.log(checkoutData);
    };

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.id]: e.target.value });
    };

    return (
        <div className="container my-5">
            <div className="row g-5">
                <div className="col-md-5 col-lg-4 order-md-last">
                    <h4 className="d-flex justify-content-between align-items-center mb-3">
                        <span className="text-primary">Your cart</span>
                        <span className="badge bg-primary rounded-pill">{items.length}</span>
                    </h4>
                    <ul className="list-group mb-3">
                        {items.map((item, index) => (
                            <li key={index} className="list-group-item d-flex justify-content-between lh-sm">
                                <div>
                                    <h6 className="my-0">{item.title}</h6>
                                </div>
                                <span className="text-muted">${item.price}</span>
                            </li>
                        ))}
                        <li className="list-group-item d-flex justify-content-between">
                            <span>Total (USD)</span>
                            <strong>${items.reduce((acc, item) => acc + item.price, 0)}</strong>
                        </li>
                    </ul>
                </div>
                <div className="col-md-7 col-lg-8">
                    <h4 className="mb-3">Billing address</h4>
                    <form className="needs-validation" onSubmit={handleSubmit} noValidate>
                        <div className="row g-3">
                            <div className="col-12">
                                <label htmlFor="address" className="form-label">Address</label>
                                <input type="text" className="form-control" id="address" placeholder="1234 Main St" required onChange={handleChange} />
                                <div className="invalid-feedback">
                                    Please enter your shipping address.
                                </div>
                            </div>
                            <div className="col-md-5">
                                <label htmlFor="country" className="form-label">Country</label>
                                <select className="form-select" id="country" required onChange={handleChange}>
                                    <option value="">Choose...</option>
                                    <option>United States</option>
                                </select>
                                <div className="invalid-feedback">
                                    Please select a valid country.
                                </div>
                            </div>
                            <div className="col-md-4">
                                <label htmlFor="city" className="form-label">City</label>
                                <select className="form-select" id="city" required onChange={handleChange}>
                                    <option value="">Choose...</option>
                                    <option>California</option>
                                </select>
                                <div className="invalid-feedback">
                                    Please provide a valid state.
                                </div>
                            </div>
                        </div>
                        <hr className="my-4" />
                        <button className="w-100 btn btn-primary btn-lg" type="submit">Continue to checkout</button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default Checkout;

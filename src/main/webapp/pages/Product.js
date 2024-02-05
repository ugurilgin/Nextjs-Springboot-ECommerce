import React, {useEffect, useState} from 'react'
import {NavLink} from 'react-router-dom';

const Product = () => {
    const [data, setData] = useState([]);
    const [filter, setFilter] = useState(data);
    const [loading, setLoading] = useState(false);
    let componentMounted = true;

    useEffect(() => {
        const getProducts = async () => {
            setLoading(true);
            try {
                const response = await fetch("http://localhost:8080/api/products");
                console.log(response);
                if (componentMounted && response.ok) {
                    const data = await response.json();
                    setData(data);
                    setFilter(data);
                    setLoading(false);
                } else {
                    throw new Error('Failed to fetch data');
                }
            } catch (error) {
                console.error('Error fetching data:', error);
                setLoading(false);
            }
        }
    
        getProducts(); 
    
        return () => {
            componentMounted = false; 
        };
    }, []);
    const cardItem = (item) => {
        return (
            <div class="card my-5 py-4" key={item.id} style={{width: "18rem"}}>
                <img src={item.image} class="card-img-top" alt={item.title}/>
                <div class="card-body text-center">
                    <h5 class="card-title">{item.title}</h5>
                    <p className="lead">${item.price}</p>
                    <NavLink to={`/products/${item.id}`} class="btn btn-outline-dark">Buy Now</NavLink>
                </div>
            </div>
        );
    }

    return (
        <div>
            <div className="container py-5">
                <div className="row">
                    <div className="col-12 text-center">
                        <h1>Product</h1>
                        <hr/>
                    </div>
                </div>
            </div>
            <div className="container">
                <div className="row justify-content-around">
                    {data.map(cardItem)}
                </div>
            </div>
        </div>
    )
}

export default Product

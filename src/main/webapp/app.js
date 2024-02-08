import React from 'react';
import Home from './pages/Home'
import Header from './components/Header'
import Footer from './components/Footer'
import {Routes,Route} from "react-router-dom"
import Product from "./pages/Product";
import ProductDetail from "./pages/ProductDetail";
import Cart from "./components/Cart";
import Checkout from "./pages/Checkout";
import About from "./pages/About";
import Contact from "./pages/Contact";
import NotFound from "./pages/NotFound";

function App() {
 
  return (
    <>

      <Header/>
        <Routes>
            <Route exact path="/" element={<Home />} />
            <Route exact path="/products" element={<Product />} />
            <Route path="/products/:id" element={<ProductDetail/>} />
            <Route path="/cart" element={<Cart/>} />
            <Route exact path="/checkout" element={<Checkout/>} />
            <Route exact path="/about" element={<About/>} />
            <Route exact path="/contact" element={<Contact/>} />
            <Route path="*" element={<NotFound />} />
        </Routes>
      <Footer/>
    </>
  );

}

export default App;
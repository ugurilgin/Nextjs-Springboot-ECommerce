import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min'
import {Provider} from 'react-redux'
import App from './App';
import store from "./store";

ReactDOM.render(
    <BrowserRouter>
        <Provider store={store}>
            <App />
            </Provider>
    </BrowserRouter>,
    document.getElementById('react')
);



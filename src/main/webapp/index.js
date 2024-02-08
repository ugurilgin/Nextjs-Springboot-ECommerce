import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min'
import {Provider} from 'react-redux'
import App from './App';
import store from "./store";
import { HashRouter } from 'react-router-dom'

ReactDOM.render(
    <HashRouter>
        <Provider store={store}>
            <App />
            </Provider>
    </HashRouter>,
    document.getElementById('react')
);



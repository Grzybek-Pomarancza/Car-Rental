import React from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import Routes from "./Routes";


function App() {
    return (<Router>
                <Routes/>
            </div></Router>
    );
}

export default App;

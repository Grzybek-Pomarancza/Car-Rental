import React from "react";
import Login from "./Components/Login";
import SignUp from "./Components/SignUp";
import {Switch, Route} from "react-router-dom";

const Routes = () =>{
    return(
        <div className="auth-wrapper">
            <div className="auth-inner">
                <Switch>
                    <Route exact path='/' component={Login} />
                    <Route path="/sign-in" component={Login} />
                    <Route path="/sign-up" component={SignUp} />
                </Switch>
            </div>
        </div>
    )
}
export default Routes;
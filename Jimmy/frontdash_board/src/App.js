import React, {Fragment, useState} from 'react';
import {BrowserRouter as Router, Switch, Route} from "react-router-dom";
import {TransitionGroup, CSSTransition} from "react-transition-group";

import './App.css';
import Home from "./Components/Log/home_login"
import SignUp from "./Components/Log/Signup_component";
import Navbar from './Components/Navbar/Navbar'

function App() {
    function getLoggedStorage() {
        if (localStorage.getItem('IsLogged')) {
            var result = Boolean(localStorage.getItem('IsLogged'))
            return result;
        } else {
            return false;
        }
    }

    const [isLogged, setisLogged] = useState(getLoggedStorage());

    return (

        <Router>
            <Fragment>
                <Navbar isLogged={isLogged} setisLogged={setisLogged}/>
                <Route render={({location})=>(
                    <TransitionGroup>
                        <CSSTransition
                            key={location.key}
                            timeout={300}
                            classNames="fade">

                            <Switch location={location}>
                                <Route exact path="/">
                                    <Home isLogged={isLogged} setisLogged={setisLogged}/>
                                </Route>
                                <Route path="/Sign-in">
                                    <SignUp isLogged={isLogged} setisLogged={setisLogged}/>
                                </Route>
                                <Route path="/Sign-up" component={SignUp}/>
                            </Switch>

                        </CSSTransition>
                    </TransitionGroup>
                )}/>

            </Fragment>
        </Router>
    );
}

export default App;

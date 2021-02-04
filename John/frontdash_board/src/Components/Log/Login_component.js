import React, {Fragment, useState} from 'react';
import {Link, useHistory} from "react-router-dom";
import Axios from 'axios';
import Img from '../../style/img/logo.png'
import Google from "../../style/img/google.png";
import Facebook from "../../style/img/facebook.png";
import Git from "../../style/img/Github.png";

export default function Login_component({setisLogged, isLogged}) {

    let history = useHistory();
// Handle de l'input email
    const [Email,
        setEmail] = useState('');
    // Handle de l'input Mdp
    const [Mdp,
        setMdp] = useState('');

    /* Création d'une function requête Asynchrone recevant l'email et le mdp entré */
    const authentification = async (email, mdp) => {
        try {
            /*Création d'une requête appelant la route login */
            let res = await Axios({
                method: "POST",
                url: "http://localhost:8080/authenticate",

                /* Envoie des données dans la requête*/
                data: {
                    username: email,
                    password: mdp
                }
            })
            /* Enregistrement du token et de l'email dans le local Storage*/
            localStorage.setItem('token', res.data.token);
            localStorage.setItem('id', res.data.id);
            localStorage.setItem('IsLogged', true);

            setisLogged(true)


            history.push('/Users');

        } catch (e) {
            /* Alerte de l'erreur */
            alert("Invalid credential " + e);
            setisLogged(false)
        }
    }


    // Handle au click sur le bouton Login
    const handleSubmit = () => {
        // Authentification avec comme paramètre Email et Mdp
        if(Email !== '' && Mdp !== '') {
            authentification(Email, Mdp)
        }
    }

    const ifConnect = () => {
        if (isLogged) return connected()
        return notconnected();
    }
    const connected = () => {
        // Redirection
        history.push('Users');
        return <Fragment>
            <div className="page">
                <p>
                    Users Already connected
                </p>
            </div>

        </Fragment>
    }
    const notconnected = () => {
        return (
            <Fragment>
                <div className=" container margin-top">
                    <div className=" auth-inner">
                        <form>
                            <div className="form-group text-center auth-inner-icon">
                                <img src={Img} className="iconPirate" alt=""/>
                            </div>
                            <div className="form-group margin-top input-field">
                                {/* Email Input */}
                                <input
                                    id="email"
                                    type="email"
                                    className="input"
                                    name="email"
                                    onChange={event => setEmail(event.target.value)} required/>
                                <label htmlFor="email" className="labelinput">Email</label>
                            </div>
                            <div className="form-group input-field">

                                <input
                                    type="password"
                                    className="input"
                                    id="mdp"
                                    name="mdp"
                                    onChange={event => setMdp(event.target.value)} required/>
                                <label htmlFor="mdp" className="labelinput">Password</label>


                                <button className="bobutton connexion" onClick={() => handleSubmit()}>
                                    Sign in with your email
                                </button>
                            </div>
                        </form>
                        <div className="form-group input-field">

                            <h6 className="or">- or -</h6>
                            <button className="bobutton google">
                                <img src={Google} className="btnGoogle" alt=""/>
                                Sign in with Google
                            </button>
                            <button className="bobutton fb">
                                <img src={Facebook} className="btnFacebook" alt=""/>
                                Sign in with Facebook
                            </button>
                            <button className="bobutton git">
                                <img src={Git} className="btnGit" alt=""/>
                                Sign in with GitHub
                            </button>
                        </div>

                        <div className="d-flex justify-content-center links margin-top italic">
                            Don't have an account?<Link to={"/Sign-up"}> Sign Up</Link>
                        </div>
                    </div>
                </div>
            </Fragment>
        )
    }

    /* Return du component */
    return ifConnect()

}
/**/

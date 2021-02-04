import {Link} from "react-router-dom";
import React, {Fragment, useState} from 'react'
import Axios from 'axios';
import {useHistory} from "react-router-dom";
import Img from "../../style/img/logo.png";

export default function Signup_component() {
    let history = useHistory();

    const [Lastname,
        setLastName] = useState("");
    const [firstName,
        setFirstname] = useState("");

    const [email,
        setEmail] = useState('');
    const [num,
        setNum] = useState('');
    const [mdp,
        setMdp] = useState('');

    const [reMdp,
        setReMdp] = useState('')



    const createUser = async () => {

        if (mdp === reMdp && mdp !== "") {
            try {

                await Axios({
                    method: "POST",
                    url: "http://localhost:8080/register",
                    data: {
                        nom: Lastname,
                        prenom: firstName,
                        username: email,
                        num: num,
                        password: mdp,
                    }
                }).then(
                    alert("Bienvenue sur JoJi_Board Mr." + firstName + " " + Lastname + ".\nVous etes maintenant inscrit"),
                    history.push('/')
                )
            } catch (e) {
                alert(e.response.data.msg);
            }

        } else {
            alert("Mot de passe différent de la confirmation !")
        }

    }

    return (
        <Fragment>
            <div className="container margin-top page">
                <div className="auth-inner">
                    <div className="form-group text-center auth-inner-icon">
                        <img src={Img} className="iconPirate" alt=""/>
                    </div>
                    <form>

                        <div className="form-group input-field ">
                            <input
                                id="firstname"
                                type="text"
                                className=" input"
                                onChange={e => setFirstname(e.target.value)}
                                required/>
                            <label htmlFor="firstname" className="labelinput">First name</label>

                        </div>

                        <div className="form-group input-field">
                            <input
                                id="lastname"
                                type="text"
                                className="input"
                                onChange={e => setLastName(e.target.value)}
                                required/>
                            <label htmlFor="lastname" className="labelinput">Last name</label>

                        </div>
                        <div className="form-group input-field">
                            <input
                                id="email"
                                type="email"
                                className="input"
                                onChange={e => setEmail(e.target.value)}
                                required/>
                            <label htmlFor="email" className="labelinput">Enter email</label>

                        </div>
                        <div className="form-group input-field">
                            <input
                                id="num"
                                type="text"
                                className="input"
                                title="10 digits only allowed"
                                maxLength="10"
                                minLength="10"
                                pattern="[0-9]{10}"
                                onChange={e => setNum(e.target.value)}
                                required/>
                            <label htmlFor="num" className="labelinput">Enter num</label>

                        </div>
                        <div className="form-group input-field">
                            <input
                                id="password"
                                type="password"
                                className="input"
                                onChange={e => setMdp(e.target.value)}
                                required/>
                            <label htmlFor="password" className="labelinput">Password</label>

                        </div>
                        <div className="form-group input-field">
                            <input
                                id="confirmpassword"
                                type="password"
                                className="input"
                                onChange={e => setReMdp(e.target.value)}
                                required/>
                            <label htmlFor="password" className="labelinput">Confirm password</label>

                        </div>

                        <button
                            className="btn btn-primary bobutton connexion"
                            onClick={() => createUser()}>Sign Up
                        </button>
                        <p className="forgot-password text-right">
                            Already registered
                            <Link to={"/Sign-in"}> Sign In</Link>
                        </p>
                    </form>
                </div>
            </div>
        </Fragment>
    )
}
